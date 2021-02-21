package com.edu.vo;

import com.edu.entity.Resources;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 我的申请vo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceAndSupportVO {
    private String resourceId; //资源id
    private String resourceName; //资源名
    private Date resourceTime; //创建时间
    private String resourceInfo; //资源描述
    private String resourceLevel; //资源级别
    private String supportResult;
    private String supportId;
}
