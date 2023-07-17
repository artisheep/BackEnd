package com.swave.urnr.User.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserDto {
    private String email;

    private String password;
    private String department;
    private String name;
}

