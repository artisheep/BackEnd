package com.swave.releasenotesharesystem.ReleaseNote.service;

import com.swave.releasenotesharesystem.ReleaseNote.RequestDTO.NewCommentDTO;
import com.swave.releasenotesharesystem.ReleaseNote.responseDTO.CommentContentListDTO;

public interface CommentService {

    public Long createComment(NewCommentDTO newCommentDTO);
    public CommentContentListDTO loadRecentComment(Long projectId);
}
