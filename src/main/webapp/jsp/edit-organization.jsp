<%-- 
    Document   : edit-organization
    Created on : Aug 8, 2018, 6:53:25 PM
    Author     : main
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Superperson Tracker - Edit Organization</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/superperson2.css" rel="stylesheet">
    </head>
    <body>
	<div class="container-fluid">
	    <h1>Edit Organization</h1>
	    <hr/>
	    <sf:form role="form" action="edit-organization" method="POST" modelAttribute="organization">
		<sf:hidden path="id"/>
		<div class="form-group">
		    <label for="name">Name</label>
		    <sf:input class="form-control" type="text" title="name" path="name"
			      required="required" maxlength="100"/>
		</div>
		<div class="form-group">
		    <label for="head">Head</label>
		    <sf:input class="form-control" type="text" title="head" path="head"
			      maxlength="50"/>
		</div>
		<div class="form-group">
		    <label for="description">Description</label>
		    <sf:textarea class="form-control" rows="10" title="description" path="description"
			      maxlength="1000"/>
		</div>
		<div class="form-group">
		    <label for="location">Location</label>
		    <sf:select class="form-control" title="location" path="locationId">
			<sf:options items="${locations}" itemValue="id" itemLabel="name"/>
		    </sf:select>
		</div>
		<div class="form-group">
		    <input class="btn btn-primary" type="submit" value="Save"/>
		    <a class="btn btn-primary" href="${pageContext.request.contextPath}/organizations">
			Cancel
		    </a>
		</div>
	    </sf:form>
	</div>
	
	<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
