package com.swave.releasenotesharesystem.ReleaseNote.responseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@ApiModel(value = "릴리즈 노트 버전 리스트 가져오기 DTO")
@NoArgsConstructor
public class ResponseVersionListDTO {
    @ApiModelProperty(value="프로젝트 이름", example = "노트북 성능 개선 프로젝트", required = true)
    private String projectName;
    @ApiModelProperty(value="프로젝트 ID", example = "2", required = true)
    private Long projectId;
    @ApiModelProperty(value="구독중인가?", example = "true", required = true)
    private boolean isSubscribe;
    @ApiModelProperty(value="릴리즈 노트 버전 리스트", required = true)
    private ArrayList<ResponseReleaseNoteVersionListDTO> releaseNoteVersionList;
}
