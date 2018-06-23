package com.example.api.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author haijun
 * @class ${classname}
 * @date 2018/6/20, 10:55
 */
@Data
public class User implements Serializable{

    private Integer id;
    private String userName;
    private transient String passWord;
}
