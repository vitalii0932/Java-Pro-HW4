package com.example.demo.repository;

import com.example.demo.model.Role;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class RoleRepository {
    private static final String INSERT_QUERY = "INSERT INTO roles(name) VALUES (?);";
    private static final String DELETE_QUERY = "DELETE FROM roles WHERE id = ?;";
    private static final String UPDATE_QUERY = "UPDATE roles SET name = ? WHERE id = ?;";
    private static final String GET_ALL_QUERY = "SELECT * FROM roles;";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM roles WHERE id = ?;";
    private static final String GET_BY_NAME_QUERY = "SELECT * FROM roles WHERE name = ?;";
    private static final String COUNT_QUERY = "SELECT COUNT(roles) from roles;";

    public RoleRepository() {
    }

    public boolean add(Role role) {
        var connection = DataSource.getConnection();
        try(var statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(1, role.getName());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean delete(Role role) {
        var connection = DataSource.getConnection();
        try(var statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, role.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean update(Role role) {
        var connection = DataSource.getConnection();
        try(var statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, role.getName());
            statement.setInt(2, role.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Role> getAll() {
        var connection = DataSource.getConnection();
        try(var statement = connection.prepareStatement(GET_ALL_QUERY)) {
            try(var set = statement.executeQuery()) {
                var queryResult = new ArrayList<Role>();
                while (set.next()) {
                    var role = new Role();
                    role.setId(set.getInt(1));
                    role.setName(set.getString(2));
                    queryResult.add(role);
                }
                return queryResult;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Role> getById(Role role) {
        var connection = DataSource.getConnection();
        try(var statement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            statement.setInt(1, role.getId());
            try(var set = statement.executeQuery()) {
                var queryResult = new ArrayList<Role>();
                while (set.next()) {
                    var nwRole = new Role();
                    nwRole.setId(set.getInt(1));
                    nwRole.setName(set.getString(2));
                    queryResult.add(nwRole);
                }
                return queryResult;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Role> getByName(Role role) {
        var connection = DataSource.getConnection();
        try(var statement = connection.prepareStatement(GET_BY_NAME_QUERY)) {
            statement.setString(1, role.getName());
            try(var set = statement.executeQuery()) {
                var queryResult = new ArrayList<Role>();
                while (set.next()) {
                    var nwRole = new Role();
                    nwRole.setId(set.getInt(1));
                    nwRole.setName(set.getString(2));
                    queryResult.add(nwRole);
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
        return "RoleRepository{}";
    }
}

