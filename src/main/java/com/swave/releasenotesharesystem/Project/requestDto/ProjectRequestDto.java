package com.swave.releasenotesharesystem.Project.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequestDto {
    Long id;
    String name;
    String description;
    Date createDate;
    Long userId;

}
