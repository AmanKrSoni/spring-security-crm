<%--
  Created by IntelliJ IDEA.
  User: cvt11
  Date: 19/01/19
  Time: 1:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add Customer</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/add-customer-style.css"/>

</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>CRM - Customer Relationship Manager</h2>
        </div>
    </div>
    <div id="container">
        <h3>Save Customer</h3>
        <form:form action="save" modelAttribute="customer" method="post">

            <%--need to associate data to customer id--%>
            <form:hidden path="id"/>
            <table>
                <tbody>
                <tr>
                    <td><label>First Name :</label></td>
                    <td><form:input path="firstName"/></td>
                </tr>

                <tr>
                    <td><label>Last Name :</label></td>
                    <td><form:input path="lastName"/></td>
                </tr>

                <tr>
                    <td><label>Email :</label></td>
                    <td><form:input path="email"/></td>
                </tr>

                <tr>
                    <td><label></label></td>
                    <td><input type="submit" value="Save" class="save"/></td>
                </tr>
                </tbody>
            </table>

        </form:form>

        <div style="clear; both;"></div>
        <p>
            <a href="${pageContext.request.contextPath}/customers/list">Back to List</a>
        </p>

    </div>

</body>
</html>
