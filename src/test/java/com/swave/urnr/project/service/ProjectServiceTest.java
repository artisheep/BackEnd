package com.swave.urnr.project.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swave.urnr.project.repository.ProjectRepository;
import com.swave.urnr.user.domain.User;
import com.swave.urnr.user.repository.UserInProjectRepository;
import com.swave.urnr.user.repository.UserRepository;
import com.swave.urnr.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@RequiredArgsConstructor
class ProjectServiceTest {

    private final ProjectService projectService;

    private final UserService userService;

    @MockBean
    ProjectRepository projectRepository;

    @MockBean
    UserInProjectRepository userInProjectRepository;

    @MockBean
    UserRepository userRepository;

    @Autowired
    ProjectServiceImpl projectServiceImpl;

    MockHttpServletRequest servletRequest;

    @Mock
    JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createProject() {
    }

    @Test
    void loadProjectList() {
    }

    @Test
    void loadProject() {
    }

    @Test
    void updateProject() {
    }

    @Test
    void deleteProject() {
    }
}