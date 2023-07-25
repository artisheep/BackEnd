package com.swave.urnr.releasenote.service;

import com.swave.urnr.releasenote.responsedto.ReleaseNoteLabelContentResponseDTO;
import com.swave.urnr.releasenote.responsedto.ReleaseNoteLabelCountResponseDTO;

import java.util.List;

public interface NoteBlockService {
    public List<ReleaseNoteLabelCountResponseDTO> countLabel(Long projectId);
    public List<ReleaseNoteLabelContentResponseDTO> filterByLabel(Long projectId);
}
