package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService() {
    }

    public boolean add(User user) {
        return userRepository.add(user);
    }

    public boolean delete(User user) {
        return userRepository.delete(user);
    }

    public boolean update(User user) {
        return userRepository.update(user);
    }

    public String getAll() {
        var users = userRepository.getAll();
        return getResp(users);
    }

    public String getById(User user) {
        var users = userRepository.getById(user);
        return getResp(users);
    }

    public String getByName(User user) {
        var users = userRepository.getByName(user);
        return getResp(users);
    }

    public String getByRole(User user) {
        var users = userRepository.getByRole(user);
        return getResp(users);
    }

    public String getByNameAndRole(User user) {
        var users = userRepository.getByNameAndRole(user);
        return getResp(users);
    }

    public String getCount() {
        return "Користувачів внесено в базу -> " + String.valueOf(userRepository.getCount());
    }

    private String getResp(List<User> users) {
        String result = "id\t, name\t, role id\n";
        for(var item : users) {
            result += String.format("%d\t, %s\t, %d\n", item.getId(), item.getName(), item.getRoleId());
        }
        return result;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "userRepository=" + userRepository +
                '}';
    }
}
