package com.ismt.repo;

import com.ismt.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepo {


    public void insert(Product product) throws SQLException {
        final String INSERT_USERS_SQL = "INSERT INTO product" +
                "  (id, name, description,price) VALUES " +
                " (?, ?, ?);";
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getPrice());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            JDBCUtils.printSQLException(e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
    }

    public List<Product> listProduct() {

        final String QUERY = "select * from product ";

        // using try-with-resources to avoid closing resources (boiler plate code)

        // Step 1: Establishing a Connection
        List<Product> products = new ArrayList<>();
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
                String description = rs.getString("description");
                String price = rs.getString("price");
                System.out.println(id + "," + name + "," + description+" "+price);
                products.add(new Product(id, name, description,price));
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return products;
    }

    public void delete(int id) {
        final String QUERY = "delete from product where id=? ";

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

    public Product getById(int id) {
        final String QUERY = "select * from product where id =?";
        Product product = null;
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
            int ProductId = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String price = rs.getString("price");
            System.out.println(id + "," + name + "," + description+ "," + price);
            product = new Product(ProductId, name, description,price);

            //}
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return product;
    }

    public void edit(Product product) {

        final String UPDATE_USERS_SQL = "update product set name = ?, description=? where id = ?;";
        // Step 1: Establishing a Connection

        System.out.println("trying to establish connection");
        try (Connection connection = JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getPrice());

            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            // print SQL exception information
            JDBCUtils.printSQLException(e);
        }

    }
}

