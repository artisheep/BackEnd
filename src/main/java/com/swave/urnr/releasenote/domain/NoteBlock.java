package com.swave.urnr.releasenote.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE comment SET is_deleted = true WHERE comment_id = ?")
@NoArgsConstructor
public class NoteBlock {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_block_id")
    private Long id;

    @Column(columnDefinition = "TEXT", name = "note_block_context")
    private String noteBlockContext;

    /*@Column(name = "tag")
    private String tag;

    @Column(name = "label")
    private String label;*/

    @ManyToOne
    @JoinColumn(name = "release_note_id")
    private ReleaseNote releaseNote;

    @Column(name = "is_deleted")
    private boolean isDeleted = Boolean.FALSE;

    @Builder
    public NoteBlock(String noteBlockContext, ReleaseNote releaseNote) {
        this.noteBlockContext = noteBlockContext;

        //다른 entity와의 연결
        this.releaseNote = releaseNote;
    }
}
