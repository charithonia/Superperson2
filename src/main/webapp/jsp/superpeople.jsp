<%-- 
    Document   : superpeople
    Created on : Aug 5, 2018, 2:05:38 PM
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
        <title>Superperson Tracker</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
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
			<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/superpeople">Superpeople</a></li>
			<li role="presentation"><a href="${pageContext.request.contextPath}/powers">Powers</a></li>
		    </ul>
		</div>
	    </nav>
	    <h1>Superpeople</h1>
	    <hr/>
	    <a class="btn btn-primary" href="superpeople/new-superperson" role="button">
		New Superperson
	    </a>
	    <hr/>
	    <table class="table table-hover">
		<thead>
		    <tr>
			<th width="20">Name</th>
			<th width="50">Description</th>
			<th width="15"></th>
			<th width="15"></th>
		    </tr>
		</thead>
		<c:forEach var="superperson" items="${superpersons}">
		    <tr>
			<td>
			    <c:out value="${superperson.name}"/>
			</td>
			<td>
			    <c:out value="${superperson.description}"/>
			</td>
			<td>
			    <a href="#">
				Edit
			    </a>
			</td>
			<td>
			    <a href="superpeople/delete-superperson?id=${superperson.id}">
				Delete
			    </a>
			</td>
		    </tr>
		</c:forEach>
	    </table>
	</div>
    </body>
</html>
