package com.swave.releasenotesharesystem.Project.controller;

import com.swave.releasenotesharesystem.Project.domain.Project;
import com.swave.releasenotesharesystem.Project.requestDto.ProjectRequestDto;
import com.swave.releasenotesharesystem.Project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    //0706 : create 생성
    //프로젝트를 생성할 때 생성자가 userinproject에 들어가야해
    @PostMapping("/create")
    public String createProject(@RequestBody ProjectRequestDto project){
        projectService.createProject(project);
        return "Success";

    }
}
