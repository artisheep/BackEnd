package com.swave.urnr.releasenote.repository;

public interface ReleaseNoteCustomRepository {

    String latestReleseNote(Long userId,Long projectId);
}
