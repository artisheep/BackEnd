package com.swave.urnr.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swave.urnr.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Slf4j
@RequiredArgsConstructor
class UserServiceImplTest {


    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void createAccountByEmail() {

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