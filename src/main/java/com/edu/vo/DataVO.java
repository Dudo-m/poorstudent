package com.edu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//layui数据表格格式
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataVO<T> {
    private Integer code;
    private String msg;
    private long count;
    private List<T> data;
}
