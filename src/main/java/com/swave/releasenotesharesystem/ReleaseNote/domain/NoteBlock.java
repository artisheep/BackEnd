package com.swave.releasenotesharesystem.ReleaseNote.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class NoteBlock {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_block_id")
    private Long id;

    @Column(columnDefinition = "TEXT", name = "note_block_context")
    private String note_block_context;

    @ManyToOne
    @JoinColumn(name = "release_note_id")
    private ReleaseNote releaseNote;
    //todo : label 필드 생성

    @Builder
    public NoteBlock(String note_block_context, ReleaseNote releaseNote) {
        this.note_block_context = note_block_context;
        this.releaseNote = releaseNote;
    }
}
