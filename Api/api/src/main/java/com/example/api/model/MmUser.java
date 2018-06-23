package com.example.api.model;

import javax.persistence.*;

@Table(name = "mm_user")
public class MmUser {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码(MD5加密)
     */
    private String password;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取用户密码(MD5加密)
     *
     * @return password - 用户密码(MD5加密)
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码(MD5加密)
     *
     * @param password 用户密码(MD5加密)
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}