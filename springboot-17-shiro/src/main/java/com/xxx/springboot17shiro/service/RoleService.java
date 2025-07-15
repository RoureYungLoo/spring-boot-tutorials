package com.xxx.springboot17shiro.service;

import com.xxx.springboot17shiro.entity.Role;

import java.util.List;

public interface RoleService {
    Role saveRole(Role role);

    boolean deleteRole(int id);

    boolean updateRole(Role role);

    Role getRoleById(int id);

    List<Role> getRoleList();
}
