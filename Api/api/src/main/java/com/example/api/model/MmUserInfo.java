package com.example.api.model;

import javax.persistence.*;

@Table(name = "mm_user_info")
public class MmUserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    private Integer uid;

    private String qq;

    private String email;

    private String phone;

    /**
     * 用户详细信息表
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * @return id
     */
    public Byte getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Byte id) {
        this.id = id;
    }

    /**
     * @return uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * @return qq
     */
    public String getQq() {
        return qq;
    }

    /**
     * @param qq
     */
    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取用户详细信息表
     *
     * @return real_name - 用户详细信息表
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置用户详细信息表
     *
     * @param realName 用户详细信息表
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }
}