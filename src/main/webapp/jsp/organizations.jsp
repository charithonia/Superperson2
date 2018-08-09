<%-- 
    Document   : organizations
    Created on : Jul 29, 2018, 2:45:44 AM
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
        <title>Superperson Tracker - Organizations</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/superperson2.css" rel="stylesheet">
    </head>
    <body>
	<div class="container-fluid">
	    <nav class="navbar navbar-default">
		<div class="container-fluid">
		    <div class="navbar-header">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/">Superperson Tracker</a>
		    </div>
		    <ul class="nav navbar-nav">
			<li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
			<li role="presentation"><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
			<li role="presentation"><a href="${pageContext.request.contextPath}/superpeople">Superpeople</a></li>
			<li role="presentation"><a href="${pageContext.request.contextPath}/powers">Powers</a></li>
			<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/organizations">Organizations</a></li>
		    </ul>
		</div>
	    </nav>
	    <h1>Organizations</h1>
	    <hr/>
	    <a class="btn btn-primary" href="organizations/new-organization" role="button">
		New Organization
	    </a>
	    <hr/>
	    <table class="table table-hover">
		<thead>
		    <tr>
			<th width="30%">Name</th>
			<th width="50%">Head</th>
			<th width="10%"></th>
			<th width="10%"></th>
		    </tr>
		</thead>
		<tbody>
		    <c:forEach var="organization" items="${organizations}">
			<tr>
			    <td>
				<a href="organizations/organization?id=${organization.id}">
				    <c:out value="${organization.name}"/>
				</a>
			    </td>
			    <td>
				<c:out value="${organization.head}"/>
			    </td>
			    <td>
				<a href="organizations/edit-organization?id=${organization.id}">
				    Edit
				</a>
			    </td>
			    <td>
				<a href="organizations/delete-organization?id=${organization.id}">
				    Delete
				</a>
			    </td>
			</tr>
		    </c:forEach>
		</tbody>
	    </table>
	</div>
	
	<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
