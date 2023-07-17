package com.swave.urnr.project.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE project SET is_deleted = true WHERE project_id = ?")
public class ProjectContentResponseDto {
    Long id;
    String name;
    String description;
    Date createDate;
    //todo releasenote 나중에 구현할것
    //todo usercount 나중에 구현할것


}
