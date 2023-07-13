package com.swave.releasenotesharesystem.ReleaseNote.responseDTO;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class ResponseReleaseNoteVersionListDTO {
    private ArrayList<String> versionList;
}
