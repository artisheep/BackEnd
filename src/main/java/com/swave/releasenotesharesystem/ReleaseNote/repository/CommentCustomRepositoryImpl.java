package com.swave.releasenotesharesystem.ReleaseNote.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swave.releasenotesharesystem.ReleaseNote.domain.Comment;
import com.swave.releasenotesharesystem.ReleaseNote.domain.QComment;
import com.swave.releasenotesharesystem.ReleaseNote.domain.QReleaseNote;

import java.util.ArrayList;

public class CommentCustomRepositoryImpl implements CommentCustomRetository{
    private final JPAQueryFactory jpaQueryFactory;

    public CommentCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public ArrayList<Comment> findTop5RecentComment(Long projectId){
        ArrayList<Comment> comments = new ArrayList<>();

        //todo : querydsl

        return comments;
    }
}
