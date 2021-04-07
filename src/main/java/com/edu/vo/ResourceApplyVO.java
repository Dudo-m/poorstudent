package com.edu.vo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceApplyVO {
    private String resourceId; //资源id
    private String resourceName; //资源名
    private Date resourceTime; //创建时间
    private String resourceInfo; //资源描述
    private String resourceLevel; //资源级别
    //是否已申请
    private boolean  flag;
}