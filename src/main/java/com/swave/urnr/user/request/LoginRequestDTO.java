package com.swave.urnr.user.request;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {

    private String email;
    private String password;
}
