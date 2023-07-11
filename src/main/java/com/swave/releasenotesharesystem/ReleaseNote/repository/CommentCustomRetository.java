package com.swave.releasenotesharesystem.ReleaseNote.repository;

import com.swave.releasenotesharesystem.ReleaseNote.domain.Comment;

import java.util.ArrayList;

public interface CommentCustomRetository {
    ArrayList<Comment> findTop5RecentComment(Long projectId);
}
