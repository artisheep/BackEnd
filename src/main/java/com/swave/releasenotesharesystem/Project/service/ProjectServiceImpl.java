package com.swave.releasenotesharesystem.Project.service;

import com.swave.releasenotesharesystem.Project.domain.Project;
import com.swave.releasenotesharesystem.Project.responseDto.loadAllProjectDto;
import com.swave.releasenotesharesystem.Project.responseDto.loadOneProjectDto;
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

import java.util.ArrayList;
import java.util.List;

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
        /*Project project = new Project();
        project.setId(projectRequestDto.getId());
        project.setName(projectRequestDto.getName());
        project.setDescription(projectRequestDto.getDescription());
        project.setCreateDate(projectRequestDto.getCreateDate());*/
        Project project = Project.builder()
                .name(projectRequestDto.getName())
                .description(projectRequestDto.getDescription())
                .createDate(projectRequestDto.getCreateDate())
                .build();
        User user = userRepository.findById(projectRequestDto.getUserId()).orElse(null);
        //UserInProject.setUserInProject(new ArrayList<>());
        //builder를 이용한 userInProject 생성
        UserInProject userInProject = UserInProject.builder()
                .role(UserRole.Manager)
                .user(user)
                .project(project)
                .build();
        //리스트형식에서 삽입을 위한 list생성 빈칸생성은 아닌듯
        ArrayList<UserInProject> list = new ArrayList<>(user.getUserInProjectList());
        list.add(userInProject);
        user.setUserInProjectList(list);
        list = new ArrayList<>();
        list.add(userInProject);
        project.setUserInProjectList(list);

        //저장하고
        Project saveProject = projectRepository.save(project);
        User saveUser = userRepository.save(user);
        UserInProject saveUserInProject = userInProjectRepository.save(userInProject);
        //flush로 반영한다.
        userRepository.flush();
        projectRepository.flush();
        userInProjectRepository.flush();
        return project.toString();
    }

    @Override
    public List<loadAllProjectDto> loadProjectList(Long userId) {
        //여기 DTO 왜 못쓰냐
        List<loadAllProjectDto> projectList = new ArrayList<>();
        List<UserInProject> userInProjectList = userInProjectRepository.findByUser_Id(userId);
        for(UserInProject userInProject: userInProjectList){
            Project project = projectRepository.findById(userInProject.getProject().getId())
                    .orElseThrow(NoSuchFieldError::new);
            projectList.add(new loadAllProjectDto(project.getId(),userInProject.getRole(),project.getName(),project.getDescription(),project.getCreateDate()));
        }
        /*
        List<ProjectRequestDto> loadAll = new ArrayList<>();
        for (Project project : projectRequestDtos){
            System.out.println(project.getName());
            loadAll.add(new ProjectRequestDto(project.getId(),project.getName(),project.getDescription(),project.getCreateDate()));
        }*/
        return projectList;
    }

    @Override
    public loadOneProjectDto loadProject(Long projectId) {
        loadOneProjectDto getproject = new loadOneProjectDto();
        Project project = projectRepository.findById(projectId).get();
        getproject.setId(project.getId());
        getproject.setName(project.getName());
        getproject.setDescription(project.getDescription());
        getproject.setCreateDate(project.getCreateDate());
        /*
        List<ProjectRequestDto> loadAll = new ArrayList<>();
        for (Project project : projectRequestDtos){
            System.out.println(project.getName());
            loadAll.add(new ProjectRequestDto(project.getId(),project.getName(),project.getDescription(),project.getCreateDate()));
        }*/
        return getproject;
    }
}
