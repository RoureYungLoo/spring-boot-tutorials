package com.xxx.springboot19commonprojectarchitect.controller;

import com.xxx.springboot19commonprojectarchitect.entity.JsonResult;
import com.xxx.springboot19commonprojectarchitect.entity.User;
import com.xxx.springboot19commonprojectarchitect.exception.BusinessErrorException;
import com.xxx.springboot19commonprojectarchitect.exception.BusinessMsgEnum;
import com.xxx.springboot19commonprojectarchitect.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.omg.CORBA.IRObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "用户模块", value = "用户模块接口列表")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(tags = "获取用户", value = "根据ID获取用户信息")
    @GetMapping("/{id}")
    public JsonResult<User> getUserById(@PathVariable Integer id) {

        if (id == 999) {
            int a = 1 / 0;
        }

        if (id == 666) {
            throw new BusinessErrorException(BusinessMsgEnum.ERR_ID_ILLEGAL.getMsg(), BusinessMsgEnum.ERR_ID_ILLEGAL.getCode());
        }

/*
        User user = User.builder()
                .id(id)
                .username("user-" + id)
                .password(DigestUtils.md5DigestAsHex(("user-" + id).getBytes()))
                .build();
*/

        User user = userService.getById(id);
        return new JsonResult<>(user, 200, "success");


    }

    @GetMapping
    public JsonResult<List<User>> getAll() {
        List<User> users = userService.getAll();
        return new JsonResult<>(users, 200, "查询成功");
    }
}
