package com.swave.releasenotesharesystem.User.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.swave.releasenotesharesystem.User.domain.User;
import com.swave.releasenotesharesystem.User.domain.UserUpdateRequest;
import com.swave.releasenotesharesystem.User.repository.UserRepository;
import com.swave.releasenotesharesystem.Util.OAuth.JwtProperties;
import com.swave.releasenotesharesystem.Util.OAuth.OauthToken;
import com.swave.releasenotesharesystem.Util.exception.InvalidTokenException;
import com.swave.releasenotesharesystem.Util.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final OAuthService oAuthService;

    private final UserRepository userRepository;


    @Override
    public User getUser(HttpServletRequest request) {
        Long userCode = (Long) request.getAttribute("userCode");
        User user = userRepository.findById(userCode).orElseThrow(UserNotFoundException::new);
        return user;
    }

    public ResponseEntity<Object> getCurrentUser(HttpServletRequest request) throws InvalidTokenException {
        checkInvalidToken(request);
        User user = getUser(request);
        System.out.println(user.getEmail());
        return ResponseEntity.ok().body(user);
    }

    public ResponseEntity getLogin(String code,String provider) throws JsonProcessingException {
        OauthToken oauthToken = oAuthService.getAccessToken(code, provider);
        String jwtToken = oAuthService.SaveUserAndGetToken(oauthToken.getAccess_token(), provider);
        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
        return ResponseEntity.ok().headers(headers).body("\"success\"");
    }
    public String updateMyPage(HttpServletRequest request, UserUpdateRequest userUpdateRequest) throws Exception {
        checkInvalidToken(request);
        Long userId =getUser(request).getId();
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setName(userUpdateRequest.getNickname());
            userRepository.save(user);
        }else {
            throw new Exception();
        }
        return "success";
    }

    public void checkInvalidToken(HttpServletRequest request) throws InvalidTokenException {
        if(request.getHeader("Authorization") == null) {
//            log.info("error");
            throw new InvalidTokenException();
        }
//        log.info("토큰 체크 완료");
    }


}
