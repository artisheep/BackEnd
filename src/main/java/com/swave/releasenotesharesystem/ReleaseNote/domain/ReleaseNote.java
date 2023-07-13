package com.swave.releasenotesharesystem.ReleaseNote.domain;

import com.swave.releasenotesharesystem.Project.domain.Project;
import com.swave.releasenotesharesystem.ReleaseNote.responseDTO.ResponseReleaseNoteContentDTO;
import com.swave.releasenotesharesystem.ReleaseNote.responseDTO.ResponseReleaseNoteContentListDTO;
import com.swave.releasenotesharesystem.User.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@ToString(exclude = {"user", "project", "commentList", "noteBlockList"})
@NoArgsConstructor
public class ReleaseNote {
    @Id @Column(name = "release_note_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "version")
    private String version;
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;
    @Column(name = "release_date")
    private Date releaseDate;
    @Column(name = "count")
    private int count; // 조회수
    @Column(name = "is_updated")
    private boolean isUpdated = false;
    @Column(name = "summary")
    private String summary;

    // User 와 mapping
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    // Project 와 mapping
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    // Comment 와 mapping
    @OneToMany(mappedBy = "releaseNote", fetch = FetchType.LAZY)
    @Column(name = "comment_id")
    private List<Comment> commentList;

    // note block 과 mapping
    @OneToMany(mappedBy = "releaseNote")
    @Column(name = "note_block_id")
    private List<NoteBlock> noteBlockList;

    @Builder
    public ReleaseNote(String version, Date lastModifiedDate, Date releaseDate, int count, Boolean isUpdated, String summary, User user, Project project, List<NoteBlock> noteBlockList, List<Comment> commentList) {
        this.version = version;
        this.lastModifiedDate = lastModifiedDate;
        this.releaseDate = releaseDate;
        this.count = count;
        this.isUpdated = isUpdated;
        this.summary = summary;

        //다른 entity와의 연결
        this.user = user;
        this.project = project;
        this.commentList = commentList;
        this.noteBlockList = noteBlockList;
    }

    public ResponseReleaseNoteContentDTO makeReleaseNoteContentDTO(){
        ResponseReleaseNoteContentDTO responseReleaseNoteContentDTO = new ResponseReleaseNoteContentDTO();
        ArrayList<String> noteBlockContentList = new ArrayList<>();

        for (NoteBlock noteblock:this.noteBlockList){
            noteBlockContentList.add(noteblock.getNoteBlockContext());
        }

        responseReleaseNoteContentDTO.setCreator(this.user.getName());
        responseReleaseNoteContentDTO.setVersion(this.version);
        responseReleaseNoteContentDTO.setLastModified(this.lastModifiedDate);
        responseReleaseNoteContentDTO.setReleaseDate(this.releaseDate);
        responseReleaseNoteContentDTO.setContent(noteBlockContentList.toString());
        responseReleaseNoteContentDTO.setCount(this.count);
        responseReleaseNoteContentDTO.setSummary(this.summary);
        //releaseNoteContentDTO.setLiked(this);

        return responseReleaseNoteContentDTO;
    }

    public ResponseReleaseNoteContentListDTO makeReleaseNoteContentListDTO(){
        ResponseReleaseNoteContentListDTO responseReleaseNoteContentListDTO = new ResponseReleaseNoteContentListDTO();

        responseReleaseNoteContentListDTO.setCreator(this.user.getName());
        responseReleaseNoteContentListDTO.setReleaseDate(this.releaseDate);
        responseReleaseNoteContentListDTO.setLastModified(this.lastModifiedDate);
        responseReleaseNoteContentListDTO.setVersion(this.version);
        responseReleaseNoteContentListDTO.setSummary(this.summary);

        return responseReleaseNoteContentListDTO;
    }
}
