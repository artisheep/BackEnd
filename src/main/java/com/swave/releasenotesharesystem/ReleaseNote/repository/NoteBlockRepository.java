package com.swave.releasenotesharesystem.ReleaseNote.repository;

import com.swave.releasenotesharesystem.ReleaseNote.domain.NoteBlock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteBlockRepository extends JpaRepository<NoteBlock, Long> {
}
