package com.swave.releasenotesharesystem.User.request;
import lombok.*;

@Data
@AllArgsConstructor
public class UserRegisterRequestDto {
    private String department;
    private String email;
    private String user_name;
    private String password;
}
