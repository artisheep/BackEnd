package com.swave.urnr.user.responsedto;


import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@ApiModel(value = "유저 멤버 정보 응답 DTO")
@Data
public class UserMemberInfoResponseDTO {

    @ApiModelProperty(value = "유저(멤버) ID", example = "1", required = true)
    Long user_Id;

    @ApiModelProperty(value="유저(멤버) 이름", example = "전강훈", required = true)
    String username;

    @ApiModelProperty(value="유저(멤버) 소속", example = "가천대학교", required = true)
    String user_Department;

    @QueryProjection
    public UserMemberInfoResponseDTO(Long user_Id, String username, String user_Department) {
        this.user_Id = user_Id;
        this.username = username;
        this.user_Department = user_Department;
    }
}
