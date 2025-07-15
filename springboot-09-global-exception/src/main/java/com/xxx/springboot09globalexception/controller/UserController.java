package com.xxx.springboot09globalexception.controller;

import com.xxx.springboot09globalexception.BusinessMsgEnum;
import com.xxx.springboot09globalexception.exception.BusinessException;
import com.xxx.springboot09globalexception.exception.DiyException;
import com.xxx.springboot09globalexception.utils.JsonResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/get")
    public JsonResult list(
            @RequestParam("name") String username,
            @RequestParam("pwd") String password
    ) {
        return new JsonResult();
    }

    @GetMapping("/{id}")
    public JsonResult getUserById(@PathVariable("id") Integer useId) {
        useId = null;
        useId.intValue();
        return new JsonResult();
    }

    @GetMapping("/business")
    public void testBusinessException() {
        throw new BusinessException(BusinessMsgEnum.UID_ERROR);
    }

    @GetMapping("/input")
    public void testInput(Integer userId) {
        if (userId < 0) {
            throw new DiyException("userId不能小于0");
        } else {
            throw new DiyException("userID合法" + userId);
        }
    }
}
