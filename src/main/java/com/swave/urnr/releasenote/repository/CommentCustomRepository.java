package com.swave.urnr.releasenote.repository;

import com.swave.urnr.releasenote.domain.Comment;
import com.swave.urnr.releasenote.domain.ReleaseNote;

import java.util.List;

public interface CommentCustomRepository {
    List<Comment> findTop5RecentComment(Long projectId);

    }
