package com.swave.releasenotesharesystem.User.service;

import com.swave.releasenotesharesystem.User.exception.InvalidIdException;
import com.swave.releasenotesharesystem.User.request.UserRegisterRequestDto;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;


public interface UserService {

    public default void test(HttpServletRequest request, UserRegisterRequestDto userRequest) throws InvalidIdException, UnknownHostException {


    }

    public default void createUser(UserRegisterRequestDto request) {}

    public default void testGET(HttpServletRequest request, UserRegisterRequestDto userRequest) throws InvalidIdException, UnknownHostException {


    }
    public default void createSurvey(HttpServletRequest request, UserRegisterRequestDto userRequest) throws InvalidIdException, UnknownHostException {


    }
}
