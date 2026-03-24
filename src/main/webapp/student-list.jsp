<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.myweb0.Student" %>
<%
    List<Student> students = (List<Student>) request.getAttribute("students");
%>
<!DOCTYPE html>
<html>
<head>
    <title>学生列表</title>
</head>
<body>
<h1>学生列表</h1>
<a href="student-form.jsp">新增学生</a>
<br>
<br>
<table border="1" cellspacing="0" cellpadding="8">
    <tr>
        <th>学号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>班级编号</th>
        <th>班级名称</th>
    </tr>
    <%
        if (students == null || students.isEmpty()) {
    %>
    <tr>
        <td colspan="5">当前没有学生数据</td>
    </tr>
    <%
        } else {
            for (Student student : students) {
    %>
    <tr>
        <td><%= student.getSid() %></td>
        <td><%= student.getSname() %></td>
        <td><%= student.getGender() %></td>
        <td><%= student.getClassId() %></td>
        <td><%= student.getClassCaption() == null ? "" : student.getClassCaption() %></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
