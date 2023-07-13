package com.swave.releasenotesharesystem.ReleaseNote.requestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

//프로젝트 id
//버전
//최종 수정 날짜(내가 생성)
//릴리즈 날짜(유저가)
//내용
@Data
@ApiModel(value = "새 릴리즈 노트 작성 DTO")
@NoArgsConstructor
public class RequestNewReleaseNoteDTO {
    @ApiModelProperty(value="릴리즈 노트 버전", example = "1.0.0", required = true)
    private String version;
    @ApiModelProperty(value="릴리즈 날", example = "2023-07-08", required = true)
    private Date releaseDate;
    @ApiModelProperty(value="릴리즈 노트 내용", example = "맥북을 상향 했습니다.", required = true)
    private String content;
}
