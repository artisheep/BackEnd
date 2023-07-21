package com.swave.urnr.releasenote.requestdto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@ApiModel(value = "릴리즈 노트 업데이트 DTO")
@NoArgsConstructor
public class ReleaseNoteUpdateRequestDTO {
    @ApiModelProperty(value="버전 명", example = "1.0.1", required = true)
    private String version;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @ApiModelProperty(value="릴리즈 날짜", example = "2023-07-08", required = true)
    private Date releaseDate;
    @ApiModelProperty(value="업데이트할 내용", example = "그램을 너프 했습니다.", required = true)
    private String content;
}
