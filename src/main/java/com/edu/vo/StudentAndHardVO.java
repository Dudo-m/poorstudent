package com.edu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAndHardVO {
    private String studentId; //学生信息id
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
    private String hardId;  //困难申请id
    private String hardReason; //申请理由
    private String hardResult;  //认定结果
}
