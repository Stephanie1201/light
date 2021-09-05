package edu.episen.si.ing1.pds.backend.server.model;

import edu.episen.si.ing1.pds.backend.server.pool.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Crud {
    private static String s;
    private static DataSource dataSource = DataSource.getInstance();
    // This Class permits to execute the CRUD operations (create, read, update, delete)
    // For every method of this class, we recover a connection of the ConnectionPool and
    // once the operation is completed, it is put back into the ConnectionPool

    public static String select(){
        Connection connection = dataSource.receiveConnection();
        StringBuilder resultQuery = new StringBuilder();

        Statement statement;
        try {
            //l'objet de type statement va permettre d'effectuer les requetes et les mise à jour en BD

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Student");

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String firstname = resultSet.getString(2);
                String lastname = resultSet.getString(3);
                resultQuery.append("ID : " +id + ", Firstname: " + firstname + ",Lastname : "+ lastname + ".");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dataSource.putConnection(connection);
        return resultQuery.toString();
    }
public static String insert (String firstName, String lastName){
        Connection connection = dataSource.receiveConnection();
        int numberLinsAdded = 0;
        try{
            Statement statement = connection.createStatement();
            numberLinsAdded = statement.executeUpdate("INSERT INTO Student (firstname, lastname) VALUES ('" + firstName + "', '" + lastName + "')" );
            s = "Result of the insert Request : " + numberLinsAdded + "line added";

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dataSource.putConnection(connection);
        return s;
}

public static String delete(String firstName, String lastName) {
        Connection connection = dataSource.receiveConnection();
        int numberbLinesDeleted = 0;
        try {
            Statement statement = connection.createStatement();
            numberbLinesDeleted = statement.executeUpdate("DELETE FROM Student WHERE firstname= '" + firstName + "' and lastname = '" + lastName + "' ");
            s = "Result of the delete request : " + numberbLinesDeleted + " line(s) deleted. ";
            //System.out.println("Result of the delete request : " + nbLinesDeleted + " line(s) deleted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dataSource.putConnection(connection);
        return s;
    }

    public static String update(String firstName, String lastName, String newFirstName, String newLastName) {
        Connection connection = dataSource.receiveConnection();
        int numberLinesUpdated = 0;
        try {
            Statement statement = connection.createStatement();
            numberLinesUpdated = statement.executeUpdate("UPDATE Student SET firstname= '" + newFirstName + "', lastname = '" + newLastName + "' WHERE firstname = '" + firstName + "' and lastname = '" + lastName + "'");
            s = "Result of the update request : " + numberLinesUpdated + " line(s) updated. ";
            //System.out.println("Result of the update request : " + nbLinesUpdated + " line(s) updated");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dataSource.putConnection(connection);
        return s;
    }

    public static void main(String[] args) {

    }
}
