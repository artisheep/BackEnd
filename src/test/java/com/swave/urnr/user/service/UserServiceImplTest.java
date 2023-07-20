package com.swave.urnr.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swave.urnr.user.repository.UserRepository;
import com.swave.urnr.user.requestdto.UserRegisterRequestDTO;
import com.swave.urnr.util.common.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

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


    @Test
    @DisplayName("계정 생성")
    void createAccountByEmail() {
        String requestBody = "{ \"id\": \"0\"}";

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
    void initDepartment() {
    }

    @Test
    void getValidationCode() {
    }

    @Test
    void getUser() {
    }

    @Test
    void getCurrentUserInformation() {
    }

    @Test
    void checkInvalidToken() {
    }

    @Test
    void getUserInformationList() {
    }

    @Test
    void getTokenByLogin() {
    }

    @Test
    void getTokenByOauth() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void setTemporaryPassword() {
    }

    @Test
    void deleteUser() {
    }
}