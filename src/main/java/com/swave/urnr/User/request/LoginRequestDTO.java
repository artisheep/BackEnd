package com.swave.urnr.User.request;
import lombok.*;

@Data
@Builder
public class LoginRequestDTO {

    private String email;
    private String password;
}
