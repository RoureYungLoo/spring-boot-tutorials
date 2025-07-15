package com.xxx.springboot17shiro.controller;

import com.xxx.springboot17shiro.entity.Perm;
import com.xxx.springboot17shiro.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perm")
public class PermController {

    @Autowired
    private PermService permService;

    @PostMapping
    public Perm savePerm(@RequestBody Perm perm) {
        return permService.savePerm(perm);
    }

    @DeleteMapping("/{id}")
    public String deletePerm(@PathVariable("id") Integer id) {
        if (permService.deletePerm(id)) {
            return "success";
        }
        return "failed";
    }

    @PutMapping
    public String updatePerm(@RequestBody Perm perm) {
        if (permService.updatePerm(perm)) {
            return "success";
        }
        return "failed";
    }

    @GetMapping("/{id}")
    public Perm getById(@PathVariable int id) {
        return permService.getPermById(id);
    }

    @GetMapping
    public List<Perm> getAll() {
        return permService.getPermList();
    }
}