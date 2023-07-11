package com.swave.releasenotesharesystem.Project.responseDto;

import com.swave.releasenotesharesystem.Util.type.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class loadOneProjectDto {
    Long id;
    String name;
    String description;
    Date createDate;
    //todo releasenote 나중에 구현할것
    //todo usercount 나중에 구현할것


}
