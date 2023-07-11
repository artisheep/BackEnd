package com.swave.releasenotesharesystem.ReleaseNote.service;

import com.swave.releasenotesharesystem.ReleaseNote.RequestDTO.NewReleaseNoteDTO;
import com.swave.releasenotesharesystem.ReleaseNote.RequestDTO.UpdateReleaseNoteDTO;
import com.swave.releasenotesharesystem.ReleaseNote.responseDTO.ReleaseNoteContentDTO;
import com.swave.releasenotesharesystem.ReleaseNote.responseDTO.ReleaseNoteContentListDTO;
import com.swave.releasenotesharesystem.ReleaseNote.responseDTO.ReleaseNoteVersionListDTO;

import java.util.ArrayList;

public interface  ReleaseNoteService {

    public Long createReleaseNote(Long projectId, NewReleaseNoteDTO newReleaseNoteDTO);
    public Long updateReleaseNote(UpdateReleaseNoteDTO updateReleaseNoteDTO);
    public ArrayList<ReleaseNoteContentListDTO> loadReleaseNoteList(Long projectId);
    public ReleaseNoteContentDTO loadReleaseNote(Long releaseNoteId);
    public ReleaseNoteVersionListDTO loadVersionList(Long projectId);
}
