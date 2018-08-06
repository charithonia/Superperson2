<%-- 
    Document   : edit-power
    Created on : Aug 1, 2018, 3:52:40 PM
    Author     : main
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Superperson 2 - Edit Power</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
	<div class="container-fluid">
	    <h1>Edit Power</h1>
	    <hr/>
	    <sf:form role="form" action="edit-power" method="POST" modelAttribute="power">
		<sf:hidden path="id"/>
		<div class="form-group">
		    <label for="name" class="control-label">Power Name:</label>
		    <sf:input class="form-control" type="text" title="name" path="name"
			      required="required" maxlength="50"/>
		</div>
		<div class="form-group">
		    <label for="description" class="control-label">Description:</label>
		    <sf:textarea class="form-control" path="description" rows="10"
				 maxlength="1000"></sf:textarea>
		</div>
		<div class="form-group">
		    <input type="submit" class="btn btn-primary" value="Save"/>
		    <a href="${pageContext.request.contextPath}/powers"
		       type="button" class="btn btn-primary">
			Cancel
		    </a>
		</div>
	    </sf:form>
	</div>
	
	<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
