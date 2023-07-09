package com.swave.releasenotesharesystem.User.service;

import com.swave.releasenotesharesystem.User.exception.InvalidIdException;
import com.swave.releasenotesharesystem.User.exception.UserNotFoundException;
import com.swave.releasenotesharesystem.User.request.*;
import com.swave.releasenotesharesystem.User.response.EmailCheckResponseDto;
import com.swave.releasenotesharesystem.User.response.LoginResponseDTO;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;


public interface UserService {

    public default void test(HttpServletRequest request, RegisterRequestDto userRequest) throws InvalidIdException, UnknownHostException {

    }


    public default void testGET(HttpServletRequest request, RegisterRequestDto userRequest) throws InvalidIdException, UnknownHostException {


    }


    public default void createUser(RegisterRequestDto request) {}

    public default void deleteUser(RegisterRequestDto request) {}


    public default void readUser(RegisterRequestDto request) {}

    public default void updateDepartment(RegisterRequestDto request) {}

    public default EmailCheckResponseDto checkEmailBody(EmailCheckRequestDto request) { return null; }

public default String checkEmailHead(HttpServletRequest request) throws  UserNotFoundException {return null;}
    public default void updateUser(HttpServletRequest request,UpdateUserDto requestDto) throws UserNotFoundException {}


    public default LoginResponseDTO userLogin(HttpServletRequest request, LoginRequestDTO requestDto) throws UserNotFoundException {return null;}

public default void deleteUser (HttpServletRequest request, DeleteUserDto requestDTO) throws UserNotFoundException{}
}
