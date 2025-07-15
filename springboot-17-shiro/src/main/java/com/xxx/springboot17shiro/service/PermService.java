package com.xxx.springboot17shiro.service;

import com.xxx.springboot17shiro.entity.Perm;

import java.util.List;

public interface PermService {
    Perm savePerm(Perm perm);

    boolean deletePerm(int id);

    boolean updatePerm(Perm perm);

    Perm getPermById(int id);

    List<Perm> getPermList();
}
