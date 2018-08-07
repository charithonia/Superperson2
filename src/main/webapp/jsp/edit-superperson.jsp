<%-- 
    Document   : edit-superperson
    Created on : Aug 6, 2018, 3:40:49 AM
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
        <title>Superperson Tracker - Edit Superperson</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/superperson2.css" rel="stylesheet">
    </head>
    <body>
	<div class="container-fluid">
	    <h1>Edit Superperson</h1>
	    <hr/>
	    <sf:form role="form" action="edit-superperson" method="POST" modelAttribute="superperson">
		<sf:hidden path="id"/>
		<div class="form-group">
		    <label class="control-label" for="name">Name</label>
		    <sf:input class="form-control" type="text" title="name" path="name"
			      required="required" maxlength="50"/>
		</div>
		<div class="form-group">
		    <label class="control-label" for="description">Description</label>
		    <sf:textarea class="form-control" rows="10" title="description" path="description"
			      maxlength="1000"/>
		</div>
		<div class="form-group">
		    <label class="control-label" for="organizations">Organizations</label>
		    <sf:select class="form-control" multiple="true" path="organizationIds">
			<sf:options items="${organizations}" itemLabel="name" itemValue="id"/>
		    </sf:select>
		</div>
		<div class="form-group">
		    <label class="control-label" for="powers">Powers</label>
		    <sf:select class="form-control" multiple="true" path="powerIds">
			<sf:options items="${powers}" itemLabel="name" itemValue="id"/>
		    </sf:select>
		</div>
		<div class="form-group">
		    <input class="btn btn-primary" type="submit" value="Save"/>
		    <a href="${pageContext.request.contextPath}/superpeople"
		       class="btn btn-primary">
			Cancel
		    </a>
		</div>
	    </sf:form>
	</div>
	
	<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
