<%-- 
    Document   : powers
    Created on : Jul 27, 2018, 11:54:15 AM
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
        <title>Superperson Tracker - Powers</title>
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
			<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/powers">Powers</a></li>
			<li role="presentation"><a href="${pageContext.request.contextPath}/organizations">Organizations</a></li>
		    </ul>
		</div>
	    </nav>
            <h1>Powers</h1>
	    <hr/>
	    <a class="btn btn-primary" href="powers/new-power" role="button">
		New Power
	    </a>
	    <hr/>
	    <table class="table table-hover">
		<thead>
		    <tr>
			<th width="20%">Name</th>
			<th width="60%">Description</th>
			<th width="10%"></th>
			<th width="10%"></th>
		    </tr>
		</thead>
		<tbody>
		    <c:forEach var="power" items="${powers}">
			<tr>
			    <td>
				<a href="powers/power?id=${power.id}">
				    <c:out value="${power.name}"/>
				</a>
			    </td>
			    <td>
				<c:out value="${power.description}"/>
			    </td>
			    <td align="center">
				<a href="powers/edit-power?id=${power.id}">
				    Edit
				</a>
			    </td>
			    <td align="center">
				<a href="powers/delete-power?id=${power.id}">
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
