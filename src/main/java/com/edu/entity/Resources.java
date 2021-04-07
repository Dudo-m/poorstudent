package com.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("resource")
public class Resources extends BaseEntity{
    @TableId(type = IdType.AUTO)
    private String resourceId; //资源id
    private String resourceName; //资源名
    private Date resourceTime; //创建时间
    private String resourceInfo; //资源描述
    private String resourceLevel; //资源级别
}
