package com.xxx.springboot17shiro.service.impl;

import com.xxx.springboot17shiro.entity.Role;
import com.xxx.springboot17shiro.mapper.RoleMapper;
import com.xxx.springboot17shiro.mapper.RoleMapper;
import com.xxx.springboot17shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role saveRole(Role role) {
        if (roleMapper.saveRole(role) == 1) {
            return role;
        }
        return null;
    }

    @Override
    public boolean deleteRole(int id) {
        return roleMapper.delete(id) > 0;
    }

    @Override
    public boolean updateRole(Role role) {
        return roleMapper.updateRole(role) > 0;
    }

    @Override
    public Role getRoleById(int id) {
        return roleMapper.getById(id);
    }

    @Override
    public List<Role> getRoleList() {
        return roleMapper.getAll();
    }
}
