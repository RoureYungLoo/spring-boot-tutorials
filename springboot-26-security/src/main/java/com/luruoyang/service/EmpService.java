package com.luruoyang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luruoyang.entity.Emp;
import com.luruoyang.dto.EmpLoginDTO;
import com.luruoyang.vo.EmpLoginVO;

public interface EmpService extends IService<Emp> {
    /**
     * 管理员登录
     * @param empLoginDTO 管理员登录表单
     * @return 员工登录VO
     */
    EmpLoginVO empLogin(EmpLoginDTO empLoginDTO);
    
}
