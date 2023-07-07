package com.swave.releasenotesharesystem.Project.service;

import com.swave.releasenotesharesystem.Project.requestDto.ProjectRequestDto;
import com.swave.releasenotesharesystem.Project.responseDto.loadAllProjectDto;
import com.swave.releasenotesharesystem.Project.responseDto.loadOneProjectDto;

import java.util.List;

public interface ProjectService {

    public String createProject(ProjectRequestDto projectRequestDto);

    List<loadAllProjectDto> loadProjectList(Long userId);

    loadOneProjectDto loadProject(Long projectId);
}

