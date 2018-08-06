<%-- 
    Document   : new-superperson
    Created on : Aug 5, 2018, 5:14:37 PM
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
        <title>Superperson Tracker - New Superperson</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
	<div class="container-fluid">
	    <h1>New Superperson</h1>
	    <hr/>
	    <form role="form" action="create-superperson" method="POST" modelAttribute="superperson">
		<div class="form-group">
		    <label for="name">Name</label>
		    <input class="form-control" type="text" title="name" name="name"
			      required maxlength="50"/>
		</div>
		<div class="form-group">
		    <label for="description">Description</label>
		    <textarea class="form-control" rows="10" type="text" title="description" name="description"
			      maxlength="1000"/></textarea>
		</div>
		<div class="form-group">
		    <label>Organizations</label>
		    <select class="form-control" multiple="true" name="organizationIds">
			<c:forEach var="organization" items="${organizations}">
			    <option value="${organization.id}">
				<c:out value="${organization.name}"/>
			    </option>
			</c:forEach>
		    </select>
		</div>
		<div class="form-group">
		    <select class="form-control" multiple="true" name="powerIds">
		    <label>Powers</label>
			<c:forEach var="power" items="${powers}">
			    <option value="${power.id}">
				<c:out value="${power.name}"/>
			    </option>
			</c:forEach>
		    </select>
		</div>
		<div class="form-group">
		    <input type="submit" class="btn btn-primary" value="Create"/>
		    <a href="${pageContext.request.contextPath}/superpeople"
		       type="button" class="btn btn-primary">
			Cancel
		    </a>
		</div>
	    </form>
	</div>
	
	<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
