package com.xxx.springboot17shiro.service.impl;

import com.xxx.springboot17shiro.entity.Perm;
import com.xxx.springboot17shiro.mapper.PermMapper;
import com.xxx.springboot17shiro.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PermServiceImpl implements PermService {

    @Autowired
    private PermMapper permMapper;

    @Override
    public Perm savePerm(Perm perm) {
        if(permMapper.savePerm(perm)==1){
            return perm;
        }
        return null;
    }

    @Override
    public boolean deletePerm(int id) {
        return permMapper.delete(id) > 0;
    }

    @Override
    public boolean updatePerm(Perm perm) {
        return permMapper.updatePerm(perm) > 0;
    }

    @Override
    public Perm getPermById(int id) {
        return permMapper.getById(id);
    }

    @Override
    public List<Perm> getPermList() {
        return permMapper.getAll();
    }
}
