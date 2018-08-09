<%-- 
    Document   : superperson
    Created on : Aug 8, 2018, 6:24:29 PM
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
        <title>Superperson Tracker - Superperson Information</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/superperson2.css" rel="stylesheet">
    </head>
    <body>
	<div class="container-fluid">
	    <h1>Superperson Information</h1>
	    <hr/>
	    <div class="panel panel-default">
		<div class="panel-heading">Name</div>
		<div class="panel-body">
		    <c:out value="${superperson.name}"/>
		</div>
	    </div>
	    <div class="panel panel-default">
		<div class="panel-heading">Description</div>
		<div class="panel-body">
		    <c:out value="${superperson.description}"/>
		</div>
	    </div>
	    <div class="panel panel-default">
		<div class="panel-heading">Powers</div>
		<div class="panel-body">
		    <c:forEach var="power" items="${superperson.powers}" varStatus="status">
			<c:out value="${power.name}"/>
			<c:if test="${!status.last}"><br/></c:if>
		    </c:forEach>
		</div>
	    </div>
	    <div class="panel panel-default">
		<div class="panel-heading">Organizations</div>
		<div class="panel-body">
		    <c:forEach var="organization" items="${superperson.organizations}" varStatus="status">
			<c:out value="${organization.name}"/>
			<c:if test="${!status.last}"><br/></c:if>
		    </c:forEach>
		</div>
	    </div>
	    <a class="btn btn-primary" href="${pageContext.request.contextPath}/superpeople">
		Back
	    </a>
	</div>

	<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
