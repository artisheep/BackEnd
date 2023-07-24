package com.swave.urnr.releasenote.service;

import com.swave.urnr.chatgpt.responsedto.ChatGPTResultResponseDTO;
import com.swave.urnr.chatgpt.service.ChatGPTService;
import com.swave.urnr.project.domain.Project;
import com.swave.urnr.project.repository.ProjectRepository;
import com.swave.urnr.releasenote.domain.*;
import com.swave.urnr.releasenote.repository.*;
import com.swave.urnr.releasenote.requestdto.*;
import com.swave.urnr.releasenote.responsedto.*;
import com.swave.urnr.user.domain.User;
import com.swave.urnr.user.domain.UserInProject;
import com.swave.urnr.user.repository.UserInProjectRepository;
import com.swave.urnr.user.repository.UserRepository;
import com.swave.urnr.util.http.HttpResponse;
import com.swave.urnr.util.type.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableTransactionManagement
public class ReleaseNoteServiceImpl implements ReleaseNoteService{

    private final CommentRepository commentRepository;
    private final NoteBlockRepository noteBlockRepository;
    private final BlockContextRepository blockContextRepository;
    private final ReleaseNoteRepository releaseNoteRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final UserInProjectRepository userInProjectRepository;
    private final ChatGPTService chatGPTService;
    private final SeenCheckRepository seenCheckRepository;

    private final EntityManager em;

    @Override
    @Transactional
    public HttpResponse createReleaseNote(HttpServletRequest request, Long projectId, ReleaseNoteCreateRequestDTO releaseNoteCreateRequestDTO) {
        Date currentDate = new Date();

        User user = userRepository.findById((Long) request.getAttribute("id"))
                .orElseThrow(NoSuchElementException::new);

        Project project = projectRepository.findById(projectId)
                .orElseThrow(NoSuchElementException::new);

        ReleaseNote releaseNote = ReleaseNote.builder()
                .version(releaseNoteCreateRequestDTO.getVersion())
                .lastModifiedDate(currentDate)
                .releaseDate(releaseNoteCreateRequestDTO.getReleaseDate())
                .count(0)
                .isUpdated(false)
                .summary("Temp data until ChatGPT is OKAY")
                .project(project)
                .user(user)
                .commentList(new ArrayList<Comment>())
                .build();

        releaseNoteRepository.save(releaseNote);


        StringBuilder content = new StringBuilder(new String());
        List<NoteBlock> noteBlockList = new ArrayList<>();

        for(NoteBlockCreateRequestDTO noteBlockCreateRequestDTO : releaseNoteCreateRequestDTO.getBlocks()){
            NoteBlock noteBlock = NoteBlock.builder()
                    .label(noteBlockCreateRequestDTO.getLabel())
                    .releaseNote(releaseNote)
                    .build();

            noteBlockRepository.save(noteBlock);

            List<BlockContext> blockContextList = new ArrayList<>();

            for (BlockContextCreateRequestDTO blockContextCreateRequestDTO : noteBlockCreateRequestDTO.getContexts()){
                BlockContext blockContext = BlockContext.builder()
                        .context(blockContextCreateRequestDTO.getContext())
                        .tag(blockContextCreateRequestDTO.getTag())
                        .index(blockContextCreateRequestDTO.getIndex())
                        .noteBlock(noteBlock)
                        .build();
                content.append(blockContext.getContext());

                blockContextRepository.save(blockContext);

                blockContextList.add(blockContext);
            }
            blockContextRepository.flush();

            noteBlock.setBlockContextList(blockContextList);

            noteBlockList.add(noteBlock);
        }
        blockContextRepository.flush();

//        ChatGPTResultDTO ChatGPTResultDTO =  chatGPTService.chatGptResult(
//                new ChatGPTQuestionRequestDTO(content.toString() + "의 내용을 세줄로 요약해줘"));

        releaseNote.setNoteBlockList(noteBlockList);
        //releaseNote.setSummary(ChatGPTResultDTO.getText());

        releaseNoteRepository.flush();

        return HttpResponse.builder()
                .message("Release Note Created")
                .description("Release Note ID : " + releaseNote.getId() + " Created")
                .build();
    }

