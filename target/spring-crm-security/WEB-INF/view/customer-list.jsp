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
    <title>Customer List</title>
</head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>CRM - Customer Relationship Manager</h2>
        </div>
    </div>
    <div id="container">
        <div id="content">

            <input type="button" value="Add Customer"
                onclick="window.location.href='showFormForAdd'"; return false;
                   class="add-button"
            />

            <%--add out html table here--%>
            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
                <%--loop to iterate content--%>
                <c:forEach var="temp" items="${customers}">
                    <%--construct an update based on customer id--%>
                    <c:url var="updateLink" value="/customers/showFormForUpdate">
                        <c:param name="customerId" value="${temp.id}"/>
                    </c:url>

                    <%--construct an delete based on customer id--%>
                    <c:url var="deleteLink" value="/customers/delete">
                        <c:param name="customerId" value="${temp.id}"/>
                    </c:url>

                    <tr>
                        <td>${temp.firstName}</td>
                        <td>${temp.lastName}</td>
                        <td>${temp.email}</td>
                        <td>
                            <a href="${updateLink}">Update</a>
                            | <a href="${deleteLink}"
                                    onclick="if(!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
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
