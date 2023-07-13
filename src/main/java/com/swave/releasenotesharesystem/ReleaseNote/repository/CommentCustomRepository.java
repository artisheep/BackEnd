package com.swave.releasenotesharesystem.ReleaseNote.repository;

import com.swave.releasenotesharesystem.ReleaseNote.domain.Comment;

import java.util.List;

public interface CommentCustomRepository {
    List<Comment> findTop5RecentComment(Long projectId);
}
