package com.example.demo.controllers;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody User user) {
        if(userService.add(user)) {
            return new ResponseEntity<>("It's all good man!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("You screw up(", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody User user) {
        if(userService.delete(user)) {
            return new ResponseEntity<>("It's all good man!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("You screw up(", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody User user) {
        if(userService.update(user)) {
            return new ResponseEntity<>("It's all good man!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("You screw up(", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping()
    public String getAll() {
        return userService.getAll();
    }

    @PostMapping("/getById")
    public String getById(@RequestBody User user) {
        return userService.getById(user);
    }

    @PostMapping("/getByName")
    public String getByName(@RequestBody User user) {
        return userService.getByName(user);
    }

    @PostMapping("/getByRole")
    public String getByRole(@RequestBody User user) {
        return userService.getByRole(user);
    }

    @PostMapping("/getByNameAndRole")
    public String getByNameAndRole(@RequestBody User user) {
        return userService.getByNameAndRole(user);
    }

    @GetMapping("/getCount")
    public String getCount() {
        return userService.getCount();
    }
}
