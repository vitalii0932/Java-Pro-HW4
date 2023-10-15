package com.example.demo.repository;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class UserRepository {
    private static final String INSERT_QUERY = "INSERT INTO users(name, role_id) VALUES (?, ?);";
    private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?;";
    private static final String UPDATE_QUERY = "UPDATE users SET name = ?, role_id = ? WHERE id = ?;";
    private static final String GET_ALL_QUERY = "SELECT * FROM users;";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?;";
    private static final String GET_BY_NAME_QUERY = "SELECT * FROM users WHERE name = ?;";
    private static final String GET_BY_ROLE_QUERY = "SELECT * FROM users WHERE role_id = ?;";
    private static final String GET_BY_NAME_AND_ROLE = "SELECT * FROM users WHERE name = ? AND role_id = ?;";
    private static final String COUNT_QUERY = "SELECT COUNT(users) from users;";

    public UserRepository() {
    }

    public boolean add(User user) {
        var connection = DataSource.getConnection();
        try(var statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(1, user.getName());
            statement.setInt(2, user.getRoleId());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean delete(User user) {
        var connection = DataSource.getConnection();
        try(var statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, user.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean update(User user) {
        var connection = DataSource.getConnection();
        try(var statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, user.getName());
            statement.setInt(2, user.getRoleId());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<User> getAll() {
        var connection = DataSource.getConnection();
        try(var statement = connection.prepareStatement(GET_ALL_QUERY)) {
            try(var set = statement.executeQuery()) {
                var queryResult = new ArrayList<User>();
                while (set.next()) {
                    var user = new User();
                    user.setId(set.getInt(1));
                    user.setName(set.getString(2));
                    user.setRoleId(set.getInt(3));
                    queryResult.add(user);
                }
                return queryResult;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<User> getById(User user) {
        var connection = DataSource.getConnection();
        try(var statement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            statement.setInt(1, user.getId());
            try(var set = statement.executeQuery()) {
                var queryResult = new ArrayList<User>();
                while (set.next()) {
                    var nwUser = new User();
                    nwUser.setId(set.getInt(1));
                    nwUser.setName(set.getString(2));
                    nwUser.setRoleId(set.getInt(3));
                    queryResult.add(nwUser);
                }
                return queryResult;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<User> getByName(User user) {
        var connection = DataSource.getConnection();
        try(var statement = connection.prepareStatement(GET_BY_NAME_QUERY)) {
            statement.setString(1, user.getName());
            try(var set = statement.executeQuery()) {
                var queryResult = new ArrayList<User>();
                while (set.next()) {
                    var nwUser = new User();
                    nwUser.setId(set.getInt(1));
                    nwUser.setName(set.getString(2));
                    nwUser.setRoleId(set.getInt(3));
                    queryResult.add(nwUser);
                }
                return queryResult;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<User> getByRole(User user) {
        var connection = DataSource.getConnection();
        try(var statement = connection.prepareStatement(GET_BY_ROLE_QUERY)) {
            statement.setInt(1, user.getRoleId());
            try(var set = statement.executeQuery()) {
                var queryResult = new ArrayList<User>();
                while (set.next()) {
                    var nwUser = new User();
                    nwUser.setId(set.getInt(1));
                    nwUser.setName(set.getString(2));
                    nwUser.setRoleId(set.getInt(3));
                    queryResult.add(nwUser);
                }
                return queryResult;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<User> getByNameAndRole(User user) {
        var connection = DataSource.getConnection();
        try(var statement = connection.prepareStatement(GET_BY_NAME_AND_ROLE)) {
            statement.setString(1, user.getName());
            statement.setInt(2, user.getRoleId());
            try(var set = statement.executeQuery()) {
                var queryResult = new ArrayList<User>();
                while (set.next()) {
                    var nwUser = new User();
                    nwUser.setId(set.getInt(1));
                    nwUser.setName(set.getString(2));
                    nwUser.setRoleId(set.getInt(3));
                    queryResult.add(nwUser);
                }
                return queryResult;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public int getCount() {
        var connection = DataSource.getConnection();
        try(var statement = connection.prepareStatement(COUNT_QUERY)) {
            try(var set = statement.executeQuery()) {
                set.next();
                int queryResult = set.getInt(1);
                return queryResult;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String toString() {
        return "UserRepository{}";
    }
}
