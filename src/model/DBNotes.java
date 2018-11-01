package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBNotes implements INotesData {
    private Connection conn;

    public DBNotes() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:notesdb.sqlite");

            //JDBC quirk
            Class.forName("org.sqlite.JDBC"); //fix our project classpath
            System.out.println("Connected to DB");
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(
                    "Cannot connect to DB: " + e.getMessage());
        }
    }

    @Override
    public void addNotes(String tableName, String body, String other) {
        try {
            Statement stmt = conn.createStatement();

            //execute() is for insert, update or delete
            String newNote = "INSERT INTO " + tableName + " VALUES (null,'" + body + "'";
            if (!tableName.equals("todos")) {
                newNote += ", '" + other + "'";
            }
            newNote += ")";
            System.out.println("body: " + body + "other: " + other);

            stmt.execute(newNote);
            if (tableName.equals("todos")) {
                addToDo(body, other);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error inserting quote: " + e.getMessage());
        }
    }

    public void addToDo(String title, String todo) {
        int todoid;
        try {
            Statement stmt = conn.createStatement();

            todoid = stmt.getGeneratedKeys().getInt(1);

            System.out.println("todo id: " + todoid);

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error inserting quote: " + e.getMessage());
        }

        try {
            Statement stmt = conn.createStatement();

            String listItem = "INSERT INTO " + "todoitems" + " VALUES (null, " + false +
                    ", '" + todo + "', " + todoid + ")";

            stmt.execute(listItem);
        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error inserting quote: " + e.getMessage());
        }

    }

    public String viewNotes(String tableName, String body, String other) {
        try {
            Statement stmt = conn.createStatement();

            String selectedQuote = "SELECT * FROM quotes";

            //System.out.println("body: " + body + "other: " + other);

            stmt.execute(selectedQuote);

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error inserting quote: " + e.getMessage());
        }
        return body;
    }
}