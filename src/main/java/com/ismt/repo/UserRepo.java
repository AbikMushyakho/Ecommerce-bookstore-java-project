package com.ismt.repo;


import com.ismt.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {


    public void insert(User user) throws SQLException {
        final String INSERT_USERS_SQL = "INSERT INTO user" +
                "  (id, name, username, password, role) VALUES " +
                " (?, ?, ?, ?, ?);";


        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole());


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            JDBCUtils.printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }


    public List<User> listUsers() {

        final String QUERY = "select * from user ";

        // using try-with-resources to avoid closing resources (boiler plate code)

        // Step 1: Establishing a Connection
        List<User> users = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            // preparedStatement.setInt(1, 1);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.


            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                User user = new User(id, name, username, password, role);
                users.add(user);

            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return users;
    }

    public void delete(int id) {
        final String QUERY = "delete from user where id=? ";

        try (Connection connection = JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setInt(1, id);
            // preparedStatement.setInt(1, 1);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

            // Step 4: Process the ResultSet object.
         /*  while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                String password = rs.getString("password");
                System.out.println(id + "," + name + "," + email + "," + country + "," + password);
           }*/
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    public User getById(int id) {
        final String QUERY = "select * from user where id =?";
        User user = null;
        try (Connection connection = JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            // while (rs.next()) {
            rs.next();
            int userId = rs.getInt("id");
            String name = rs.getString("name");
            String username = rs.getString("email");
            String password = rs.getString("password");
            String role = rs.getString("country");
            System.out.println(id + "," + name + "," + username + "," + password + "," + role);
            user = new User(userId, name, username, password, role);

            //}
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return user;
    }

    public void edit(User user) {

        final String UPDATE_USERS_SQL = "update user set name = ?, username=?,password=?,role=? where id = ?;";
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setInt(5, user.getId());


            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            JDBCUtils.printSQLException(e);
        }

    }

    public User checkLogin(String username, String password) throws SQLException,
            ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE username = ? and password = ?";
        User user = null;
        try {
            Connection connection = JDBCUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                user = new User();
                user.setName(result.getString("name"));
                user.setRole(result.getString("role"));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;


    }
}
