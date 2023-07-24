package com.swave.urnr.user.responsedto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.persistence.Column;

@ApiModel(value = "유저 정보 리스트 반환용 DTO")
public interface UserListResponseDTO {

    @ApiModelProperty(value="사용자 식별번호", example = "1", required = true)
    @ApiParam(value = "사용자 식별번호", required = true, example = "15")
    Long getUserId();

    @ApiModelProperty(value="사용자 이름", example = "전강훈", required = true)
    @ApiParam(value = "이름", required = true, example = "전강훈")
    String getUsername();

    @ApiModelProperty(value="사용자 소속", example = "가천대학교", required = true)
    @ApiParam(value = "소속", required = true, example = "소속을 입력하세요.")
    String getUserDepartment();

}