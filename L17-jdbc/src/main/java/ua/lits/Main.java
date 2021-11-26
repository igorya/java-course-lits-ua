package ua.lits;

import java.sql.*;

public class Main {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/course_lits";

    public static void main(String[] args) {

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet;

            // Fill table if it's empty
            resultSet = statement.executeQuery("SELECT count(*) FROM User");
            boolean bool;
            if (((bool = resultSet.next()) && resultSet.getInt(1) <= 0) || !bool) {
                fillTable(connection);
            }

            // Select by age
            System.out.println("Select according to the age criteria >= 18:");
            dumpUserResultSet(statement.executeQuery(
                    "SELECT * FROM User WHERE age >= 18")
            );
            System.out.println();

            // Select by Lviv city
            System.out.println("Select Lviv settlers:");
            dumpUserResultSet(statement.executeQuery(
                    "SELECT * FROM User WHERE LOWER(address) LIKE '%lviv%'")
            );

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Dump User table as plain text
     *
     * @param resultSet
     * @throws SQLException
     */
    private static void dumpUserResultSet(ResultSet resultSet) throws SQLException {

        while (resultSet.next()) {
            System.out.printf("%-10s %-15s %d %s%n",
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getInt("age"),
                    resultSet.getString("address")
            );
        }
    }

    /**
     * Fill table from the String array
     *
     * @param connection
     * @throws SQLException
     */
    private static void fillTable(Connection connection) throws SQLException {
        String[] strData = {
                "John\tDoe\t65\tUSA, Seattle",
                "John\tSnow\t44\tUSA, San Francisco",
                "Bill\tGates\t65\tUSA, Redmond",
                "Elon\tMusk\t65\tUSA, Florida",
                "Steven\tTyler\t21\tUSA, Boston",
                "John3\tDoe\t12\tUSA, Boston",
                "John4\tDoe\t13\tUSA, Boston",
                "Ivan\tA\t11\tUkraine, Lviv",
                "Danylo\tB\t12\tUkraine, LVIV",
                "Ostap\tC\t18\tUkraine, Lviv"
        };
        String[] row;
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO User (firstName, lastName, age, address) value(?, ?, ?, ?)"
        );

        for(String str : strData) {
            row = str.split("\t", 4);
            statement.setString(1, row[0]);
            statement.setString(2, row[1]);
            statement.setString(3, row[2]);
            statement.setString(4, row[3]);
            statement.executeUpdate();
        }
        System.out.println("The number of records that have been inserted into the table="+ strData.length);
        statement.close();
    }
}
