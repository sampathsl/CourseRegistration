<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <title>Student Course Registration Form</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>Create an account</title>

    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${contextPath}/resources/js/html5shiv.min.js"></script>
    <script src="${contextPath}/resources/js/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<div class="container">

    <form:form method="POST" modelAttribute="courseRegistrationForm" class="form-signin">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <h2 class="form-signin-heading">Course Registration</h2>

        <form:hidden path="id" class="form-control"></form:hidden>

        <spring:bind path="forName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="forName" class="form-control" placeholder="First Name"
                            autofocus="true"></form:input>
                <form:errors path="forName"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="surName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="surName" class="form-control" placeholder="Last Name"
                            autofocus="true"></form:input>
                <form:errors path="surName"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="emailAddress">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="emailAddress" class="form-control" placeholder="Email Address"
                            autofocus="true"></form:input>
                <form:errors path="emailAddress"></form:errors>
            </div>
        </spring:bind>

        <c:forEach items="${allCourseList}" var="course" varStatus="loop">

            <div class="form-group ${not empty courseListError ? 'has-error' : ''}">

                <c:set var="contains" value="false" />
                <c:forEach var="courseChecked" items="${courseCheckedSet}">
                    <c:if test="${courseChecked eq course}">
                        <c:set var="contains" value="true" />
                    </c:if>
                </c:forEach>

                <c:choose>
                    <c:when test="${contains}">
                        <input type="checkbox"  id="courselist${loop.index}" name="courselist" value="${course}" checked/>${course}
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox"  id="courselist${loop.index}" name="courselist" value="${course}"/>${course}
                    </c:otherwise>
                </c:choose>

            </div>

        </c:forEach>

        <c:if test="${ not empty courseListError }">
            <span class="has-error">
                    ${courseListError}
            </span>
        </c:if>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>

    </form:form>

</div>

<!-- /container -->
<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>