package com.swave.urnr.project.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swave.urnr.project.domain.Project;
import com.swave.urnr.project.repository.ProjectRepository;
import com.swave.urnr.project.requestdto.ProjectCreateRequestDTO;
import com.swave.urnr.user.domain.User;
import com.swave.urnr.user.exception.UserNotFoundException;
import com.swave.urnr.user.repository.UserInProjectRepository;
import com.swave.urnr.user.repository.UserRepository;
import com.swave.urnr.user.requestdto.UserLoginServerRequestDTO;
import com.swave.urnr.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProjectServiceTest {

    @Autowired
    ProjectService projectService;

    @Autowired
    UserService userService;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserInProjectRepository userInProjectRepository;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() throws UserNotFoundException {
        User user = User.builder()
                .name("kang")
                .email("korea@naver.com")
                .provider("server")
                .password("1236")
                .build();
        userRepository.save(user);

        UserLoginServerRequestDTO userLoginServerRequestDTO = new UserLoginServerRequestDTO();
        userLoginServerRequestDTO.setEmail(user.getEmail());
        userLoginServerRequestDTO.setPassword(user.getPassword());
        String token = userService.getTokenByLogin(userLoginServerRequestDTO);

        User user2 = User.builder()
                .name("kim")
                .email("korea2@naver.com")
                .provider("server")
                .password("1232")
                .build();


        User user3 = User.builder()
                .name("jin")
                .email("korea3@naver.com")
                .provider("server")
                .password("1233")
                .build();
        User user4 = User.builder()
                .name("john")
                .email("korea4@naver.com")
                .provider("server")
                .password("1234")
                .build();
        User user5 = User.builder()
                .name("user5")
                .email("korea5@naver.com")
                .provider("server")
                .password("1235")
                .build();

        userRepository.save(user2);

        userRepository.save(user3);

        userRepository.save(user4);

        userRepository.save(user5);

    }

    @Test
    void createProject() {
        //테스트 코드는 무언가를 받을 필요가 없나?
        System.out.println("시작");
        ProjectCreateRequestDTO projectCreateRequestDTO = ProjectCreateRequestDTO.builder()
                .projectName("SwaveForm")
                .description("굳잡")
                .build();

        Project project = Project.builder()
                .name(projectCreateRequestDTO.getProjectName())
                .description(projectCreateRequestDTO.getDescription())
                .createDate(new Date())
                .build();


        assertEquals(project.getName(),"SwaveForm");
        assertEquals(project.getDescription(),"굳잡");
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