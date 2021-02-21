package com.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//资源申请
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("support")
public class Support {
    @TableId(type = IdType.AUTO)
    private String supportId;  //资助申请id
    private String resourceId; //资源id
    private String studentId;  //学生信息id
    private String supportResult;  //认定结果
}
