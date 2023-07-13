package com.swave.releasenotesharesystem.ReleaseNote.service;

import com.swave.releasenotesharesystem.ChatGPT.service.ChatGPTService;
import com.swave.releasenotesharesystem.Project.domain.Project;
import com.swave.releasenotesharesystem.Project.repository.ProjectRepository;
import com.swave.releasenotesharesystem.ReleaseNote.requestDTO.RequestNewCommentDTO;
import com.swave.releasenotesharesystem.ReleaseNote.requestDTO.RequestNewReleaseNoteDTO;
import com.swave.releasenotesharesystem.ReleaseNote.requestDTO.RequestUpdateReleaseNoteDTO;
import com.swave.releasenotesharesystem.ReleaseNote.domain.Comment;
import com.swave.releasenotesharesystem.ReleaseNote.domain.NoteBlock;
import com.swave.releasenotesharesystem.ReleaseNote.domain.ReleaseNote;
import com.swave.releasenotesharesystem.ReleaseNote.repository.CommentRepository;
import com.swave.releasenotesharesystem.ReleaseNote.repository.NoteBlockRepository;
import com.swave.releasenotesharesystem.ReleaseNote.repository.ReleaseNoteRepository;
import com.swave.releasenotesharesystem.ReleaseNote.responseDTO.*;
import com.swave.releasenotesharesystem.User.domain.User;
import com.swave.releasenotesharesystem.User.repository.UserRepository;
import com.swave.releasenotesharesystem.Util.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableTransactionManagement
public class ReleaseNoteAllServiceImpl implements CommentService, NoteBlockService, ReleaseNoteService{

    private final CommentRepository commentRepository;
    private final NoteBlockRepository noteBlockRepository;
    private final ReleaseNoteRepository releaseNoteRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ChatGPTService chatGPTService;

    @Override
    @Transactional
    public HttpResponse createReleaseNote(HttpServletRequest request, Long projectId, RequestNewReleaseNoteDTO requestNewReleaseNoteDTO) {
        Date currentDate = new Date();
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        log.info(request.toString());
        User user = userRepository.findById((Long) request.getAttribute("id"))
                .orElseThrow(NoSuchElementException::new);

        Project project = projectRepository.findById(projectId)
                .orElseThrow(NoSuchElementException::new);

        //todo : 추후 noteBlock 제대로 구현시 create 방법을 고쳐야함
        NoteBlock noteBlock = NoteBlock.builder()
                .noteBlockContext(requestNewReleaseNoteDTO.getContent())
                .build();


//        ChatGPTResultDTO chatGPTResultDTO =  chatGPTService.chatGptResult(
//                new ChatGPTQuestionRequestDTO(noteBlock.getNoteBlockContext() + "의 내용을 세줄로 요약해줘"));

        ReleaseNote releaseNote = ReleaseNote.builder()
                .version(requestNewReleaseNoteDTO.getVersion())
                .lastModifiedDate(currentDate)
                .releaseDate(requestNewReleaseNoteDTO.getReleaseDate())
                .count(0)
                .isUpdated(false)
//              .summary(chatGPTResultDTO.getText())
                .summary("Temp data until ChatGPT is OKAY")
                .project(project)
                .noteBlockList(new ArrayList<NoteBlock>(Collections.singletonList(noteBlock)))
                .user(user)
                .commentList(new ArrayList<Comment>())
                .build();

        noteBlock.setReleaseNote(releaseNote);
        releaseNoteRepository.save(releaseNote);
        noteBlockRepository.save(noteBlock);

        releaseNoteRepository.flush();
        noteBlockRepository.flush();

        return HttpResponse.builder()
                .message("Release Note Created")
                .description("Release Note ID : " + releaseNote.getId() + " Created")
                .build();
    }

    //project id를 받아서 해당 project에 연결된 모든 releaseNote를 리스트로 반환 -> 전체 출력용
    //todo : noteBlock의 상세 구현법을 정한 이후에는 그것을 적용할 것 (현재는 단순히 글씨를 출력)
    @Override
    public ArrayList<ResponseReleaseNoteContentListDTO> loadReleaseNoteList(Long projectId){
        List<ReleaseNote> releaseNoteList = releaseNoteRepository.findByProject_Id(projectId);

        ArrayList<ResponseReleaseNoteContentListDTO> responseReleaseNoteContentListDTOArrayList = new ArrayList<>();

        for(ReleaseNote releaseNote : releaseNoteList){
            responseReleaseNoteContentListDTOArrayList.add(releaseNote.makeReleaseNoteContentListDTO());
        }

        return responseReleaseNoteContentListDTOArrayList;
    }

