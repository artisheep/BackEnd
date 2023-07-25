package com.swave.urnr.releasenote.service;

import com.swave.urnr.releasenote.repository.NoteBlockRepository;
import com.swave.urnr.releasenote.responsedto.ReleaseNoteLabelContentResponseDTO;
import com.swave.urnr.releasenote.responsedto.ReleaseNoteLabelCountResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableTransactionManagement
public class NoteBlockServiceImpl implements NoteBlockService {
    private final NoteBlockRepository noteBlockRepository;

    @Override
    public List<ReleaseNoteLabelCountResponseDTO> countLabel(Long projectId){
        return noteBlockRepository.countByLabel(projectId);
    }

    @Override
    public List<ReleaseNoteLabelContentResponseDTO> filterByLabel(Long projectId){
        List<ReleaseNoteLabelContentResponseDTO> releaseNoteLabelContentResponseDTOList = noteBlockRepository.filterByLabel(projectId);

        for (int i = 0 ; i < releaseNoteLabelContentResponseDTOList.toArray().length ; i++){
            String context = String.join("",releaseNoteLabelContentResponseDTOList.get(i).getContext());
            releaseNoteLabelContentResponseDTOList.get(i).setContext(new ArrayList<>(List.of(context)));
        }

        return releaseNoteLabelContentResponseDTOList;
    }
}