    //project id를 받아서 해당 project에 연결된 모든 releaseNote를 리스트로 반환 -> 전체 출력용
    @Override
    public ArrayList<ReleaseNoteContentListResponseDTO> loadReleaseNoteList(Long projectId){
        List<ReleaseNote> releaseNoteList = releaseNoteRepository.findByProject_Id(projectId);

        ArrayList<ReleaseNoteContentListResponseDTO> releaseNoteContentListDTOArrayListResponse = new ArrayList<>();

        for(ReleaseNote releaseNote : releaseNoteList){
            releaseNoteContentListDTOArrayListResponse.add(releaseNote.makeReleaseNoteContentListDTO());
        }

        return releaseNoteContentListDTOArrayListResponse;
    }

    @Override
    public ReleaseNoteContentResponseDTO loadReleaseNote(HttpServletRequest request, Long releaseNoteId){
        UserInProject userInProject = releaseNoteRepository.findUserInProjectByUserIdAndReleaseNoteId((Long) request.getAttribute("id"), releaseNoteId);

        if(userInProject == null){
            throw new AccessDeniedException("해당 프로젝트의 접근 권한이 없습니다.");
        }

        ReleaseNote releaseNote = releaseNoteRepository.findById(releaseNoteId)
                .orElseThrow(NoSuchElementException::new);

        ArrayList<Comment> commentList = commentRepository.findByReleaseNote_Id(releaseNoteId);
        ArrayList<CommentContentResponseDTO> commentContentList = new ArrayList<>();

        for(Comment comment : commentList){
            CommentContentResponseDTO commentContentResponseDTO = new CommentContentResponseDTO();
            commentContentResponseDTO.setName(comment.getUser().getUsername());
            commentContentResponseDTO.setContext(comment.getCommentContext());
            commentContentResponseDTO.setLastModifiedDate(comment.getLastModifiedDate());

            commentContentList.add((commentContentResponseDTO));
        }

        ReleaseNoteContentResponseDTO releaseNoteContentResponseDTO = releaseNote.makeReleaseNoteContentDTO();
        releaseNoteContentResponseDTO.setComment(commentContentList);

        increaseViewCount(releaseNoteId);
        seenCheck(request, releaseNote, userInProject);

        return releaseNoteContentResponseDTO;
    }

    @Override
    @Transactional
    public HttpResponse updateReleaseNote(HttpServletRequest request, Long releaseNoteId, ReleaseNoteUpdateRequestDTO releaseNoteUpdateRequestDTO){
        User user = userRepository.findById((Long) request.getAttribute("id"))
                .orElseThrow(NoSuchElementException::new);

        ReleaseNote releaseNote = releaseNoteRepository.findById(releaseNoteId)
                .orElseThrow(NoSuchElementException::new);

        for(NoteBlock noteBlock : releaseNote.getNoteBlockList()){
            noteBlockRepository.deleteById(noteBlock.getId());
        }

        releaseNote.getNoteBlockList().clear();

        StringBuilder content = new StringBuilder(new String());
        List<NoteBlock> noteBlockList = new ArrayList<>();

        for(NoteBlockUpdateRequestDTO noteBlockUpdateRequestDTO : releaseNoteUpdateRequestDTO.getBlocks()){
            NoteBlock noteBlock = NoteBlock.builder()
                    .label(noteBlockUpdateRequestDTO.getLabel())
                    .releaseNote(releaseNote)
                    .build();

            noteBlockRepository.save(noteBlock);

            List<BlockContext> blockContextList = new ArrayList<>();

            for (BlockContextUpdateRequestDTO blockContextUpdateRequestDTO : noteBlockUpdateRequestDTO.getContexts()){
                BlockContext blockContext = BlockContext.builder()
                        .context(blockContextUpdateRequestDTO.getContext())
                        .tag(blockContextUpdateRequestDTO.getTag())
                        .index(blockContextUpdateRequestDTO.getIndex())
                        .noteBlock(noteBlock)
                        .build();
                content.append(blockContext.getContext());

                blockContextRepository.save(blockContext);

                blockContextList.add(blockContext);
            }
            blockContextRepository.flush();

            noteBlock.setBlockContextList(blockContextList);

            noteBlockList.add(noteBlock);
        }
        blockContextRepository.flush();


//        ChatGPTResultDTO ChatGPTResultDTO =  chatGPTService.chatGptResult(
//                new ChatGPTQuestionRequestDTO(content.toString() + "의 내용을 세줄로 요약해줘"));


        releaseNote.setVersion(releaseNoteUpdateRequestDTO.getVersion());
        releaseNote.setReleaseDate(releaseNoteUpdateRequestDTO.getReleaseDate());
        releaseNote.setLastModifiedDate(new Date());
        releaseNote.getNoteBlockList().addAll(noteBlockList);
        releaseNote.setSummary("Temp data until ChatGPT is OKAY");
        //releaseNote.setSummary(ChatGPTResultDTO.getText());
        releaseNote.setUser(user);
        releaseNote.setUpdated(true);

        return HttpResponse.builder()
                .message("Release Note Updated")
                .description("Release Note ID : " + releaseNote.getId() + " Updated")
                .build();
    }

