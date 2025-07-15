package com.xxx.springboot17shiro.mapper;

import com.xxx.springboot17shiro.entity.Perm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermMapper {
    int savePerm(Perm perm);

    int delete(int id);

    int updatePerm(Perm perm);

    Perm getById(int id);

    List<Perm> getAll();
}