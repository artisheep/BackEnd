package com.swave.releasenotesharesystem.User.controller;

import com.swave.releasenotesharesystem.User.exception.InvalidIdException;
import com.swave.releasenotesharesystem.User.request.UserRegisterRequestDto;
import com.swave.releasenotesharesystem.User.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.util.Enumeration;

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
    public String temp(HttpServletRequest request, @RequestBody UserRegisterRequestDto pageRequest) throws InvalidIdException, UnknownHostException {
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
    public String testGET(HttpServletRequest request, @RequestBody UserRegisterRequestDto pageRequest) throws InvalidIdException, UnknownHostException {
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

    @PostMapping("/api/create/user")
    public void createUser(@RequestBody UserRegisterRequestDto request) {
        userServiceImpl.createUser(request);
    }

    @GetMapping("/api/MappingTest")
    public String testController() {
return "conffirmed";
    }

}
