package com.swave.urnr.releasenote.repository;

import com.swave.urnr.releasenote.domain.Comment;
import com.swave.urnr.releasenote.domain.ReleaseNote;
import com.swave.urnr.releasenote.responsedto.CommentContentResponseDTO;
import groovy.lang.Tuple;

import java.util.List;

public interface CommentCustomRepository {
    List<CommentContentResponseDTO> findTop5RecentComment(Long projectId);

    }
