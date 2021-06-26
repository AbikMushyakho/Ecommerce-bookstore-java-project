package com.ismt.repo;

import com.ismt.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepo {


    public void insert(Category category) throws SQLException {
        final String INSERT_USERS_SQL = "INSERT INTO category" +
                "  (id, cat_name, cat_description) VALUES " +
                " (?, ?, ?);";
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setInt(1, category.getId());
            preparedStatement.setString(2, category.getCat_name());
            preparedStatement.setString(2, category.getCat_description());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            JDBCUtils.printSQLException(e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
    }

    public List<Category> listCategories() {

        final String QUERY = "select * from category ";

        // using try-with-resources to avoid closing resources (boiler plate code)

        // Step 1: Establishing a Connection
        List<Category> categories = new ArrayList<>();
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
                String name = rs.getString("cat_name");
                String description = rs.getString("cat_description");
                System.out.println(id + "," + name + "," + description);
                categories.add(new Category(id, name, description));
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return categories;
    }

    public void delete(int id) {
        final String QUERY = "delete from category where id=? ";

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
                String description = rs.getString("description");
                String country = rs.getString("country");
                String password = rs.getString("password");
                System.out.println(id + "," + name + "," + description + "," + country + "," + password);
           }*/
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    public Category getById(int id) {
        final String QUERY = "select * from category where id =?";
        Category category = null;
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
            int categoryId = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("cat_description");
            System.out.println(id + "," + name + "," + description);
            category = new Category(categoryId, name, description);

            //}
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return category;
    }

    public void edit(Category category) {

        final String UPDATE_USERS_SQL = "update category set name = ?, cat_description=? where id = ?;";
        // Step 1: Establishing a Connection

        System.out.println("trying to establish connection");
        try (Connection connection = JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            preparedStatement.setInt(1, category.getId());
            preparedStatement.setString(2, category.getCat_name());
            preparedStatement.setString(3, category.getCat_description());

            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            // print SQL exception information
            JDBCUtils.printSQLException(e);
        }

    }
}

