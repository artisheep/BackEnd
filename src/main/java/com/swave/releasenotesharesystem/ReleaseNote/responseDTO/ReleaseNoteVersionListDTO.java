package com.swave.releasenotesharesystem.ReleaseNote.responseDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class ReleaseNoteVersionListDTO {
    private ArrayList<String> versionList = new ArrayList<>();
}
