package com.example.api.shiro;

import com.example.api.model.MmUser;
import com.example.api.model.Module;
import com.example.api.model.Role;
import com.example.api.service.UserInfoService;
import com.example.api.util.ApiException;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author haijun
 * @class ${classname}
 * @date 2018/6/20, 13:15
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoService userInfoService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        String username = utoken.getUsername();
        MmUser user = userInfoService.getInfo(username);
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行shiro权限!");
        MmUser user = (MmUser) principals.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissions = new ArrayList<>();
        userInfoService.getUserRoleInfo(user.getId())
                .stream().forEach(a -> {
            Role roleInfo = userInfoService.getRoleInfo(a.getRid());
            if (Objects.isNull(roleInfo)) {
                throw new ApiException("数据库暂未定义角色对应关系!");
            }
            userInfoService.getModuleRoleInfo(a.getRid())
                    .stream().forEach(b -> {
                if (Objects.isNull(b)) {
                    throw new ApiException("数据库暂未定义角色权限对应关系!");
                }
                Module moduleInfo = userInfoService.getModuleInfo(b.getRid());
                permissions.add(moduleInfo.getMname());
            });
        });
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        return info;
    }
}
