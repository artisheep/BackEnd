package com.swave.urnr.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swave.urnr.user.domain.User;
import com.swave.urnr.user.exception.UserNotFoundException;
import com.swave.urnr.user.repository.UserRepository;
import com.swave.urnr.user.requestdto.*;
import com.swave.urnr.user.responsedto.UserResponseDTO;
import com.swave.urnr.util.common.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Slf4j
@RequiredArgsConstructor
class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    @DisplayName("계정 생성 테스트")
    void createAccountByEmail() {

        UserRegisterRequestDTO userRegisterRequestDTO = UserRegisterRequestDTO.builder()
                .email("corgiwalke@gmail.com")
                .name("전강훈")
                .password("1q2w3e4r")
                .build();
        ResponseEntity<ResponseDTO> result = userService.createAccountByEmail(userRegisterRequestDTO);


        assertEquals( result.getStatusCode().value() , 201);
        assertEquals( result.getBody().getData(),"User created");
    }

    @Test
    @DisplayName("소속 수정 테스트")
    void initDepartment() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        User user = userRepository.findByEmail("corgiwalke@gmail.com").get();
        request.setAttribute("id", user.getId());

        UserDepartmentRequestDTO userDepartmentRequestDTO = UserDepartmentRequestDTO.builder()
                .department("test")
                .build();

        ResponseEntity<ResponseDTO> result = userService.initDepartment(request,userDepartmentRequestDTO);

        ResponseDTO responseDTO = ResponseDTO.builder()
                .status(200)
                .data("test")
                .build();

        assertEquals( result.getStatusCode().value() , 200);
        assertEquals( result.getBody(),responseDTO);
    }

    @Test
    @DisplayName("인증코드 반환 테스트")
    void getValidationCode() {

        UserValidateEmailDTO request = UserValidateEmailDTO.builder()
                .email("artisheep@naver.com")
                .build();

        ResponseEntity<String> result = userService.getValidationCode(request);

        assertEquals( result.getStatusCode().value() , 200);

    }

    @Test
    @DisplayName("유저 정보 반환 테스트")
    void getUser() throws UserNotFoundException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        User user = userRepository.findByEmail("corgiwalke@gmail.com").get();
        request.setAttribute("id", user.getId());
        UserResponseDTO resultExcepted = UserResponseDTO.builder()
                .department(user.getDepartment())
                .isDeleted(user.isDeleted())
                .username(user.getUsername())
                .id(user.getId())
                .email(user.getEmail())
                .provider(user.getProvider())
                .password(user.getPassword())
                .build();


        UserResponseDTO result = userService.getUser(request);

        assertEquals( resultExcepted , result);


    }

    @Test
    @DisplayName("현재 유저 정보 반환 테스트")
    void getCurrentUserInformation() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        User user = userRepository.findByEmail("corgiwalke@gmail.com").get();
        request.setAttribute("id", user.getId());
        request.addHeader("Authorization","test");
        ResponseEntity<UserResponseDTO> useTr = userService.getCurrentUserInformation(request);
        assertEquals( useTr.getStatusCode().value(), 200);
    }

    @Test
    @DisplayName("토큰 확인 테스트")
    void checkInvalidToken() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setAttribute("id", (long) 1);
        request.addHeader("Authorization","test");
        userService.checkInvalidToken(request);

    }

    @Test
    @DisplayName("유저 정보 리스트 반환 테스트")
    void getUserInformationList() {
        userService.getUserInformationList();
    }

    @Test
    @DisplayName("로그인 테스트")
    void getTokenByLogin() {
        UserLoginServerRequestDTO userLoginServerRequestDTO = UserLoginServerRequestDTO.builder()
                .email("corgiwalke@gmail.com")
                .password("1q2w3e4r")
                .build();

        ResponseEntity<String> result =  userService.getTokenByLogin(userLoginServerRequestDTO);
        assertEquals( result.getStatusCode().value(), 200);
    }

    @Test
    void getTokenByOauth() {
    }

    @Test
    @DisplayName("유저 정보 업데이트 테스트")
    void updateUser() {


        MockHttpServletRequest request = new MockHttpServletRequest();
        User user = userRepository.findByEmail("corgiwalke@gmail.com").get();
        request.setAttribute("id", user.getId());


        UserUpdateAccountRequestDTO userUpdateAccountRequestDTO = UserUpdateAccountRequestDTO.builder()
                .password("testPW")
                .name("전강훈")
                .department("test")
                .build();

        ResponseEntity<String> updateUser = userService.updateUser(request, userUpdateAccountRequestDTO);


        assertEquals( updateUser.getStatusCode().value(), 204);

    }

    @Test
    @DisplayName("임시 비밀번호 발급 테스트")
    void setTemporaryPassword() {

        UserValidateEmailDTO userValidateEmailDTO = UserValidateEmailDTO.builder()
                .email("artisheep@naver.com")
                .build();
        ResponseEntity<String> result = userService.setTemporaryPassword(userValidateEmailDTO);

        assertEquals( result.getStatusCode().value(), 200);

    }

    @Test
    @DisplayName("유저 삭제 테스트")
    void deleteUser() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        User user = userRepository.findByEmail("corgiwalke@gmail.com").get();
        request.setAttribute("id", user.getId());

        ResponseEntity<String> result = userService.deleteUser(request);

        assertEquals( result.getStatusCode().value(), 200);
    }
}