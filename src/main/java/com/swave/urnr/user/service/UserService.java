package com.swave.urnr.user.service;

import com.swave.urnr.user.responsedto.UserListResponseDTO;
import com.swave.urnr.util.common.ResponseDto;
import com.swave.urnr.user.domain.User;
import com.swave.urnr.user.exception.UserNotFoundException;
import com.swave.urnr.user.requestdto.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface UserService {

    ResponseEntity<ResponseDto> createAccountByEmail(UserRegisterRequestDto request);

    ResponseEntity<String> getValidationCode(UserValidateEmailDTO request) ;

    ResponseEntity<String> updateUser(HttpServletRequest request, UserUpdateAccountRequestDto requestDto);

    ResponseEntity<ResponseDto> initDepartment(HttpServletRequest request, UserDepartmentRequestDto requestDto) ;

    ResponseEntity<String> getTokenByLogin(UserLoginServerRequestDTO requestDto) ;

    ResponseEntity<String> setTemporaryPassword(UserValidateEmailDTO request) ;

    ResponseEntity<String> deleteUser(HttpServletRequest request);

    User getUser(HttpServletRequest request) throws UserNotFoundException;

    ResponseEntity<Object> getCurrentUserInformation(HttpServletRequest request) throws RuntimeException;

    ResponseEntity getTokenByOauth(String code, String provider) throws RuntimeException;

    void checkInvalidToken(HttpServletRequest request) ;

    List<UserListResponseDTO> getUserInformationList();
}
