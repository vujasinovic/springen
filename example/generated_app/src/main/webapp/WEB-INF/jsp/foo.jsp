<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Foo</title>
    </head>
    <body>
	    <%@ include file="navbar.jsp"%>
	    <div class="container">
            <div class="text-center mt-2">
                <h5>Foo details</h5>
                <div>
                    <div>
	                    <div class="my-2">
	                        <span>
	                        Id:
	                        <span class="font-weight-bold">${foo.id}</span>
                            </span>
	                    </div>
	                    <hr class="my-2">
	                    <div class="my-2">
	                        <span>
	                        Data:
	                        <span class="font-weight-bold">${foo.data}</span>
                            </span>
	                    </div>
	                    <hr class="my-2">
	                    <div class="my-2">
	                        <span>
	                        Bar:
                            <a href="<c:url value="/bars/${foo.bar.id}"/>"> ${ foo.bar.displayName } </a>
                            </span>
	                    </div>
	                    <hr class="my-2">
                    </div>
                </div>
                <a  href="<c:url value="/foos/edit?id=${foo.id}"/>" class="btn btn-sm btn-outline-primary mt-4">Edit</a>
                <a  href="<c:url value="/foos/delete?id=${foo.id}"/>" class="btn btn-sm btn-outline-danger mt-4">Delete</a>
            </div>
        </div>
    </body>
</html>