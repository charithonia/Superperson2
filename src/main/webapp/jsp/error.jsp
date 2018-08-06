<%-- 
    Document   : error
    Created on : Aug 4, 2018, 1:02:13 PM
    Author     : main
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Superperson 2 - Edit Power</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
	<div class="container-fluid">
	    <h1>Error</h1>
	    <p>
		<c:out value="${errorMessage}"/>
	    </p>

	    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	</div>
    </body>
</html>
