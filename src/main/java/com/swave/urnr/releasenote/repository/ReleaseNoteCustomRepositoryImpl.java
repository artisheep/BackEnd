package com.swave.urnr.releasenote.repository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import static com.swave.urnr.releasenote.domain.QReleaseNote.releaseNote;


public class ReleaseNoteCustomRepositoryImpl implements ReleaseNoteCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public ReleaseNoteCustomRepositoryImpl(EntityManager em){
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }


    @Override
    public String latestReleseNote(Long userId, Long projectId) {
        return jpaQueryFactory
                .select(releaseNote.version)
                .from(releaseNote)
                .where(releaseNote.user.id.eq(userId).and(releaseNote.project.id.eq(projectId)))
                .orderBy(releaseNote.id.desc())
                .fetchFirst();
    }
}
