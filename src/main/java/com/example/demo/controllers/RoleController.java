package com.example.demo.controllers;

import com.example.demo.model.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody Role role) {
        if(roleService.add(role)) {
            return new ResponseEntity<>("It's all good man!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("You screw up(", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Role role) {
        if(roleService.delete(role)) {
            return new ResponseEntity<>("It's all good man!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("You screw up(", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody Role role) {
        if(roleService.update(role)) {
            return new ResponseEntity<>("It's all good man!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("You screw up(", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping()
    public String getAll() {
        return roleService.getAll();
    }

    @PostMapping("/getById")
    public String getById(@RequestBody Role role) {
        return roleService.getById(role);
    }

    @PostMapping("/getByName")
    public String getByName(@RequestBody Role role) {
        return roleService.getByName(role);
    }

    @GetMapping("/getCount")
    public String getCount() {
        return roleService.getCount();
    }
}
