package com.swave.releasenotesharesystem.ReleaseNote.requestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class RequestUpdateReleaseNoteDTO {
    private Long releaseNoteId;
    private String version;
    private Date releaseDate;
    private String content;
}
