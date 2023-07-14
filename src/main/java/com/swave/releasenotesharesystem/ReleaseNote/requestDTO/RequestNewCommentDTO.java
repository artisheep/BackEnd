package com.swave.releasenotesharesystem.ReleaseNote.requestDTO;

import io.swagger.annotations.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(value = "새 댓글 생성 DTO")
@NoArgsConstructor
public class RequestNewCommentDTO {


    @ApiModelProperty(value = "댓글 내용", example = "Good ~ !", required = true)
    @ApiParam(value = "내용", required = true, example = "내용을 입력하세요.")
    private String content;
}
