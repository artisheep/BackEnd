package com.swave.urnr.Project.service;

import com.swave.urnr.Project.requestDto.ProjectRequestDto;
import com.swave.urnr.Project.responseDto.loadAllProjectDto;
import com.swave.urnr.Project.responseDto.loadOneProjectDto;

import java.util.List;

public interface ProjectService {

    String createProject(ProjectRequestDto projectRequestDto);

    List<loadAllProjectDto> loadProjectList(Long userId);

    loadOneProjectDto loadProject(Long projectId);

    String updateUsers(ProjectRequestDto project);
}
