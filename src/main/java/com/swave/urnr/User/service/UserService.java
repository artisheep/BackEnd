package com.swave.urnr.User.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.swave.urnr.User.domain.User;
import com.swave.urnr.User.domain.UserUpdateRequest;
import com.swave.urnr.User.exception.InvalidIdException;
import com.swave.urnr.User.exception.UserNotFoundException;
import com.swave.urnr.User.request.*;
import com.swave.urnr.User.response.EmailCheckResponseDto;
import com.swave.urnr.User.response.LoginResponseDTO;
import com.swave.urnr.Util.exception.InvalidTokenException;
import org.springframework.http.ResponseEntity;


import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;


public interface UserService {
    void test(HttpServletRequest request, RegisterRequestDto userRequest) throws InvalidIdException, UnknownHostException;

    void testGET(HttpServletRequest request, RegisterRequestDto userRequest) throws InvalidIdException, UnknownHostException;

    void createUser(RegisterRequestDto request);

     EmailCheckResponseDto checkEmailBody(EmailCheckRequestDto request);

    String checkEmailString(String request ) throws  UserNotFoundException;
    void updateUser(HttpServletRequest request,UpdateUserDto requestDto) throws UserNotFoundException;

    LoginResponseDTO userLogin(HttpServletRequest request, LoginRequestDTO requestDto) throws UserNotFoundException;

      void deleteUser (HttpServletRequest request, DeleteUserDto requestDTO) throws UserNotFoundException;

      User getUser(HttpServletRequest request) throws UserNotFoundException;
      ResponseEntity<Object> getCurrentUser(HttpServletRequest request) throws InvalidTokenException, UserNotFoundException;

      ResponseEntity getLogin(String code,String provider) throws JsonProcessingException ;
      String updateMyPage(HttpServletRequest request, UserUpdateRequest userUpdateRequest) throws Exception;

      void checkInvalidToken(HttpServletRequest request) throws InvalidTokenException;

}
