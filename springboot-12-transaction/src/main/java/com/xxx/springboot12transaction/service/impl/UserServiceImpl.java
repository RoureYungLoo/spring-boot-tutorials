package com.xxx.springboot12transaction.service.impl;

import com.xxx.springboot12transaction.entity.User;
import com.xxx.springboot12transaction.mapper.UserMapper;
import com.xxx.springboot12transaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional // 开启事务，默认遇到【运行时异常】和【错误】才会回滚
//    @Transactional(rollbackFor = Exception.class)
//    public int save(User user) throws SQLException {
    public int save(User user) {
        user.setUserName("tx测试" + new Random().nextInt(100));

        /* 插入数据 */
        int saved = userMapper.save(user);

        /* 模拟异常 */
//        int infinity = 1 / 0;

        /* 抛出一个【非运行时异常】，默认不会回滚，除非指定rollbackFor */
        try {
            throw new SQLException("数据库错误");
        } catch (SQLException e) {
            // throw new RuntimeException(e); // 把异常吃了
            // 继续往上抛运行时异常, 可以正常回滚
            throw  new RuntimeException(e);
        }
//         return saved;
    }

    /* 注意并发情况下的事务问题：事务的范围比锁的范围大，不推荐在service方法上使用synchronized关键字，推荐在调用service的地方添加synchronized关键字 */

    @Override
    public int update(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public int delete(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public User getById(Long id) {
        return userMapper.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public List<User> getByName(User user) {
        return userMapper.getByUsername(user);
    }
}
