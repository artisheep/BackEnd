package com.swave.releasenotesharesystem.User.requestDto;

import com.swave.releasenotesharesystem.User.domain.UserInProject;
import com.swave.releasenotesharesystem.Util.type.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInProjectRequestDto {
    private Long id;


}
