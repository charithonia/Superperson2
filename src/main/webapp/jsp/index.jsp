<%-- 
    Document   : home
    Created on : Jul 16, 2018, 2:53:12 PM
    Author     : main
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Spring MVC Application from Archetype</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
		    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
		    <li role="presentation"><a href="${pageContext.request.contextPath}/sightings.jsp">Sightings</a></li>
		    <li role="presentation"><a href="${pageContext.request.contextPath}/superpeople.jsp">Superpeople</a></li>
		    <li role="presentation"><a href="${pageContext.request.contextPath}/organizations.jsp">Organizations</a></li>
		    <li role="presentation"><a href="#">Administration</a></li>
                </ul>    
            </div>
            <h2>Home Page</h2>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>