package org.example.theschedulerv2;
import java.sql.*;
import java.util.ArrayList;

public class Search {
    public static void main(String[] args) {
        search("COMP", 424,'c', "", 0, 'c', 'c', ' ', 'c', 'c', "", "", "", "");
    }

    public static ArrayList<Class> search(String Department, int CourseNumber, char Section, String CourseName, int NumCredits, char M, char T, char W, char R, char F, String StartTime, String EndTime, String InstructorLast, String InstructorFirst){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Establishing the connection
            conn = DriverManager.getConnection("jdbc:mysql:src/Database/database.sql");

            // Creating a statement
            stmt = conn.createStatement();

            // Executing a query
            rs = stmt.executeQuery("SELECT * FROM your_table WHERE Department = 'COMP'");

            // Processing the results
            while (rs.next()) {
                // Retrieve data from the result set
                String department = rs.getString("Department");
                int courseNumber = rs.getInt("CourseNumber");
                String section = rs.getString("Section");
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
