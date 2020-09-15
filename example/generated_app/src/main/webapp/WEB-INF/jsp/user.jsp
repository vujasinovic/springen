<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>User</title>
    </head>
    <body>
	    <%@ include file="navbar.jsp"%>
	    <div class="container">
            <div class="text-center mt-2">
                <h5>User details</h5>
                <div>
                    <div>
	                    <div class="my-2">
	                        <span>
	                        Id:
	                        <span class="font-weight-bold">${user.id}</span>
                            </span>
	                    </div>
	                    <hr class="my-2">
	                    <div class="my-2">
	                        <span>
	                        FirstName:
	                        <span class="font-weight-bold">${user.firstName}</span>
                            </span>
	                    </div>
	                    <hr class="my-2">
	                    <div class="my-2">
	                        <span>
	                        LastName:
	                        <span class="font-weight-bold">${user.lastName}</span>
                            </span>
	                    </div>
	                    <hr class="my-2">
	                    <div class="my-2">
	                        <span>
	                        Username:
	                        <span class="font-weight-bold">${user.username}</span>
                            </span>
	                    </div>
	                    <hr class="my-2">
	                    <div class="my-2">
	                        <span>
	                        Password:
	                        <span class="font-weight-bold">${user.password}</span>
                            </span>
	                    </div>
	                    <hr class="my-2">
	                    <div class="my-2">
	                        <span>
	                        Address:
                            <a href="<c:url value="/addresses/${user.address.id}"/>"> ${ user.address.displayName } </a>
                            </span>
	                    </div>
	                    <hr class="my-2">
	                    <div class="my-2">
	                        <span>
	                        Active:
	                        <span class="font-weight-bold">${user.active}</span>
                            </span>
	                    </div>
	                    <hr class="my-2">
	                    <div class="my-2">
	                        <span>
	                        Home:
                            <a href="<c:url value="/homes/${user.home.id}"/>"> ${ user.home.displayName } </a>
                            </span>
	                    </div>
	                    <hr class="my-2">
                    </div>
                </div>
                <a  href="<c:url value="/users/edit?id=${user.id}"/>" class="btn btn-sm btn-outline-primary mt-4">Edit</a>
                <a  href="<c:url value="/users/delete?id=${user.id}"/>" class="btn btn-sm btn-outline-danger mt-4">Delete</a>
            </div>
        </div>
    </body>
</html>