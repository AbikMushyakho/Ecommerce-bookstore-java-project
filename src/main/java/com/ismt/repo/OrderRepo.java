package com.ismt.repo;

import com.ismt.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepo {


    public void insert(Order order) throws SQLException {
        final String INSERT_USERS_SQL = "INSERT INTO orders" +
                "  (id, order_number, total) VALUES " +
                " (?, ?, ?);";
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setString(2, order.getOrder_number());
            preparedStatement.setString(2, order.getTotal());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            JDBCUtils.printSQLException(e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
    }

    public List<Order> listOrder() {

        final String QUERY = "select * from orders ";

        // using try-with-resources to avoid closing resources (boiler plate code)

        // Step 1: Establishing a Connection
        List<Order> order = new ArrayList<>();
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
                String order_number = rs.getString("order_number");
                String total = rs.getString("total");
                int customer_id = rs.getInt("customer_id");
                System.out.println(id + "," + order_number + "," + total+" "+customer_id);
                order.add(new Order(id, order_number, total,customer_id));
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return order;
    }

    public void delete(int id) {
        final String QUERY = "delete from orders where id=? ";

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
                String order_number = rs.getString("order_number");
                String total = rs.getString("total");
                String country = rs.getString("country");
                String password = rs.getString("password");
                System.out.println(id + "," + order_number + "," + total + "," + country + "," + password);
           }*/
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    public Order getById(int id) {
        final String QUERY = "select * from orders where id =?";
        Order order = null;
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
            String order_number = rs.getString("order_number");
            String total = rs.getString("total");
            System.out.println(id + "," + order_number + "," + total);
            order = new Order(categoryId, order_number, total);

            //}
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return order;
    }

    public void edit(Order order) {

        final String UPDATE_USERS_SQL = "update orders set order_number = ?, total=? where id = ?;";
        // Step 1: Establishing a Connection

        System.out.println("trying to establish connection");
        try (Connection connection = JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setString(2, order.getOrder_number());
            preparedStatement.setString(3, order.getTotal());

            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            // print SQL exception information
            JDBCUtils.printSQLException(e);
        }

    }
}

