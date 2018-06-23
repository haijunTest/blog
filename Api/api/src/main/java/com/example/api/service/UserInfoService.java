package com.example.api.service;

import com.example.api.mapper.*;
import com.example.api.model.*;
import com.example.api.util.ApiException;
import com.example.api.util.Md5;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

/**
 * class $classname
 *
 * @author haijun
 * @date 2018/4/19, 13:32
 */
@Service
public class UserInfoService {

    @Autowired
    private MmUserMapper mmUserMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private ModuleRoleMapper moduleRoleMapper;
    @Autowired
    private ModuleMapper moduleMapper;

    @Transactional
    public String auth(String userName, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (StringUtils.isEmpty(userName)) {
            throw new ApiException("用户名不能为空！");
        }
        if (StringUtils.isEmpty(password)) {
            throw new ApiException("密码不能为空!");
        }
        MmUser realData = mmUserMapper.getUserInfo(userName);
        if (Objects.isNull(realData)) {
            throw new ApiException("该用户不存在!");
        }
        if (password.equals(realData.getPassword())) {
            throw new ApiException("数据库信息有误!!!请更新数据库数据!!!");
        }
        boolean result = Md5.validPassword(password, realData.getPassword());
        if (result) {
            return "success";
        }
        return "fail";
    }

    @Transactional
    public String regist(String userName, String passWord, String passWordAgain) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (StringUtils.isEmpty(userName)) {
            throw new ApiException("用户名不能为空!");
        }
        if (StringUtils.isEmpty(passWord)) {
            throw new ApiException("密码不能为空!");
        }
        if (StringUtils.isEmpty(passWordAgain)) {
            throw new ApiException("确认密码不能为空!");
        }
        if (!passWord.equals(passWordAgain)) {
            throw new ApiException("两次密码输入不一致,请重新输入!");
        }
        String encryptedPwd = Md5.getEncryptedPwd(passWord);
        MmUser mmUser = new MmUser();
        mmUser.setUsername(userName);
        mmUser.setPassword(encryptedPwd);
        int i = mmUserMapper.insert(mmUser);
        if (i == 1) {
            return "success";
        }
        return "fail";
    }

    public MmUser getInfo(String userName) {
        MmUser userInfo = mmUserMapper.getUserInfo(userName);
        return userInfo;
    }

    public List<UserRole> getUserRoleInfo(Integer uid) {
        UserRole userRole = new UserRole();
        userRole.setUid(uid);
        List<UserRole> userRoleList = userRoleMapper.select(userRole);
        return userRoleList;
    }

    public Role getRoleInfo(Integer rid) {
        Role role = roleMapper.selectByPrimaryKey(rid);
        return role;
    }

    public List<ModuleRole> getModuleRoleInfo(Integer rid) {
        ModuleRole moduleRole = new ModuleRole();
        moduleRole.setRid(rid);
        List<ModuleRole> moduleRoleList = moduleRoleMapper.select(moduleRole);
        return moduleRoleList;
    }

    public Module getModuleInfo(Integer mid) {
        Module module = moduleMapper.selectByPrimaryKey(mid);
        return module;
    }

    public MmUser getOperator (){
        Subject subject = SecurityUtils.getSubject();
        MmUser user = (MmUser) subject.getPrincipal();
        return user;
    }
}
