package com.luruoyang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.awt.*;
import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

  @Select("SELECT t1.perms FROM menu t1\n" +
      "     INNER JOIN role_menu t2 ON t2.menu_id = t1.id\n" +
      "     INNER JOIN role t3 ON t3.id = t2.role_id\n" +
      "     INNER JOIN emp_role t4 ON t4.role_id = t3.id\n" +
      "     INNER JOIN emp t5 ON t5.id = t4.emp_id\n" +
      "WHERE t5.id = #{id} AND t1.perms IS NOT NULL And t5.status = 0 And t3.status = 0 And t1.status = 0;")
  List<String> getMenuByUserId(Long id);
}
