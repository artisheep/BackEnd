package com.swave.urnr.project.responsedto;

import com.querydsl.core.annotations.QueryProjection;
import com.swave.urnr.project.domain.Project;
import com.swave.urnr.user.domain.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(value = "프로젝트 싹다 가져오는 DTO")
public class TestDTO {
    Project project;
    User user;

    @QueryProjection
    public TestDTO(Project project, User user) {
        this.project = project;
        this.user = user;
    }
}
