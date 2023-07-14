package com.swave.releasenotesharesystem.ReleaseNote.service;

import com.swave.releasenotesharesystem.ReleaseNote.requestDTO.RequestNewReleaseNoteDTO;
import com.swave.releasenotesharesystem.ReleaseNote.requestDTO.RequestUpdateReleaseNoteDTO;
import com.swave.releasenotesharesystem.ReleaseNote.responseDTO.ResponseReleaseNoteContentDTO;
import com.swave.releasenotesharesystem.ReleaseNote.responseDTO.ResponseReleaseNoteContentListDTO;
import com.swave.releasenotesharesystem.ReleaseNote.responseDTO.ResponseReleaseNoteVersionListDTO;
import com.swave.releasenotesharesystem.ReleaseNote.responseDTO.ResponseVersionListDTO;
import com.swave.releasenotesharesystem.Util.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public interface  ReleaseNoteService {

    public HttpResponse createReleaseNote(HttpServletRequest request, Long projectId, RequestNewReleaseNoteDTO requestNewReleaseNoteDTO);
    public HttpResponse updateReleaseNote(HttpServletRequest request, RequestUpdateReleaseNoteDTO requestUpdateReleaseNoteDTO);
    public ArrayList<ResponseReleaseNoteContentListDTO> loadReleaseNoteList(Long projectId);
    public ResponseReleaseNoteContentDTO loadReleaseNote(Long releaseNoteId);
    public ArrayList<ResponseVersionListDTO> loadProjectVersionList(HttpServletRequest request);
}
