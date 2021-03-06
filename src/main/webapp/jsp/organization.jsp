<%-- 
    Document   : organization
    Created on : Jul 29, 2018, 2:45:50 AM
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
        <title>Superperson Tracker - Organization Information</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/superperson2.css" rel="stylesheet">
    </head>
    <body>
	<div class="container-fluid">
	    <h1>Organization Information</h1>
	    <hr/>
	    <div class="panel panel-default">
		<div class="panel-heading">Name</div>
		<div class="panel-body">
		    <c:out value="${organization.name}"/>
		</div>
	    </div>
	    <div class="panel panel-default">
		<div class="panel-heading">Head</div>
		<div class="panel-body">
		    <c:out value="${organization.head}"/>
		</div>
	    </div>
	    <div class="panel panel-default">
		<div class="panel-heading">Description</div>
		<div class="panel-body">
		    <c:out value="${organization.description}"/>
		</div>
	    </div>
	    <div class="panel panel-default">
		<div class="panel-heading">Location</div>
		<div class="panel-body">
		    <c:out value="${organization.location.name}"/>
		</div>
	    </div>
	    <a class="btn btn-primary" href="${pageContext.request.contextPath}/organizations">
		Back
	    </a>
	</div>
	
	<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
