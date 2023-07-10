package com.swave.releasenotesharesystem.User.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.swave.releasenotesharesystem.User.domain.User;
import com.swave.releasenotesharesystem.User.domain.UserUpdateRequest;
import com.swave.releasenotesharesystem.User.exception.InvalidIdException;
import com.swave.releasenotesharesystem.User.exception.UserNotFoundException;
import com.swave.releasenotesharesystem.User.repository.UserRepository;
import com.swave.releasenotesharesystem.User.request.*;
import com.swave.releasenotesharesystem.User.response.EmailCheckResponseDto;
import com.swave.releasenotesharesystem.User.response.LoginResponseDTO;
import com.swave.releasenotesharesystem.User.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Optional;

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
 

    private final UserService userService; //(2)

    private final UserRepository userRepository;

    /*
    Test code to show how api works, and how can derive header from it.
    Both codes do simple function: returns header and body.
     */
    @PostMapping(value = "/api/test")
    public String temp(HttpServletRequest request, @RequestBody RegisterRequestDto pageRequest) throws InvalidIdException, UnknownHostException {
        log.info("PR :  " + String.valueOf(pageRequest));
        log.info("request :  " + String.valueOf(request));

        userService.test(request, pageRequest);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String temp = headerNames.nextElement().toString();
            log.info("Header  " + temp);
            log.info("Value  " + request.getHeader(temp).toString());

        }
        return "Test Complete" + String.valueOf(pageRequest) + " " + String.valueOf(request);
    }

    @GetMapping(value = "/api/testGET")
    public String testGET(HttpServletRequest request, @RequestBody RegisterRequestDto pageRequest) throws InvalidIdException, UnknownHostException {
        log.info("PR :  " + String.valueOf(pageRequest));
        log.info("request :  " + String.valueOf(request));
        userService.testGET(request, pageRequest);
        Enumeration<String> headerNames = request.getHeaderNames();
        String Temp = "";
        while (headerNames.hasMoreElements()) {
            log.info("Header  " + headerNames.nextElement());
            log.info("Value  " + request.getHeader(headerNames.nextElement()));
        }
        return "Test Complete " + String.valueOf(pageRequest) + " " + String.valueOf(request);
    }

    @PostMapping("/login/createUser")
    public String createUser(@RequestBody @Validated RegisterRequestDto request) {
        log.info(request.toString());
        userService.createUser(request);
        return "Created " + request.toString();
    }

    @GetMapping("/login/checkValidId")
    public EmailCheckResponseDto checkValidEmail(@RequestBody EmailCheckRequestDto request) {
        return userService.checkEmailBody(request);
    }

    @PostMapping("/login/updateUser")
    public String updateUser(HttpServletRequest request, @RequestBody UpdateUserDto requestDto) throws UserNotFoundException {
        userService.updateUser(request, requestDto);
        return "updated";
    }


    @GetMapping("/login/login")
    public LoginResponseDTO login(HttpServletRequest request, @RequestBody LoginRequestDTO requestDto) throws UserNotFoundException {

        return userService.userLogin(request, requestDto);
    }

    /*
    TODO: Avoid 500 ERROR
     */
    @GetMapping("/login/deleteAccount")
    public void deleteUser(HttpServletRequest request, @RequestBody DeleteUserDto requestDTO) throws UserNotFoundException {
        userService.deleteUser(request, requestDTO);

 
    }


    @PostMapping("/oauth/token")
    public ResponseEntity getLogin (@RequestParam("code") String code, @RequestParam("provider") String provider) throws
            JsonProcessingException {
        return userService.getLogin(code, provider);
    }

    @GetMapping("/me")
    public ResponseEntity<Object> getCurrentUser (HttpServletRequest request) throws Exception { //(1)
        return userService.getCurrentUser(request);
    }


    @PostMapping("/updatepage")
    public String updateMyPage (HttpServletRequest request, @RequestBody UserUpdateRequest user) throws Exception
    { //(1)
        return userService.updateMyPage(request, user);
    }


    @GetMapping("/TestForLogin")
    public String hayday(HttpServletRequest request){
        Long Id = (Long) request.getAttribute("id");
        User user = userRepository.findById(Id).get();

        log.info(" ID : "+Id + " name :"+user.getName());

        return "SUCUESS";
    }
}
