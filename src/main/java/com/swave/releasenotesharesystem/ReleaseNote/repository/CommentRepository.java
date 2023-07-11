package com.swave.releasenotesharesystem.ReleaseNote.repository;

import com.swave.releasenotesharesystem.ReleaseNote.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
