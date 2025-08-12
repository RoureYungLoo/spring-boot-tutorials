package com.luruoyang.controller;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.luruoyang.dto.EmpLoginDTO;
import com.luruoyang.vo.EmpLoginVO;
import com.luruoyang.utils.BaseContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin // 允许跨域
@RestController
@RequestMapping("admin/emp")
@Tag(name = "员工管理接口")
@Slf4j
@RequiredArgsConstructor
public class EmpController {

    private final EmpService empService;
    private final RedisUtil redisUtil; // redis工具类，换成自己的

    /**
     * 员工登录
     * @param empLoginDTO 员工登录信息
     * @return  统一返回结果
     */
    @PostMapping("/login")
    @Operation(summary = "员工登录")
    public Result<EmpLoginVO> login(@Validated @RequestBody EmpLoginDTO empLoginDTO) {
        log.info("员工：{}，登录成功", empLoginDTO.getUsername());
        EmpLoginVO empLoginVO = empService.empLogin(empLoginDTO);

        return Result.success(empLoginVO);
    }

    /**
     * 员工退出登录
     * @return  统一返回结果
     */
    @PostMapping("/logout")
    @Operation(summary = "员工退出登录")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        log.info("员工ID：{}，退出登录", BaseContext.getCurrentId());

        String token = request.getHeader("Authorization");
        if (ObjectUtils.isEmpty(token)) { // header没有token
            token = request.getParameter("Authorization");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            // 清除上下文
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            // 清理redis
            redisUtil.del("token_" + token);
            // 清理ThreadLocal
            BaseContext.removeCurrentId();

        }
        return Result.success();
    }
}
