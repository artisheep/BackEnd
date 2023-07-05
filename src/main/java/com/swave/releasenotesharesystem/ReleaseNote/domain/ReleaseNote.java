package com.swave.releasenotesharesystem.ReleaseNote.domain;

import com.swave.releasenotesharesystem.Project.domain.Project;
import jdk.jfr.Timestamp;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
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
    public ReleaseNote(String version, Date lastModifiedDate, Date releaseDate, int count) {
        this.version = version;
        this.lastModifiedDate = lastModifiedDate;
        this.releaseDate = releaseDate;
        this.count = count;
    }
}
