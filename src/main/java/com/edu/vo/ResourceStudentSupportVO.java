package com.edu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 申请表左连接资源信息和学生信息vo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceStudentSupportVO {
    private String supportId;  //资助申请id
    private String resourceId; //资源id
    private String studentId;  //学生信息id
    private String supportResult;  //认定结果

    private String studentName;  //姓名
    private String studentSex;  //性别
    private String studentNumber;  //学号
    private String studentCardid;  //身份证
    private Integer studentYear;   //入学年份
    private String studentEducation;  //学历
    private String studentDepartment;  //院系
    private String studentClass;  //班级
    private Integer studentPopulation;  //家庭人口数
    private String studentRes;  //户口
    private boolean specialOad;  //孤残
    private boolean specialSp;  //单亲
    private boolean specialCom;  //烈士子女
    private boolean specialFo;  //优抚对象
    private boolean specialSa;  //低保家庭
    private String userId;  //用户id

    private String resourceName; //资源名

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date resourceTime; //创建时间
    private String resourceInfo; //资源描述
    private String resourceLevel; //资源级别
}
