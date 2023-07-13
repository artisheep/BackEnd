package com.swave.releasenotesharesystem.ReleaseNote.service;

import com.swave.releasenotesharesystem.ReleaseNote.requestDTO.RequestNewCommentDTO;
import com.swave.releasenotesharesystem.ReleaseNote.responseDTO.ResponseCommentContentListDTO;
import com.swave.releasenotesharesystem.Util.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;

public interface CommentService {

    public HttpResponse createComment(HttpServletRequest request, RequestNewCommentDTO requestNewCommentDTO);
    public ResponseCommentContentListDTO loadRecentComment(Long projectId);
}
