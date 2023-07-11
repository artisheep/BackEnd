package com.swave.releasenotesharesystem.ReleaseNote.repository;

import com.swave.releasenotesharesystem.ReleaseNote.domain.NoteBlock;
import com.swave.releasenotesharesystem.ReleaseNote.domain.ReleaseNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface NoteBlockRepository extends JpaRepository<NoteBlock, Long> {
    List<NoteBlock> findByReleaseNote_Id(Long Id);
}