    @Override
    public ArrayList<ReleaseNoteVersionListResponseDTO> loadProjectVersionList(HttpServletRequest request){
        ArrayList<ReleaseNoteVersionListResponseDTO> responseVersionListDTOList = new ArrayList<>();
        List<UserInProject> userInProjectList = userInProjectRepository.findByUser_Id((Long) request.getAttribute("id"));

        for(UserInProject userInProject : userInProjectList){
            ReleaseNoteVersionListResponseDTO releaseNoteVersionListResponseDTO = new ReleaseNoteVersionListResponseDTO();

            releaseNoteVersionListResponseDTO.setProjectId(userInProject.getProject().getId());
            releaseNoteVersionListResponseDTO.setProjectName(userInProject.getProject().getName());
            releaseNoteVersionListResponseDTO.setSubscribe(UserRole.Subscriber == userInProject.getRole());

            List<ReleaseNote> releaseNoteList = userInProject.getProject().getReleaseNoteList();
            ArrayList<ReleaseNoteVersionResponseDTO> releaseNoteVersionListDTOListResponse = new ArrayList<>();

            for(ReleaseNote releaseNote : releaseNoteList){
                ReleaseNoteVersionResponseDTO releaseNoteVersionResponseDTO = new ReleaseNoteVersionResponseDTO();

                releaseNoteVersionResponseDTO.setReleaseNoteId(releaseNote.getId());
                releaseNoteVersionResponseDTO.setVersion(releaseNote.getVersion());

                releaseNoteVersionListDTOListResponse.add(releaseNoteVersionResponseDTO);
            }
            releaseNoteVersionListResponseDTO.setReleaseNoteVersionList(releaseNoteVersionListDTOListResponse);

            responseVersionListDTOList.add(releaseNoteVersionListResponseDTO);
        }
        return responseVersionListDTOList;
    };

    @Override
    @Transactional
    public HttpResponse deleteReleaseNote(Long releaseNoteId){
        releaseNoteRepository.deleteById(releaseNoteId);

        return HttpResponse.builder()
                .message("Release Note Deleted")
                .description("Release Note ID : " + releaseNoteId + " Deleted")
                .build();
    }

    @Override
    public ReleaseNoteContentResponseDTO loadRecentReleaseNote(HttpServletRequest request){
        ReleaseNote releaseNote = releaseNoteRepository.findMostRecentReleaseNote((Long) request.getAttribute("id"));

        return releaseNote.makeReleaseNoteContentDTO();
    }

    @Override
    public void increaseViewCount(Long releaseNoteId){
        ReleaseNote releaseNote = releaseNoteRepository.findById(releaseNoteId)
                .orElseThrow(NoSuchElementException::new);
        releaseNote.addViewCount();
        releaseNoteRepository.flush();
    }

    @Override
    @Transactional
    public void seenCheck(HttpServletRequest request, ReleaseNote releaseNote, UserInProject userInProject){
        if(seenCheckRepository.findByUserInProjectIdAndReleaseNoteId(userInProject.getId(), releaseNote.getId()) != null) {
            return;
        }

        SeenCheck seenCheck = SeenCheck.builder()
                .userName((String) request.getAttribute("username"))
                .releaseNote(releaseNote)
                .userInProject(userInProject)
                .build();

        seenCheckRepository.save(seenCheck);
        seenCheckRepository.flush();
    }
}

