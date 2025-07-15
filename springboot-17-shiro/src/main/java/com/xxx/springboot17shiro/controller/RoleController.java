package com.xxx.springboot17shiro.controller;

import com.xxx.springboot17shiro.entity.Role;
import com.xxx.springboot17shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public Role saveRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable("id") Integer id) {
        if (roleService.deleteRole(id)) {
            return "success";
        }
        return "failed";
    }

    @PutMapping
    public String updateRole(@RequestBody Role role) {
        if (roleService.updateRole(role)) {
            return "success";
        }
        return "failed";
    }

    @GetMapping("/{id}")
    public Role getById(@PathVariable int id) {
        return roleService.getRoleById(id);
    }

    @GetMapping
    public List<Role> getAll() {
        return roleService.getRoleList();
    }
}
