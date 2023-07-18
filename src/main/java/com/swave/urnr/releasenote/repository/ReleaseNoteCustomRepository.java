package com.swave.urnr.releasenote.repository;

import com.swave.urnr.releasenote.domain.ReleaseNote;
import com.swave.urnr.user.domain.UserInProject;

public interface ReleaseNoteCustomRepository {

    ReleaseNote findMostRecentReleaseNote(Long userId);

    String latestReleseNote(Long id, Long id1);
    UserInProject findUserInProjectByUserIdAndReleaseNoteId(Long userId, Long releaseNote_id);
}
