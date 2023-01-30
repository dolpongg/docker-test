package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ApiModel("BookmarkInfoReq")
public class BookmarkRegisterReq {
    @ApiModelProperty(name="유저 email", example="email")
    String email;
    @ApiModelProperty(name="강의 id", example="lesson_id")
    Long lesson_id;
}
