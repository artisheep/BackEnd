package com.swave.releasenotesharesystem.ReleaseNote.controller;

import com.swave.releasenotesharesystem.ReleaseNote.requestDTO.RequestNewCommentDTO;
import com.swave.releasenotesharesystem.ReleaseNote.requestDTO.RequestNewReleaseNoteDTO;
import com.swave.releasenotesharesystem.ReleaseNote.requestDTO.RequestUpdateReleaseNoteDTO;
import com.swave.releasenotesharesystem.ReleaseNote.responseDTO.ResponseCommentContentListDTO;
import com.swave.releasenotesharesystem.ReleaseNote.responseDTO.ResponseReleaseNoteContentDTO;
import com.swave.releasenotesharesystem.ReleaseNote.responseDTO.ResponseReleaseNoteContentListDTO;
import com.swave.releasenotesharesystem.ReleaseNote.responseDTO.ResponseReleaseNoteVersionListDTO;
import com.swave.releasenotesharesystem.ReleaseNote.service.ReleaseNoteAllServiceImpl;
import com.swave.releasenotesharesystem.Util.http.HttpResponse;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class ReleaseNoteController {
    private final ReleaseNoteAllServiceImpl releaseNoteAllService;

    @PostMapping("/api/project/{projectId}/release/create")
    public HttpResponse createReleaseNote(HttpServletRequest request, @PathVariable Long projectId, @RequestBody RequestNewReleaseNoteDTO requestNewReleaseNoteDTO) {
        return releaseNoteAllService.createReleaseNote(request, projectId, requestNewReleaseNoteDTO);
    }

    @PostMapping("/api/project/release/update")
    public HttpResponse UpdateReleaseNote(HttpServletRequest request, @RequestBody RequestUpdateReleaseNoteDTO requestUpdateReleaseNoteDTO) {
        return releaseNoteAllService.updateReleaseNote(request, requestUpdateReleaseNoteDTO);
    }

    @GetMapping("/api/project/release/load/{releaseNoteId}")
    public ResponseReleaseNoteContentDTO loadReleaseNote(@PathVariable Long releaseNoteId) {
        return releaseNoteAllService.loadReleaseNote(releaseNoteId);
    }

    @GetMapping("/api/project/{projectId}/release/load_all")
    public ArrayList<ResponseReleaseNoteContentListDTO> loadReleaseNoteList(@PathVariable Long projectId) {
        return releaseNoteAllService.loadReleaseNoteList(projectId);
    }

    @GetMapping("/api/project/{projectId}/release/version_list")
    public ResponseReleaseNoteVersionListDTO loadVersionList(@PathVariable Long projectId) {
        return releaseNoteAllService.loadVersionList(projectId);
    }

    @PostMapping("/api/project/release/comment/create")
    public HttpResponse createComment(HttpServletRequest request, @RequestBody RequestNewCommentDTO requestNewCommentDTO){
        return releaseNoteAllService.createComment(request, requestNewCommentDTO);
    }

    @GetMapping("/api/project/{projectId}/release/comment/load_recent")
    public ResponseCommentContentListDTO loadRecentComment(@PathVariable Long projectId){
        return releaseNoteAllService.loadRecentComment(projectId);
    }


}
