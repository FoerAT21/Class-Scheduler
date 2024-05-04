package org.example.theschedulerv2;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Search {
    

    private static Connection connect() {
        // SQLite's connection string
        String url = "jdbc:sqlite:src/main/resources/Database/database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
        if (StartTime != null){query.append(" /startTime_int >= " + StartTime);}
        if (EndTime != null){query.append(" /endTime_int <= " + EndTime);}
        if (InstructorName != null){query.append(" /professorName LIKE  '%" + InstructorName + "%'");}
        String q = query.toString();

        // If nothing was put in, set the search to generate everything.
        if(q.equals("SELECT * FROM course WHERE")) q = "SELECT * FROM course";
        return q.replaceFirst("/", "").replaceAll("/", "AND ");
    }

    public static ArrayList<Class> search(String Department, String CourseNumber, String Section, String CourseName, String NumCredits, String DaysOfWeek, String StartTime, String EndTime, String InstructorName){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        // ArrayList to hold classes
        ArrayList<Class> results = new ArrayList<>();

        try {
            // Establishing the connection
            conn = connect();

            // Creating a statement
            stmt = conn.createStatement();

            // Executing a query
            String query = formatQuery(Department, CourseNumber, Section, CourseName, NumCredits, DaysOfWeek, StartTime, EndTime, InstructorName);
            rs = stmt.executeQuery(query);

            // Processing the results
            while (rs.next()) {
                // Retrieve data from the result set
                String department = rs.getString("department");
                int courseNumber = rs.getInt("number");
                String section = rs.getString("section");
                String courseID = department + " " + courseNumber + " " + section;
                String courseName = rs.getString("name");
                int numCredits = rs.getInt("hours");
                String daysOfWeek = rs.getString("weekday");
                int startTime = rs.getInt("startTime_int");
                int endTime = rs.getInt("endTime_int");
                String instructorName = rs.getString("professorName");
                int index = rs.getInt("id");
                results.add(new Class(courseID, courseName, numCredits, daysOfWeek, startTime, endTime, instructorName, department, index));
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
        return results;
    }

    public static Class getClassByID(int id) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Class c = null;

        try {
            // Establishing the connection
            conn = connect();

            // Creating a statement
            stmt = conn.createStatement();

            // Executing a query
            String query = "SELECT * FROM course WHERE id = " + id;

            rs = stmt.executeQuery(query);

            // Processing the results
            while (rs.next()) {
                // Retrieve data from the result set
                String department = rs.getString("department");
                int courseNumber = rs.getInt("number");
                String section = rs.getString("section");
                String courseID = department + " " + courseNumber + " " + section;
                String courseName = rs.getString("name");
                int numCredits = rs.getInt("hours");
                String daysOfWeek = rs.getString("weekday");
                int startTime = rs.getInt("startTime_int");
                int endTime = rs.getInt("endTime_int");
                String instructorName = rs.getString("professorName");
                int index = rs.getInt("id");
                c = new Class(courseID, courseName, numCredits, daysOfWeek, startTime, endTime, instructorName, department, index);
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
        return c;
    }
}
