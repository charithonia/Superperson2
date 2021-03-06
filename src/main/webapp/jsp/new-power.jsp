<%-- 
    Document   : new-power
    Created on : Jul 29, 2018, 2:05:44 AM
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
        <title>Superperson Tracker - New Power</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/superperson2.css" rel="stylesheet">
    </head>
    <body>
	<div class="container-fluid">
	    <h1>New Power</h1>
	    <hr/>
	    <form role="form" action="create-power" method="POST" modelAttribute="power">
		<div class="form-group">
		    <label class="control-label" for="name">Power Name:</label>
		    <input class="form-control" type="text" title="name" name="name"
			      required/>
		</div>
		<div class="form-group">
		    <label for="description" class="control-label">Description:</label>
		    <textarea class="form-control" rows="10" name="description"
				 maxlength="1000"></textarea>
		</div>
		<div class="form-group">
		    <input type="submit" class="btn btn-primary" value="Create"/>
		    <a href="${pageContext.request.contextPath}/powers"
		       typ="button" class="btn btn-primary">
			Cancel
		    </a>
		</div>
	    </form>
	</div>
	
	<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
