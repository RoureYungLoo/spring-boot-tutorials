package com.xxx.springboot12transaction.mapper;

import com.xxx.springboot12transaction.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    int save(User user);

    int deleteById(Long id);

    int updateById(User user);

    User getById(Long id);

    List<User> getAll();

    @Select("select * FROM tb_user WHERE user_name like CONCAT(#{userName},'%') ")
    @ResultType(User.class)
    List<User> getByUsername(User user);
}
