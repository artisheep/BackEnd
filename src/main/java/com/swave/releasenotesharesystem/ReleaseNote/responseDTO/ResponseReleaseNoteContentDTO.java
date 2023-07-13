package com.swave.releasenotesharesystem.ReleaseNote.responseDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@NoArgsConstructor
public class ResponseReleaseNoteContentDTO {

    private String creator;
    private String version;
    private Date lastModified;
    private Date releaseDate;
    private String summary;
    private String content;
    private ArrayList<ResponseCommentContentDTO> comment;
    private int count;
    private int liked;
}
