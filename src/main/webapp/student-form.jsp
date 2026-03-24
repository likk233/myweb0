<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>新增学生</title>
</head>
<body>
<h1>新增学生</h1>
<form action="student" method="post">
    <div>
        <label for="sname">姓名：</label>
        <input id="sname" type="text" name="sname" required>
    </div>
    <br>
    <div>
        <label for="gender">性别：</label>
        <select id="gender" name="gender" required>
            <option value="男">男</option>
            <option value="女">女</option>
        </select>
    </div>
    <br>
    <div>
        <label for="classId">班级编号：</label>
        <input id="classId" type="number" name="classId" min="1" required>
    </div>
    <br>
    <input type="submit" value="提交">
</form>
<br>
<a href="students">查看学生列表</a>
</body>
</html>
