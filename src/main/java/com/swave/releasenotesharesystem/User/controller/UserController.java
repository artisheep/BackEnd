package com.swave.releasenotesharesystem.User.controller;

import com.swave.releasenotesharesystem.User.domain.User;
import com.swave.releasenotesharesystem.User.exception.InvalidIdException;
import com.swave.releasenotesharesystem.User.exception.UserNotFoundException;
import com.swave.releasenotesharesystem.User.request.*;
import com.swave.releasenotesharesystem.User.response.EmailCheckResponseDto;
import com.swave.releasenotesharesystem.User.response.LoginResponseDTO;
import com.swave.releasenotesharesystem.User.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.util.Enumeration;

/*
TODO LIST: 1. mail validation system
           2. login full implementation
           3. Code cleaning

 */
@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    /*
    Test code to show how api works, and how can derive header from it.
    Both codes do simple function: returns header and body.
     */
    @PostMapping(value = "/api/test")
    public String temp(HttpServletRequest request, @RequestBody RegisterRequestDto pageRequest) throws InvalidIdException, UnknownHostException {
        log.info("PR :  "+String.valueOf(pageRequest));
        log.info("request :  "+String.valueOf(request));
        userServiceImpl.test(request, pageRequest);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            log.info("Header  " + headerNames.nextElement());
            log.info("Value  " + request.getHeader(headerNames.nextElement()));

        }
        return "Test Complete"+String.valueOf(pageRequest)+" "+String.valueOf(request);
    }

    @GetMapping(value = "/api/testGET")
    public String testGET(HttpServletRequest request, @RequestBody RegisterRequestDto pageRequest) throws InvalidIdException, UnknownHostException {
        log.info("PR :  "+String.valueOf(pageRequest));
        log.info("request :  "+String.valueOf(request));
        userServiceImpl.testGET(request, pageRequest);
        Enumeration<String> headerNames = request.getHeaderNames();
        String Temp = "";
        while (headerNames.hasMoreElements()) {
            log.info("Header  " + headerNames.nextElement());
            log.info("Value  " + request.getHeader(headerNames.nextElement()));
        }
        return "Test Complete "+String.valueOf(pageRequest)+" "+String.valueOf(request);
    }

    @PostMapping("/login/createUser")
    public String createUser(@RequestBody @Validated RegisterRequestDto request) {
        userServiceImpl.createUser(request);
        return "Created "+request.toString();
    }

    @GetMapping("/login/checkValidId")
public EmailCheckResponseDto checkValidEmail(@RequestBody EmailCheckRequestDto request){
        return userServiceImpl.checkEmailBody(request);
    }

    @PostMapping("/login/updateUser")
    public String updateUser(HttpServletRequest request,@RequestBody UpdateUserDto requestDto) throws UserNotFoundException{
        userServiceImpl.updateUser(request, requestDto);
        return "updated";
    }


    @GetMapping("/login/login")
    public LoginResponseDTO login(HttpServletRequest request, @RequestBody  LoginRequestDTO requestDto) throws UserNotFoundException{

        return userServiceImpl.userLogin(request,requestDto);
    }

    /*
    TODO: Avoid 500 ERROR
     */
    @GetMapping("/login/deleteAccount")
    public void deleteUser(HttpServletRequest request, @RequestBody DeleteUserDto requestDTO ) throws UserNotFoundException{
        userServiceImpl.deleteUser(request, requestDTO);
    }
}
