package com.swave.urnr.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swave.urnr.user.domain.User;
import com.swave.urnr.user.repository.UserRepository;
import com.swave.urnr.user.requestdto.*;
import com.swave.urnr.user.responsedto.UserListResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Slf4j
@RequiredArgsConstructor
class UserControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    @DisplayName("유저 생성 테스트")
    void setAccountToServer() throws Exception {
        UserRegisterRequestDto userRegisterRequestDto = UserRegisterRequestDto.builder()
                .email("corgiwalke@gmail.com")
                .name("전강훈")
                .password("1q2w3e4r")
                .build();

        String json = objectMapper.writeValueAsString(userRegisterRequestDto);
        log.info(json);
        MvcResult result = mockmvc.perform(post("/api/user/prelogin/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is(201))
                .andReturn();


        User user = User.builder()
                .name("전강훈")
                .email("dreamingjas@gmail.com")
                .provider("kakao").build();

        userRepository.save(user);
    }

    @Test
    @DisplayName("유저 중복 생성 테스트")
    void createAccountByEmail() throws Exception {

        /*
        정상 생성 케이스 및 중복, 그리고 유효성 없는 이메일로 테스트 진행
         */

        UserRegisterRequestDto userRegisterRequestDto = UserRegisterRequestDto.builder()
                .email("artisheep@naver.com")
                .name("전강훈")
                .password("1q2w3e4r")
                .build();

        String json = objectMapper.writeValueAsString(userRegisterRequestDto);
        log.info(json);
        MvcResult result = mockmvc.perform(post("/api/user/prelogin/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is(201))
                .andReturn();

        result = mockmvc.perform(post("/api/user/prelogin/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is(409))
                .andReturn();
        userRegisterRequestDto = UserRegisterRequestDto.builder()
                .email("artisheepnavercom")
                .name("전강훈")
                .password("1q2w3e4r")
                .build();
        json = objectMapper.writeValueAsString(userRegisterRequestDto);
        result = mockmvc.perform(post("/api/user/prelogin/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is(400))
                .andReturn();
        }


    @Test
    @DisplayName("이메일 전송 테스트")
    void emailSend() throws Exception {
        UserValidateEmailDTO userValidateEmailDTO = UserValidateEmailDTO.builder()
                .email("corgiwalke@gmail.com")
                .build();

        String json = objectMapper.writeValueAsString(userValidateEmailDTO);
        MvcResult result = mockmvc.perform(post("/api/user/prelogin/get-temporary-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                        .andExpect(status().is(200))
                        .andReturn();

    }

    @Test
    void updateUser() throws Exception{

        UserLoginServerRequestDTO userLoginServerRequestDTO = UserLoginServerRequestDTO.builder()
                .email("corgiwalke@gmail.com")
                .password("1q2w3e4r")
                .build();

        String json = objectMapper.writeValueAsString(userLoginServerRequestDTO);
        MvcResult result = mockmvc.perform(post("/api/user/prelogin/login-by-email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is(200))
                .andReturn();
        UserUpdateAccountRequestDto userUpdateAccountRequestDto = UserUpdateAccountRequestDto.builder()
                .department("Good bye")
                .password("Seeyou")
                .name("Ganghoon jeon")
                .build();

        json = objectMapper.writeValueAsString(userUpdateAccountRequestDto);
        String responseContent = result.getResponse().getContentAsString();
        result = mockmvc.perform(put("/api/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .header("Authorization", "Bearer "+responseContent))
                .andExpect(status().is(204))
                .andReturn();
    }

    @Test
    void initDepartment() throws Exception {

        UserLoginServerRequestDTO userLoginServerRequestDTO = UserLoginServerRequestDTO.builder()
                .email("corgiwalke@gmail.com")
                .password("1q2w3e4r")
                .build();

        String json = objectMapper.writeValueAsString(userLoginServerRequestDTO);
        MvcResult result = mockmvc.perform(post("/api/user/prelogin/login-by-email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is(200))
                .andReturn();

        UserDepartmentRequestDto userDepartmentRequestDto = UserDepartmentRequestDto.builder()
                .department("Test")
                .build();

        json = objectMapper.writeValueAsString(userDepartmentRequestDto);
        String responseContent = result.getResponse().getContentAsString();
        result = mockmvc.perform(patch("/api/user/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .header("Authorization", "Bearer "+responseContent))
                .andExpect(status().is(200))
                .andReturn();
    }

    @Test
    @DisplayName("로그인 테스트")
    void getTokenByLogin() throws Exception {

        UserLoginServerRequestDTO userLoginServerRequestDTO = UserLoginServerRequestDTO.builder()
                .email("corgiwalke@gmail.com")
                .password("1q2w3e4r")
                .build();

        String json = objectMapper.writeValueAsString(userLoginServerRequestDTO);
        MvcResult result = mockmvc.perform(post("/api/user/prelogin/login-by-email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is(200))
                .andReturn();

        userLoginServerRequestDTO = UserLoginServerRequestDTO.builder()
                .email("corgiwalke@gmail.com")
                .password("1q2w3e4r!!!!")
                .build();

        json = objectMapper.writeValueAsString(userLoginServerRequestDTO);
        result = mockmvc.perform(post("/api/user/prelogin/login-by-email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is(409))
                .andReturn();
    }

    @Test
    @DisplayName("Delete")
    void deleteUser() throws  Exception {

        UserLoginServerRequestDTO userLoginServerRequestDTO = UserLoginServerRequestDTO.builder()
                .email("corgiwalke@gmail.com")
                .password("1q2w3e4r")
                .build();

        String json = objectMapper.writeValueAsString(userLoginServerRequestDTO);
        MvcResult result = mockmvc.perform(post("/api/user/prelogin/login-by-email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is(200))
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        result = mockmvc.perform(delete("/api/user/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .header("Authorization", "Bearer "+responseContent))
                .andExpect(status().is(200))
                .andReturn();
    }

    @Test
    void getTokenByOauth() {
    }

    @Test
    void getUserInformationList() throws Exception {

        MvcResult result = mockmvc.perform(get("/api/user/prelogin/getuserlist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().is(200))
                .andReturn();
    }

    @Test
    void getCurrentUserInformation() throws Exception {

        UserLoginServerRequestDTO userLoginServerRequestDTO = UserLoginServerRequestDTO.builder()
                .email("corgiwalke@gmail.com")
                .password("1q2w3e4r")
                .build();

        String json = objectMapper.writeValueAsString(userLoginServerRequestDTO);
        MvcResult result = mockmvc.perform(post("/api/user/prelogin/login-by-email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is(200))
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        result = mockmvc.perform(get("/api/user/getuser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .header("Authorization", "Bearer "+responseContent))
                .andExpect(status().is(200))
                .andReturn();
    }
}