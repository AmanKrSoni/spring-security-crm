<%--
  Created by IntelliJ IDEA.
  User: cvt11
  Date: 18/01/19
  Time: 5:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Users List</title>
</head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Users Record</h2>
    </div>
</div>
<div id="container">
    <div id="content">

        <input type="button" value="Add Users"
               onclick="window.location.href='showFormForAdd'"; return false;
               class="add-button"
        />

        <%--add out html table here--%>
        <table>
            <tr>
                <th>UserName</th>
                <th>Password</th>
                <th>Enabled</th>
            </tr>
            <%--loop to iterate content--%>
            <c:forEach var="temp" items="${users}">

                <%--construct an update based on username --%>
                <c:url var="updateLink" value="/users/showFormForUpdate">
                    <c:param name="username" value="${temp.username}"/>
                </c:url>

                <%--construct an delete based on username--%>
                <c:url var="deleteLink" value="/users/delete">
                    <c:param name="username" value="${temp.username}"/>
                </c:url>

                <tr>
                    <td>${temp.username}</td>
                    <td>${temp.password}</td>
                    <td>${temp.enabled}</td>
                    <td>
                        <a href="${updateLink}">Update</a>
                        | <a href="${deleteLink}"
                             onclick="if(!(confirm('Are you sure you want to delete this user?'))) return false">Delete</a>
                    </td>
                </tr>

            </c:forEach>
        </table>
    </div>
</div>

<a href="${pageContext.request.contextPath}/home">back to Home</a>
<br>

<form:form action="${pageContext.request.contextPath}/logout" method="post">
    <input type="submit" value="Logout"/>

</form:form>
</body>
</html>
