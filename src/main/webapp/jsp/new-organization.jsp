<%-- 
    Document   : new-organization
    Created on : Aug 8, 2018, 2:55:50 AM
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
        <title>Superperson Tracker - New Organization</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/superperson2.css" rel="stylesheet">
    </head>
    <body>
	<div class="container-fluid">
	    <h1>New Organization</h1>
	    <hr/>
	    <div class="alert alert-info">
		Location selection is temporary pending a rework of the location system.
	    </div>
	    <form role="form" action="create-organization" method="POST" modelAttribute="organization">
		<div class="form-group">
		    <label class="control-label" for="location">Location</label>
		    <select class="form-control" multiple="true" title="location" name="locationId">
			<c:forEach var="location" items="${locations}">
			    <option value="${location.id}">
				<c:out value="${location.name}"/>
			    </option>
			</c:forEach>
		    </select>
		</div>
		<div class="form-group">
		    <label class="control-label" for="name">Name</label>
		    <input class="form-control" type="text" title="name" name="name"
			   required maxlength="100">
		</div>
		<div class="form-group">
		    <label class="control-label">Head</label>
		    <input class="form-control" type="text" title="head" name="head"
			   maxlength="50"/>
		</div>
		<div class="form-group">
		    <label class="control-label">Description</label>
		    <textarea class="form-control" rows="10" title="description" name="description"
			      maxlength="1000"></textarea>
		</div>
		<div class="form-group">
		    <input class="btn btn-primary" type="submit" value="Create"/>
		    <a class="btn btn-primary" href="${pageContext.request.contextPath}/organizations">
			Cancel
		    </a>
		</div>
	    </form>
	</div>
	
	<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
