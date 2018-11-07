package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            if (!tableName.equals("todos") && !tableName.equals("codesnippets")) {
                newNote += ", '" + other + "'";
            }
            newNote += ", null)";
            addToDo(body, other);

            //System.out.println(newNote);
            System.out.println("body: " + body + "other: " + other);

            stmt.execute(newNote);

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error inserting quote: " + e.getMessage());
        }
    }

    @Override
    public void addToDo(String title, String todo) {
        int todoid = 0;
        title = "";
        try {
            Statement stmt = conn.createStatement();

//            todoid = stmt.getGeneratedKeys().getInt(1);
            ResultSet retrieved = conn.createStatement().executeQuery(
                    "SELECT * FROM todos");

            while ((retrieved.next())) {
                todoid = retrieved.getInt("id") + 1;
            }

            System.out.println("todoid: " + todoid);

            String listItem = "INSERT INTO todoitems VALUES (null, " + "'" +
                    todo + "', false, '" + todoid + "', current_timestamp)";
            System.out.println(listItem);
            stmt.execute(listItem);
        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Error inserting quote: " + e.getMessage());
        }

    }

    public void updateToDo(String title, String todo) {
        int todolistid = 0;
        try {
            Statement stmt = conn.createStatement();
            String listItem = "INSERT INTO " + "todoitems" + " VALUES (null, " + false +
                    ", '" + todo + "', " + todolistid + ")";

//            todolistid = stmt.executeUpdate(  );

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Update Todo List: " + e.getMessage());
        }

        try {
            Statement stmt = conn.createStatement();

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Item Complete: " + e.getMessage());
        }

    }

    @Override
    public List<NotePair> viewNotes(String tableName) {

        try {
            ResultSet retrieved = conn.createStatement().executeQuery(
                    "SELECT * FROM '" + tableName + "'");

            List<NotePair> pairs = new ArrayList<>();

            //want to add the quotes and authors to String multi-dimensional array
            switch (tableName) {

                case "quotes":
                    while (retrieved.next()) {
                        String body = retrieved.getString("quote");
                        String other = retrieved.getString("author");

                        pairs.add(new NotePair(tableName, body, other));
                    }
                case "hyperlinks":
                    while (retrieved.next()) {
                        String body = retrieved.getString("title");
                        String other = retrieved.getString("hyperlink");

                        pairs.add(new NotePair(tableName, body, other));
                    }
                case "codesnippets":
                    while ((retrieved.next())) {
                        String body = retrieved.getString("codesnippet");

                        pairs.add(new NotePair(tableName, body, null));

                        System.out.println("Model Pairs:" + pairs);
                    }
                case "todos":
                    while ((retrieved.next())) {
                        String body = retrieved.getString("title");
                        String other = retrieved.getString("todo");

                        pairs.add(new NotePair(tableName, body, other));
                        System.out.println("Todo pairs: " + pairs);
                    }
                case "todoitems":
                    while ((retrieved.next())) {
                        String other = retrieved.getString("todo");

                        pairs.add(new NotePair(tableName, null, other));
                    }
            }
            return pairs;

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    @Override
    public List<NotePair> viewToDos() {
        try {
            ResultSet retrieved = conn.createStatement().executeQuery(
                    "SELECT todo, title FROM todoitems INNER JOIN todos ON todos.id = todoitems.listid");

            List<NotePair> pairs = new ArrayList<>();

            while (retrieved.next()) {
                String body = retrieved.getString("title");
                String other = retrieved.getString("todo");

                System.out.println("view title: " + body);
                System.out.println("view todo: " + other);

                pairs.add(new NotePair("todoitems", body, other));
                System.out.println("Pair: " + pairs);

            }

            return pairs;

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}