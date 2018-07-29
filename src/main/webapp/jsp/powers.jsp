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
        <title>Superperson Tracker</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <nav class="navbar navbar-default">
		<div class="container-fluid">
		    <div class="navbar-header">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/">Superperson Tracker</a>
		    </div>
		    <ul class="nav navbar-nav">
			<li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
			<li role="presentation"><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
			<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/powers">Powers</a></li>
		    </ul>
		</div>
	    </nav>
            <h1>Powers</h1>
	    <hr/>
	    <table class="table table-hover">
		<thead>
		    <tr>
			<th>Name</th>
			<th>Description</th>
		    </tr>
		</thead>
		<tbody>
		    <c:forEach var="power" items="${powers}">
			<tr>
			    <td>
				<c:out value="${power.name}"/>
			    </td>
			    <td>
				<c:out value="${power.description}"/>
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
