package com.swave.releasenotesharesystem.User.service;

import com.swave.releasenotesharesystem.User.exception.InvalidIdException;
import com.swave.releasenotesharesystem.User.exception.UserNotFoundException;
import com.swave.releasenotesharesystem.User.repository.UserRepository;
import com.swave.releasenotesharesystem.User.request.*;
import com.swave.releasenotesharesystem.User.response.EmailCheckResponseDto;
import com.swave.releasenotesharesystem.User.response.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



import com.swave.releasenotesharesystem.User.domain.User;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void test(HttpServletRequest request, RegisterRequestDto userRequest) throws InvalidIdException {

    }

    @Override
    public void testGET(HttpServletRequest request, RegisterRequestDto userRequest) throws InvalidIdException {

    }


    /*
    TODO: Make a update-holder
     */
    @Override
    public void createUser(RegisterRequestDto request) {

        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .name(request.getName())
                .build();

        userRepository.save(user);

    }
@Override
    public EmailCheckResponseDto checkEmailBody(EmailCheckRequestDto request) {

        log.info(request.toString());
        String email = (String) request.getEmail();
        Boolean  result = userRepository.findByEmail(email).isPresent();

        EmailCheckResponseDto emailCheckResponseDto = EmailCheckResponseDto.builder()
                .isValid(result)
                .build();

        return emailCheckResponseDto;
    }
@Override
public String checkEmailHead(HttpServletRequest request) throws UserNotFoundException{
        String email = request.getHeader("email");
        //log.info("User Email : "+email);
        Boolean value =userRepository.findByEmail(email).isEmpty() ;
    //log.info(value.toString() );
        if( request.getHeader("email") == null || userRepository.findByEmail(email).isEmpty()){
            //log.info("User Doesn't Have any Header or not valid account");
            throw new UserNotFoundException();
        }

    log.info("User Email is Valid : "+email);
        return email;
}
@Override
    public void updateUser(HttpServletRequest request,UpdateUserDto requestDto) throws UserNotFoundException{
        String email = checkEmailHead(request);
        Optional<User> optionalUser  = userRepository.findByEmail(email);
        String newEmail = requestDto.getEmail();
        String newPassword = requestDto.getPassword();
        String newDepartment = requestDto.getDepartment();
        String newName = requestDto.getName();

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(newEmail != null && !newEmail.isEmpty()){
                user.setEmail(newEmail);
            }
            if(newPassword != null && !newPassword.isEmpty()){
                user.setPassword(newPassword);
            }
            if(newDepartment !=null && !newDepartment.isEmpty()){
                user.setDepartment(newDepartment);
            }
            if(newName !=null && !newName.isEmpty()){
                user.setName(newName);
            }
            log.info("Final : " + user.toString());
            userRepository.save(user);
        }
        else{
            throw new UserNotFoundException();

        }

}

    @Override
    public LoginResponseDTO userLogin(HttpServletRequest request, LoginRequestDTO requestDto) throws UserNotFoundException {
        String email = checkEmailHead(request);
        Optional<User> optionalUser  = userRepository.findByEmail(email);
        boolean result = false;
        if(optionalUser.isPresent())
        {
            User user = optionalUser.get();
            log.info("User : "+ user.getPassword() + " request : " + requestDto.getPassword());
            if(user.getPassword().equals(requestDto.getPassword())){
                result =true;
            }
        }

LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder()
        .result(result)
        .build();
        return loginResponseDTO;
    }

    @Override
    public void deleteUser(HttpServletRequest request, DeleteUserDto requestDTO) throws UserNotFoundException{

        String email = checkEmailHead(request);
        User user = userRepository.findByEmail(email).get();

        Long target = user.getId();

        userRepository.deleteById(target);

    }
}
