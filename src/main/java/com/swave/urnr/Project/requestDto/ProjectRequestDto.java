package com.swave.urnr.Project.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequestDto {
    Long id;
    String name;
    String description;
    Date createDate;

    //managerID
    Long userId;
    List<Long> users;


    public ProjectRequestDto(Long id, String name, String description, Date createDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createDate = createDate;

    }


}
