package com.swave.releasenotesharesystem.ReleaseNote.controller;

import com.swave.releasenotesharesystem.ReleaseNote.requestDTO.RequestNewCommentDTO;
import com.swave.releasenotesharesystem.ReleaseNote.requestDTO.RequestNewReleaseNoteDTO;
import com.swave.releasenotesharesystem.ReleaseNote.requestDTO.RequestUpdateReleaseNoteDTO;
import com.swave.releasenotesharesystem.ReleaseNote.responseDTO.*;
import com.swave.releasenotesharesystem.ReleaseNote.service.ReleaseNoteAllServiceImpl;
import com.swave.releasenotesharesystem.Util.http.HttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import lombok.RequiredArgsConstructor;

@Api(tags = "ReleaseNoteController")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class ReleaseNoteController {
    private final ReleaseNoteAllServiceImpl releaseNoteAllService;

    @Operation(summary="릴리즈 노트 생성", description="projectId의 프로젝트에 릴리즈 노트를 생성합니다. 유저 정보는 JWT로부터 가져옵니다.")
    @PostMapping("/api/project/{projectId}/release/create")
    public HttpResponse createReleaseNote(HttpServletRequest request, @PathVariable Long projectId, @RequestBody RequestNewReleaseNoteDTO requestNewReleaseNoteDTO) {
        return releaseNoteAllService.createReleaseNote(request, projectId, requestNewReleaseNoteDTO);
    }

    @Operation(summary="릴리즈 노트 수정", description="릴리즈 노트를 수정합니다. 유저 정보는 JWT로부터 가져옵니다.")
    @PostMapping("/api/project/release/update")
    public HttpResponse UpdateReleaseNote(HttpServletRequest request, @RequestBody RequestUpdateReleaseNoteDTO requestUpdateReleaseNoteDTO) {
        return releaseNoteAllService.updateReleaseNote(request, requestUpdateReleaseNoteDTO);
    }

    @Operation(summary="릴리즈 노트 한개 받기", description="releaseNoteId의 릴리즈 노트를 읽어옵니다. 댓글이 포함됩니다.")
    @GetMapping("/api/project/release/load/{releaseNoteId}")
    public ResponseReleaseNoteContentDTO loadReleaseNote(@PathVariable Long releaseNoteId) {
        return releaseNoteAllService.loadReleaseNote(releaseNoteId);
    }

    @Operation(summary="릴리즈 노트 리스트 받기", description="projectId의 프로젝트의 모든 릴리즈 노트를 읽어옵니다. (*GPT 요약본으로)")
    @GetMapping("/api/project/{projectId}/release/load_all")
    public ArrayList<ResponseReleaseNoteContentListDTO> loadReleaseNoteList(@PathVariable Long projectId) {
        return releaseNoteAllService.loadReleaseNoteList(projectId);
    }

    @Operation(summary="릴리즈 노트 버전 리스트 받기", description="내가 소속된 모든 프로젝트의 릴리즈 노트 버전정보를 읽어옵니다. 유저 정보는 JWT로부터 가져옵니다.")
    @GetMapping("/api/project/release/version_list")
    public ArrayList<ResponseVersionListDTO> loadProjectVersionList(HttpServletRequest request) {
        return releaseNoteAllService.loadProjectVersionList(request);
    }

    @Operation(summary = "댓글 하나 쓰기", description = "releaseNoteId의 릴리즈 노트에 댓글을 생성합니다. 유저 정보는 JWT로부터 가져옵니다.")
    @PostMapping("/api/project/release/{releaseNoteId}/comment/create")
    public HttpResponse createComment(HttpServletRequest request, @PathVariable Long releaseNoteId , @RequestBody RequestNewCommentDTO requestNewCommentDTO){
        return releaseNoteAllService.createComment(request, releaseNoteId ,requestNewCommentDTO);
    }

    @Operation(summary="최근 댓글 5개 읽기", description="projectId의 프로젝트에서 최근에 달린 댓글 5개를 보여줍니다.")
    @GetMapping("/api/project/{projectId}/release/comment/load_recent")
    public ResponseCommentContentListDTO loadRecentComment(@PathVariable Long projectId){
        return releaseNoteAllService.loadRecentComment(projectId);
    }


}
