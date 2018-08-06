<%-- 
    Document   : index
    Created on : Jul 26, 2018, 11:25:32 AM
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
			<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
			<li role="presentation"><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
			<li role="presentation"><a href="${pageContext.request.contextPath}/superpeople">Superpeople</a></li>
			<li role="presentation"><a href="${pageContext.request.contextPath}/powers">Powers</a></li>
		    </ul>
		</div>
	    </nav>
            <h1>Home</h1>
	    <hr/>
	    <p>
		Welcome to the Superperson Tracker!
	    </p>
	    <p>
		This resource allows you to report and search for sightings of
		superpeople, and provides information about superpeople and
		their organizations.
	    </p>
	    <hr/>
	    <h2>Latest Sightings</h2>
	    <table class="table table-hover">
		<thead>
		    <tr>
			<th width="20%">Date</th>
			<th width="50%">Superpeople</th>
			<th width="30%">Location</th>
		    </tr>
		</thead>
		<tbody>
		    <c:forEach var="sighting" items="${sightings}">
			<tr>
			    <td>
				<c:out value="${sighting.timestamp}"/>
			    </td>
			    <td>
				<c:forEach var="superperson" items="${sighting.superpersons}" varStatus="status">
				    <c:out value="${superperson.name}"/>
				    <c:if test="${!status.last}"><br/></c:if>
				</c:forEach>
			    </td>
			    <td>
				<c:out value="${sighting.location.name}"/>
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

