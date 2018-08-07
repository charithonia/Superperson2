<%-- 
    Document   : new-sighting
    Created on : Jul 29, 2018, 2:45:30 AM
    Author     : main
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Superperson Tracker - New Sighting</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/superperson2.css" rel="stylesheet">
    </head>
    <body>
	<div class="container-fluid">
	    <h1>New Sighting</h1>
	    <hr/>
	    <div class="alert alert-info">
		User and location selection are temporary placeholders.
	    </div>
	    <form role="form" action="create-sighting" method="POST" modelAttribute="sighting">
		<div class="form-group">
		    <label class="control-label" for="user">User</label>
		    <select class="form-control" name="userId">
			<c:forEach var="user" items="${users}">
			    <option value="${user.id}">
				<c:out value="${user.username}"/>
			    </option>
			</c:forEach>
		    </select>
		</div>
		<div class="form-group">
		    <label class="control-label" for="location">Location</label>
		    <select class="form-control" name="locationId">
			<c:forEach var="location" items="${locations}">
			    <option value="${location.id}">
				<c:out value="${location.name}"/>
			    </option>
			</c:forEach>
		    </select>
		</div>
		<div class="form-group">
		    <label class="control-label" for="superpeople">Superpeople</label>
		    <select class="form-control" multiple="true" name="superpersonIds">
			<c:forEach var="superperson" items="${superpersons}">
			    <option value="${superperson.id}">
				<c:out value="${superperson.name}"/>
			    </option>
			</c:forEach>
		    </select>
		</div>
		<div class="form-group">
		    <input class="btn btn-primary" type="submit" value="Report"/>
		    <a href="${pageContext.request.contextPath}/sightings"
		       class="btn btn-primary">
			Cancel
		    </a>
		</div>
	    </form>
	</div>
	
	<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
