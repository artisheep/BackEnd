package com.swave.releasenotesharesystem.ReleaseNote.repository;

import com.swave.releasenotesharesystem.ReleaseNote.domain.Comment;
import com.swave.releasenotesharesystem.ReleaseNote.domain.NoteBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> , CommentCustomRetository{
    ArrayList<Comment> findByReleaseNote_Id(Long Id);
    ArrayList<Comment> findAllByOrderByLastModifiedDateDesc();
}
