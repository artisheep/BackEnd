package com.swave.releasenotesharesystem.ReleaseNote.responseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

@Data
@ApiModel(value = "댓글 내용 리스 가져오기 DTO")
@AllArgsConstructor
public class ResponseCommentContentListDTO {
    @ApiModelProperty(value="댓글들의 리스트", required = true)
    private ArrayList<ResponseCommentContentDTO> comments;
}
