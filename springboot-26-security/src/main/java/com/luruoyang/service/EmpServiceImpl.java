package com.luruoyang.service;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luruoyang.constants.JwtClaimsConstant;
import com.luruoyang.entity.Emp;
import com.luruoyang.properties.JwtProperties;
import com.luruoyang.user.EmpLogin;
import com.luruoyang.dto.EmpLoginDTO;
import com.luruoyang.vo.EmpLoginVO;
import com.luruoyang.mapper.EmpMapper;
import com.luruoyang.utils.BaseContext;
import com.luruoyang.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {

    private final EmpMapper empMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtProperties jwtProperties;
    private final RedisUtil redisUtil;
    /**
     * 管理员登录
     */
    public EmpLoginVO empLogin(EmpLoginDTO empLoginDTO) {
        String username = empLoginDTO.getUsername();
        String password = empLoginDTO.getPassword();

        // 1. 封装用户登录表单，创建未认证Authentication对象
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        // 2. 进行校验
        Authentication authenticate = authenticationManager.authenticate(authentication);
        // 3. 获取用户信息
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        EmpLogin empLogin = (EmpLogin) authenticate.getPrincipal();
        Emp emp = empLogin.getEmp();
        if (emp.getStatus() == 1){
            throw new RuntimeException("账号被禁用");
        }
        log.info("员工 {} 登录成功", empLogin.getEmp().getName());

        // 登录成功，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        // 使用fastjson的方法，把对象转换成json字符串
        String loginEmpString = JSON.toJSONString(empLogin);
        claims.put(JwtClaimsConstant.EMP_LOGIN, loginEmpString);
        String token = JwtUtil.createJWT(
                jwtProperties.getSecretKey(),
                jwtProperties.getTtl(),
                claims);

        // 存储redis白名单
        String tokenKey = "token_" + token;
        redisUtil.set(tokenKey, token, jwtProperties.getTtl()/1000);

        BaseContext.setCurrentId(emp.getId());


        //3、返回实体对象
        return EmpLoginVO.builder()
                .id(emp.getId())
                .token(token)
                .username(emp.getUsername())
                .name(emp.getName())
                .build();

    }
}
