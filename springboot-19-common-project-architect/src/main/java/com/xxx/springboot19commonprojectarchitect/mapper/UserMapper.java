package com.xxx.springboot19commonprojectarchitect.mapper;

import com.xxx.springboot19commonprojectarchitect.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    List<User> findAll();

    @Select("select `id`,`user_name`,`age`,`create_time`,`update_time` from tb_user where id = #{id}")
    User findById(int id);
}
