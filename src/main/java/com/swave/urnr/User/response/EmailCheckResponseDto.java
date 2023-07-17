package com.swave.urnr.User.response;

import lombok.*;

/*
Usage: Check if Email is Valid or Not.

 */
@Data
@Builder
public class EmailCheckResponseDto {
    boolean isValid;
}
