package com.example.myweb0;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "studentServlet", value = "/student")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String sname = request.getParameter("sname");
        String gender = request.getParameter("gender");
        String classIdValue = request.getParameter("classId");

        try {
            DatabaseUtil.initializeDatabase();
            int classId = Integer.parseInt(classIdValue);
            int affectedRows = insertStudent(sname, gender, classId);

            String message = "学生新增成功，影响行数: " + affectedRows
                    + "，sname=" + sname + "，gender=" + gender + "，classId=" + classId;
            System.out.println(message);

            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("""
                    <!DOCTYPE html>
                    <html>
                    <head><title>新增结果</title></head>
                    <body>
                        <h2>学生新增成功</h2>
                        <p>结果已经输出到控制台。</p>
                        <a href="student-form.jsp">继续新增</a>
                        <br>
                        <a href="students">查看学生列表</a>
                    </body>
                    </html>
                    """);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "班级编号必须是数字");
        } catch (SQLException e) {
//            logSqlException("新增学生失败", e);
//            writeErrorPage(response, e);
            throw new ServletException("Failed to insert student", e);
        }
    }

    private int insertStudent(String sname, String gender, int classId) throws SQLException {
        String sql = "INSERT INTO student(sname, gender, class_id) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, sname);
            preparedStatement.setString(2, gender);
            preparedStatement.setInt(3, classId);
            return preparedStatement.executeUpdate();
        }
    }

//    private void logSqlException(String message, SQLException e) {
//        System.err.println(message);
//        SQLException current = e;
//        while (current != null) {
//            System.err.println("SQLState=" + current.getSQLState()
//                    + ", ErrorCode=" + current.getErrorCode()
//                    + ", Message=" + current.getMessage());
//            current = current.getNextException();
//        }
//    }
//
//    private void writeErrorPage(HttpServletResponse response, SQLException e) throws IOException {
//        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        response.setContentType("text/html;charset=UTF-8");
//
//        try (PrintWriter writer = response.getWriter()) {
//            writer.println("""
//                    <!DOCTYPE html>
//                    <html>
//                    <head><title>新增失败</title></head>
//                    <body>
//                        <h2>学生新增失败</h2>
//                    """);
//            writer.println("<p>错误信息：" + escapeHtml(e.getMessage()) + "</p>");
//            writer.println("<p>请同时查看 Tomcat 控制台中的 SQLState 和 ErrorCode。</p>");
//            writer.println("<a href=\"student-form.jsp\">返回表单</a>");
//            writer.println("""
//                    </body>
//                    </html>
//                    """);
//        }
//    }
//
//    private String escapeHtml(String value) {
//        if (value == null) {
//            return "";
//        }
//        return value
//                .replace("&", "&amp;")
//                .replace("<", "&lt;")
//                .replace(">", "&gt;")
//                .replace("\"", "&quot;");
//    }
}
