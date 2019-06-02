<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/20
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>注册界面</title>
  </head>
  <body>
  <form action="${pageContext.request.contextPath}/RegisterServlet" method="post">
    用户名:<input type="text" name="username"><br>
    密码：<input type="password" name="password"><br>
    邮箱:<input type="text" name="email"><br>
    <input type="submit" value="注册">
  </form>
  </body>
</html>
