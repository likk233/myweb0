package com.example.myweb0;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseUtil {
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DATABASE_NAME = "ls1";
    private static final String ROOT_JDBC_URL = "jdbc:mysql://" + HOST + ":" + PORT
            + "/?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&characterEncoding=UTF-8";
    private static final String JDBC_URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE_NAME
            + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&characterEncoding=UTF-8";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "11111111";

    private DatabaseUtil() {
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public static void initializeDatabase() throws SQLException {
        String createDatabaseSql = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME
                + " CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci";
        String createClassTableSql = """
                CREATE TABLE IF NOT EXISTS class (
                    cid INT PRIMARY KEY AUTO_INCREMENT,
                    caption VARCHAR(32) NOT NULL
                )
                """;
        String createStudentTableSql = """
                CREATE TABLE IF NOT EXISTS student (
                    sid INT PRIMARY KEY AUTO_INCREMENT,
                    gender CHAR(1) NOT NULL,
                    class_id INT NOT NULL,
                    sname VARCHAR(32) NOT NULL,
                    CONSTRAINT fk_class FOREIGN KEY (class_id) REFERENCES class (cid)
                )
                """;

        try (Connection connection = DriverManager.getConnection(ROOT_JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute(createDatabaseSql);
        }

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createClassTableSql);
            statement.execute(createStudentTableSql);
        }
    }
}
