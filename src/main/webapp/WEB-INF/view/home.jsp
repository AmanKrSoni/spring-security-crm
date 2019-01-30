<%--
  Created by IntelliJ IDEA.
  User: cvt11
  Date: 20/01/19
  Time: 1:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>Welocme ,To Homepage</h1>
<hr>

    <%--To display userId currenty loggedIn spring security provide some predefined tags --%>
    <p>
        User : <security:authentication property="principal.username"/>
        <br><br>
    </p>
    <p>
        Role(s) : <security:authentication property="principal.authorities" />
        <br><br><hr>
    </p>

<%--
&lt;%&ndash;<security:authorize access="hasRole('MANAGER')">
        <br><hr><br>
        <p>
                &lt;%&ndash;Add link for manager roles &ndash;%&gt;
            <a href="${pageContext.request.contextPath}/managers">ManagerCredentials</a>
            (Only for Manager)
        </p>
    </security:authorize>

    <security:authorize access="hasRole('ADMIN')">
        <br><hr><br>
        <p>
                &lt;%&ndash;Add link for Admin roles &ndash;%&gt;
            <a href="${pageContext.request.contextPath}/admins">AdminCredentials</a>
            (Only for Admin)
        </p>
    </security:authorize>
--%>

    <h3>
        <p>
            <a href="${pageContext.request.contextPath}/users/list">for User Information</a>
        </p>
        <br>

        <p>
            <a href="${pageContext.request.contextPath}/customers/list">for Customer Information</a>
        </p>
    </h3>

    <form:form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="submit" value="Logout"/>

    </form:form>
</body>
</html>
