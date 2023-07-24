package com.swave.urnr.releasenote.repository;

import com.swave.urnr.releasenote.domain.NoteBlock;
import com.swave.urnr.releasenote.responsedto.ReleaseNoteLabelCountResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteBlockRepository extends JpaRepository<NoteBlock, Long> , NoteBlockCustomRepository {
    List<NoteBlock> findByReleaseNote_Id(Long Id);
}
