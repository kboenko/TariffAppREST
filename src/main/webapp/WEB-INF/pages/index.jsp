<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="TariffApp.domain.User" %>
<%@ page import="TariffApp.dao.UserRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application); %>
<% UserRepository hostRepository = context.getBean(UserRepository.class); %>
<% for (User item : hostRepository.findAll()) { %>
<p> <%= item.getName() %> </p>
<%}%>
</body>
</html>
