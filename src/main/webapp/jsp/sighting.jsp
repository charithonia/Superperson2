<%-- 
    Document   : sighting
    Created on : Jul 29, 2018, 2:45:24 AM
    Author     : main
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Superperson Tracker - Sighting Information</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/superperson2.css" rel="stylesheet">
    </head>
    <body>
	<div class="container-fluid">
	    <h1>Sighting Information</h1>
	    <hr/>
	    <div class="panel panel-default">
		<div class="panel-heading">Timestamp</div>
		<div class="panel-body">
		    <c:out value="${sighting.timestamp}"/>
		</div>
	    </div>
	    <div class="panel panel-default">
		<div class="panel-heading">Location</div>
		<div class="panel-body">
		    <c:out value="${sighting.location.name}"/>
		</div>
	    </div>
	    <div class="panel panel-default">
		<div class="panel-heading">User</div>
		<div class="panel-body">
		    <c:out value="${sighting.user.username}"/>
		</div>
	    </div>
	    <div class="panel panel-default">
		<div class="panel-heading">Superpeople</div>
		<div class="panel-body">
		    <c:forEach var="superperson" items="${sighting.superpersons}" varStatus="status">
			<c:out value="${superperson.name}"/>
			<c:if test="${!status.last}"><br/></c:if>
		    </c:forEach>
		</div>
	    </div>
	    <a class="btn btn-primary" href="${pageContext.request.contextPath}/sightings">
		Back
	    </a>
	</div>
	
	<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
