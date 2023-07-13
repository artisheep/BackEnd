package com.swave.releasenotesharesystem.ReleaseNote.requestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestNewCommentDTO {
    private Long releaseNoteId;
    private String content;
}
