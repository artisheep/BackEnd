package com.swave.urnr.releasenote.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swave.urnr.releasenote.domain.Comment;
import static com.swave.urnr.releasenote.domain.QComment.comment;
import static com.swave.urnr.releasenote.domain.QReleaseNote.releaseNote;

import java.util.List;

public class CommentCustomRepositoryImpl implements CommentCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public CommentCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Comment> findTop5RecentComment(Long projectId){

        return jpaQueryFactory
                .selectFrom(comment)
                .join(releaseNote).on(releaseNote.id.eq(comment.releaseNote.id))
                .where(releaseNote.project.id.eq(projectId))
                .orderBy(comment.lastModifiedDate.desc())
                .limit(5)
                .fetch();
    }
}
