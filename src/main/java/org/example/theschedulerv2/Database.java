//package org.example.theschedulerv2;
//
//import org.example.fasterxml.jackson.databind.ObjectMapper;
//import edu.gcc.subjecttochange.controllers.CoursesController;
//
//import org.example.apache.commons.dbutils.QueryRunner;
//import org.apache.commons.dbutils.handlers.MapListHandler;
//
//
//import java.sql.*;
//import java.util.List;
//
//public class Database {
//    public static Connection connect() {
//        String url = "jdbc:sqlite:C://sqlite/database.db";
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(url);
//        } catch (SQLException e) {
//        }
//        return conn;
//    }
//
//    public static <T> List<T> query(String sql, Class<T> serializeTo, Object... args) throws SQLException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return new QueryRunner()
//                .query(Database.connect(), sql, new MapListHandler(), args)
//                .stream()
//                .map(response -> objectMapper.convertValue(response, serializeTo))
//                .toList();
//    }
//
//    public static int update(String sql, Object... args) throws SQLException {
//        return new QueryRunner().update(Database.connect(), sql, args);
//    }
//}
