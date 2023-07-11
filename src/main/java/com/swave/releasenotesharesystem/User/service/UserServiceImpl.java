package com.swave.releasenotesharesystem.User.service;

import com.swave.releasenotesharesystem.User.domain.User;
import com.swave.releasenotesharesystem.User.exception.InvalidIdException;
import com.swave.releasenotesharesystem.User.repository.UserRepository;
import com.swave.releasenotesharesystem.User.request.UserRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void test(HttpServletRequest request, UserRegisterRequestDto userRequest) throws InvalidIdException {

    }

    @Override
    public void testGET(HttpServletRequest request, UserRegisterRequestDto userRequest) throws InvalidIdException {

    }

    @Override
    public void createUser(UserRegisterRequestDto request) {

        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .name(request.getUser_name())
                .build();

        userRepository.save(user);

    }
}
