package com.luruoyang.user;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luruoyang.entity.Emp;
import com.luruoyang.mapper.EmpMapper;
import com.luruoyang.mapper.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final EmpMapper empMapper;
    private final MenuMapper menuMapper;

    /**
     * 根据用户名查询用户信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username.isEmpty()){
            throw new InternalAuthenticationServiceException("");
        }
        //  根据用户名查询用户信息
        QueryWrapper<Emp> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Emp emp = empMapper.selectOne(wrapper);
        // 判断是否查到用户 如果没查到抛出异常
        if (ObjectUtil.isNull(emp)){
            throw new UsernameNotFoundException("");
        }
        // 2.赋权操作 查询数据库
        List<String> list = menuMapper.getMenuByUserId(emp.getId());

        for (String s : list) {
            System.out.println(s);
        }

        return new EmpLogin(emp, list);
    }
}
