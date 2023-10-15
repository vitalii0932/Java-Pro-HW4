package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public boolean add(Role role) {
        return roleRepository.add(role);
    }

    public boolean delete(Role role) {
        return roleRepository.delete(role);
    }

    public boolean update(Role role) {
        return roleRepository.update(role);
    }

    public String getAll() {
        var users = roleRepository.getAll();
        return getResp(users);
    }

    public String getById(Role role) {
        var users = roleRepository.getById(role);
        return getResp(users);
    }

    public String getByName(Role role) {
        var users = roleRepository.getByName(role);
        return getResp(users);
    }

    public String getCount() {
        return "Ролей внесено в базу -> " + String.valueOf(roleRepository.getCount());
    }

    private String getResp(List<Role> roles) {
        String result = "id\t, name\n";
        for(var item : roles) {
            result += String.format("%d\t, %s\n", item.getId(), item.getName());
        }
        return result;
    }
}
