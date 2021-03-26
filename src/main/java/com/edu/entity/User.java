package com.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private String userId;

    private String userName;
    private String userPassword;
    private String userPhone;
    private String userEmail;
    private String userRole;
}
