package com.example.myweb0;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "studentQueryServlet", value = "/students")
public class StudentQueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            DatabaseUtil.initializeDatabase();
            List<Student> students = queryStudents();
            printStudentsToConsole(students);
            request.setAttribute("students", students);
            request.getRequestDispatcher("/student-list.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Failed to query students", e);
        }
    }

    private List<Student> queryStudents() throws SQLException {
        String sql = """
                SELECT s.sid, s.sname, s.gender, s.class_id, c.caption
                FROM student s
                LEFT JOIN class c ON s.class_id = c.cid
                ORDER BY s.sid DESC
                """;
        List<Student> students = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getInt("sid"),
                        resultSet.getString("sname"),
                        resultSet.getString("gender"),
                        resultSet.getInt("class_id"),
                        resultSet.getString("caption")
                ));
            }
        }

        return students;
    }

    private void printStudentsToConsole(List<Student> students) {
        System.out.println("学生列表查询结果：");
        if (students.isEmpty()) {
            System.out.println("当前没有学生数据");
            return;
        }

        for (Student student : students) {
            System.out.println("sid=" + student.getSid()
                    + ", sname=" + student.getSname()
                    + ", gender=" + student.getGender()
                    + ", classId=" + student.getClassId()
                    + ", className=" + student.getClassCaption());
        }
    }
}
