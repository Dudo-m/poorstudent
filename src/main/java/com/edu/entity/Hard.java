package com.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//困难申请
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("hard")
public class Hard extends BaseEntity{
    @TableId(type = IdType.AUTO)
    private String hardId;  //困难申请id
    private String hardReason; //申请理由
    private String hardResult;  //认定结果
    private String studentId;  //学生信息id
}
