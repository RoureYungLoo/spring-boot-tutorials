package com.luruoyang.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.luruoyang.entity.*;
import com.luruoyang.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author luruoyang
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private RoleMapper roleMapper;
  @Autowired
  private PermissionMapper permissionMapper;
  @Autowired
  private UserRoleMapper userRoleMapper;
  @Autowired
  private RolePermissionMapper rolePermissionMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // 根据用户名查找用户
    User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
    // 用户的角色
    List<UserRole> userRoleList = userRoleMapper.selectList(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, user.getId()));
    // 用户的权限
    Set<Permission> permissionList = new HashSet<>();
    // 遍历用户的角色
    userRoleList.forEach(userRole -> {
      // 取出每个角色的权限
      List<RolePermission> rolePermissions = rolePermissionMapper.selectList(Wrappers.<RolePermission>lambdaQuery()
          .eq(RolePermission::getRoleId, userRole.getRoleId()));
      // 把权限添加到权限列表中
      rolePermissions.forEach(rp -> {
        permissionList.add(permissionMapper.selectById(rp.getPermissionId()));
      });
    });

    // 权限转换
    List<GrantedAuthority> authorities = new ArrayList<>();
    permissionList.forEach(permission -> {
      authorities.add(new SimpleGrantedAuthority(permission.getCode()));
    });

    //返回 UserDetails
    return new org.springframework.security.core.userdetails.User(
        user.getUsername(),
        user.getPassword(),
        true,
        true,
        true,
        true,
        authorities
    );

  }
}