    @Override
    public ResponseReleaseNoteContentDTO loadReleaseNote(Long releaseNoteId){
        ReleaseNote releaseNote = releaseNoteRepository.findById(releaseNoteId)
                .orElseThrow(NoSuchElementException::new);

        ArrayList<Comment> commentList = commentRepository.findByReleaseNote_Id(releaseNoteId);
        ArrayList<ResponseCommentContentDTO> commentContentList = new ArrayList<>();

        for(Comment comment : commentList){
            ResponseCommentContentDTO responseCommentContentDTO = new ResponseCommentContentDTO();
            responseCommentContentDTO.setName(comment.getUser().getName());
            responseCommentContentDTO.setContext(comment.getCommentContext());
            responseCommentContentDTO.setLastModifiedDate(comment.getLastModifiedDate());

            commentContentList.add((responseCommentContentDTO));
        }

        ResponseReleaseNoteContentDTO responseReleaseNoteContentDTO = releaseNote.makeReleaseNoteContentDTO();
        responseReleaseNoteContentDTO.setComment(commentContentList);

        return responseReleaseNoteContentDTO;
    }

    @Override
    public ResponseReleaseNoteVersionListDTO loadVersionList(Long projectId){
        List<ReleaseNote> releaseNoteList = releaseNoteRepository.findByProject_Id(projectId);

        ResponseReleaseNoteVersionListDTO responseReleaseNoteVersionListDTO = new ResponseReleaseNoteVersionListDTO(new ArrayList<>());

        for(ReleaseNote releaseNote : releaseNoteList){
            responseReleaseNoteVersionListDTO.getVersionList().add(releaseNote.getVersion());
        }

        return responseReleaseNoteVersionListDTO;
    }

    @Override
    @Transactional
    public HttpResponse createComment(HttpServletRequest request, RequestNewCommentDTO requestNewCommentDTO){

        User user = userRepository.findById((Long) request.getAttribute("id"))
                .orElseThrow(NoSuchElementException::new);

        ReleaseNote releaseNote = releaseNoteRepository.findById(requestNewCommentDTO.getReleaseNoteId())
                .orElseThrow(NoSuchElementException::new);

        Comment comment = Comment.builder()
                .user(user)
                .commentContext(requestNewCommentDTO.getContent())
                .releaseNote(releaseNote)
                .lastModifiedDate(new Date())
                .build();

        releaseNote.getCommentList().add(comment);

        commentRepository.save(comment);
        releaseNoteRepository.save(releaseNote);

        commentRepository.flush();
        releaseNoteRepository.flush();

        return HttpResponse.builder()
                .message("Comment Created")
                .description("Comment ID : " + releaseNote.getId() + " Created")
                .build();
    }

    //todo : user의 정보를 세션에서 받아와서 user field를 채울 것 (세션/토큰 구현 후)
    @Override
    @Transactional
    public HttpResponse updateReleaseNote(HttpServletRequest request, RequestUpdateReleaseNoteDTO requestUpdateReleaseNoteDTO){
        User user = userRepository.findById((Long) request.getAttribute("id"))
                .orElseThrow(NoSuchElementException::new);

        ReleaseNote releaseNote = releaseNoteRepository.findById(requestUpdateReleaseNoteDTO.getReleaseNoteId())
                .orElseThrow(NoSuchElementException::new);

        releaseNote.setVersion(requestUpdateReleaseNoteDTO.getVersion());
        releaseNote.setReleaseDate(requestUpdateReleaseNoteDTO.getReleaseDate());
        releaseNote.setLastModifiedDate(new Date());
        //todo : 추후 noteBlock 제대로 구현시 업데이트 방법을 고쳐야함
        releaseNote.getNoteBlockList().get(0).setNoteBlockContext(requestUpdateReleaseNoteDTO.getContent());
        releaseNote.setUser(user);
        releaseNote.setUpdated(true);

        return HttpResponse.builder()
                .message("Release Note Updated")
                .description("Release Note ID : " + releaseNote.getId() + " Updated")
                .build();
    };
    public ResponseCommentContentListDTO loadRecentComment(Long projectId){
        ResponseCommentContentListDTO responseCommentContentListDTO = new ResponseCommentContentListDTO(new ArrayList<>());
        List<Comment> comments = commentRepository.findTop5RecentComment(projectId);

        for(Comment comment : comments) {
            ResponseCommentContentDTO responseCommentContentDTO = new ResponseCommentContentDTO();

            responseCommentContentDTO.setName(comment.getUser().getName());
            responseCommentContentDTO.setContext(comment.getCommentContext());
            responseCommentContentDTO.setLastModifiedDate(comment.getLastModifiedDate());

            responseCommentContentListDTO.getComments().add(responseCommentContentDTO);
        }

        return responseCommentContentListDTO;
    }
}
