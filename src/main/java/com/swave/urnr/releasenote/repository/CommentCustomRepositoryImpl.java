package com.swave.urnr.releasenote.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swave.urnr.releasenote.responsedto.CommentContentResponseDTO;
import io.swagger.annotations.ApiModelProperty;

import static com.swave.urnr.releasenote.domain.QComment.comment;
import static com.swave.urnr.releasenote.domain.QReleaseNote.releaseNote;

import java.util.Date;
import java.util.List;

public class CommentCustomRepositoryImpl implements CommentCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public CommentCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<CommentContentResponseDTO> findTop5RecentComment(Long projectId){
        return jpaQueryFactory
                .select(Projections.fields(CommentContentResponseDTO.class,
                                comment.commentContext.as("context"),
                                comment.lastModifiedDate.as("lastModifiedDate"),
                                comment.user.username.as("name"),
                                releaseNote.version.as("version"),
                                releaseNote.id.as("releaseNoteId")))
                .from(comment)
                .join(releaseNote).on(releaseNote.id.eq(comment.releaseNote.id))
                .where(releaseNote.project.id.eq(projectId))
                .orderBy(comment.lastModifiedDate.desc())
                .limit(5)
                .fetch();
    }


}
