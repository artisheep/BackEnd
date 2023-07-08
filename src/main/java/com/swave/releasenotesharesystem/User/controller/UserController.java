package com.swave.releasenotesharesystem.User.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.swave.releasenotesharesystem.User.domain.UserUpdateRequest;
import com.swave.releasenotesharesystem.User.repository.UserRepository;
import com.swave.releasenotesharesystem.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserService userService; //(2)

    private final UserRepository userRepository;

    // 프론트에서 인가코드 받아오는 url
    @PostMapping("/oauth/token")
    public ResponseEntity getLogin(@RequestParam("code") String code, @RequestParam("provider") String provider) throws JsonProcessingException {
        return userService.getLogin(code,provider);
    }

    @GetMapping("/me")
    public ResponseEntity<Object> getCurrentUser(HttpServletRequest request) throws Exception { //(1)
        return userService.getCurrentUser(request);
    }


    @PostMapping("/updatepage")
    public String updateMyPage(HttpServletRequest request, @RequestBody UserUpdateRequest user) throws Exception { //(1)
        return userService.updateMyPage(request,user);
    }
}
