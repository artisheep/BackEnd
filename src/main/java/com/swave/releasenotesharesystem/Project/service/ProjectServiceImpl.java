package com.swave.releasenotesharesystem.Project.service;

import com.swave.releasenotesharesystem.Project.domain.Project;
import com.swave.releasenotesharesystem.Util.type.UserRole;
import com.swave.releasenotesharesystem.Project.repository.ProjectRepository;
import com.swave.releasenotesharesystem.Project.requestDto.ProjectRequestDto;

import com.swave.releasenotesharesystem.User.domain.User;
;
import com.swave.releasenotesharesystem.User.repository.UserInProjectRepository;
import com.swave.releasenotesharesystem.User.domain.UserInProject;
import com.swave.releasenotesharesystem.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserInProjectRepository userInProjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String createProject(ProjectRequestDto projectRequestDto) {
        Project project = new Project();
        project.setId(projectRequestDto.getId());
        project.setName(projectRequestDto.getName());
        project.setDescription(projectRequestDto.getDescription());
        project.setCreateDate(projectRequestDto.getCreateDate());
        User user = userRepository.findById(projectRequestDto.getUserId()).orElse(null);
        //UserInProject.setUserInProject(new ArrayList<>());
        UserInProject userInProject = UserInProject.builder()
                .role(UserRole.Manager)
                .user(user)
                .project(project)
                .build();
        ArrayList<UserInProject> list = new ArrayList<>(user.getUserInProjectList());
        list.add(userInProject);
        user.setUserInProjectList(list);
        list = new ArrayList<>();
        list.add(userInProject);
        project.setUserInProjectList(list);

        Project saveProject = projectRepository.save(project);
        User saveUser = userRepository.save(user);
        UserInProject saveUserInProject = userInProjectRepository.save(userInProject);
        userRepository.flush();
        projectRepository.flush();
        userInProjectRepository.flush();



        return null;
    }
}
