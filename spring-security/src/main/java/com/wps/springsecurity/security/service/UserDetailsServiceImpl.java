package com.wps.springsecurity.security.service;

/**
 * @Title UserDetailsServiceImpl
 * @Description
 * @auther wps
 * @Date 2020/4/2215:22
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wps.springsecurity.security.LoginUser;
import com.wps.springsecurity.testcontroller.entity.SysUser;
import com.wps.springsecurity.testcontroller.mapper.SysUserMapper;
import com.wps.springsecurity.testcontroller.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        SysUser user =sysUserMapper.selectUserByName(username);
        UserDetails userDetails=createLoginUser(user);
        return userDetails;
    }

    public UserDetails createLoginUser(SysUser user)
    {
        LoginUser loginUser=new LoginUser();
        Set<String> roles= permissionService.getRolePermission(user);
        loginUser.setSysUser(user);
        loginUser.setPermissions(roles);
        return loginUser;
    }
}
