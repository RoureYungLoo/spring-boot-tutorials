package com.luruoyang.user;

import com.luruoyang.dto.EmpLoginDTO;
import com.luruoyang.vo.EmpLoginVO;

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
    // 需要 "ems:employee:list" 权限才能访问
    @PreAuthorize("hasAuthority('ems:employee:list')")
    @GetMapping("/page")
    public Result<PageResult> getList(EmpPageDTO empPageDTO) {
        return Result.success(empService.pageQuery(empPageDTO));
    }

    // 需要多个权限中的任意一个
    @PreAuthorize("hasAnyAuthority('ems:employee:add','ems:employee:edit')")
    @PostMapping
    public Result add(@RequestBody EmpAddDTO empAddDTO) {
        empService.add(empAddDTO);
        return Result.success();
    }
}
