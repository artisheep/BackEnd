package com.swave.urnr.User.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateRequest {
    String nickname;

    String email;

    String description;
}
