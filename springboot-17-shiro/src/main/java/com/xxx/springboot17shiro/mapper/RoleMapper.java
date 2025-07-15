package com.xxx.springboot17shiro.mapper;

import com.xxx.springboot17shiro.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    int saveRole(Role role);

    int delete(int id);

    int updateRole(Role role);

    Role getById(int id);

    List<Role> getAll();
}