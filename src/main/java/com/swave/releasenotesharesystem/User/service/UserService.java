package com.swave.releasenotesharesystem.User.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.swave.releasenotesharesystem.User.domain.User;
import com.swave.releasenotesharesystem.User.domain.UserUpdateRequest;
import com.swave.releasenotesharesystem.Util.exception.InvalidTokenException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    public User getUser(HttpServletRequest request) ;
    public ResponseEntity<Object> getCurrentUser(HttpServletRequest request) throws InvalidTokenException;

    public ResponseEntity getLogin(String code,String provider) throws JsonProcessingException;
    public String updateMyPage(HttpServletRequest request, UserUpdateRequest userUpdateRequest) throws Exception;

    public void checkInvalidToken(HttpServletRequest request) throws InvalidTokenException;

}
