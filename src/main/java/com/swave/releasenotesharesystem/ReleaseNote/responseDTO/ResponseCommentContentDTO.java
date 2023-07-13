package com.swave.releasenotesharesystem.ReleaseNote.responseDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ResponseCommentContentDTO {
    private String name;
    private String context;
    private Date lastModifiedDate;
}
