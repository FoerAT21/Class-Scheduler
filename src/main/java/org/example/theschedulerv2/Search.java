package org.example.theschedulerv2;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Search {
    public static void main(String[] args) {
        search("COMP", null, null, null, null, null, "9:00 AM", null,null);
    }

    private static Connection connect() {
        // SQLite's connection string
        String url = "jdbc:sqlite:C://sqlite/database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Successfully connected to database!");
        return conn;
    }

    private static String formatQuery(String Department, String CourseNumber, String Section, String CourseName, String NumCredits, String DaysOfWeek, String StartTime, String EndTime, String InstructorName){
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM course WHERE");
        if (Department != null){query.append(" /department = '" + Department + "'");}
        if (CourseNumber != null){query.append(" /number = '" + CourseNumber + "'");}
        if (Section != null){query.append(" /section = '" + Section + "'");}
        if (CourseName != null){query.append(" /name LIKE '%" + CourseName + "%'");}
        if (NumCredits != null){query.append(" /hours = '" + NumCredits + "'");}
        if (DaysOfWeek != null){query.append(" /weekday = '" + DaysOfWeek + "'");}
        if (StartTime != null){query.append(" /startTime >= '" + StartTime + "'");}
        if (EndTime != null){query.append(" /TIME(endTime) <= TIME(" + EndTime + ")");}
        if (InstructorName != null){query.append(" /professorName LIKE  '%" + InstructorName + "%'");}
        String q = query.toString();
        return q.replaceFirst("/", "").replaceAll("/", "AND ");
    }

    public static ArrayList<Class> search(String Department, String CourseNumber, String Section, String CourseName, String NumCredits, String DaysOfWeek, String StartTime, String EndTime, String InstructorName){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Establishing the connection
            conn = connect();

            // Creating a statement
            stmt = conn.createStatement();

            // Executing a query
            String query = formatQuery(Department, CourseNumber, Section, CourseName, NumCredits, DaysOfWeek, StartTime, EndTime, InstructorName);
            System.out.println(query);
            rs = stmt.executeQuery(query);

            // Processing the results
            while (rs.next()) {
                // Retrieve data from the result set
                String department = rs.getString("department");
                int courseNumber = rs.getInt("number");
                String section = rs.getString("section");
                // Process the data
                System.out.println("Dep: " + department + ", CourseNum: " + courseNumber + "Section" + section);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
