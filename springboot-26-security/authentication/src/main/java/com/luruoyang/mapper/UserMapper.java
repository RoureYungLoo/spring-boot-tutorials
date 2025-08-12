package com.luruoyang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luruoyang.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author luruoyang
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
