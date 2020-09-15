<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Home</title>
    </head>
    <body>
	    <%@ include file="navbar.jsp"%>
	    <div class="container">
            <div class="text-center mt-2">
                <h5>Home details</h5>
                <div>
                    <div>
	                    <div class="my-2">
	                        <span>
	                        Id:
	                        <span class="font-weight-bold">${home.id}</span>
                            </span>
	                    </div>
	                    <hr class="my-2">
	                    <div class="my-2">
	                        <span>
	                        Title:
	                        <span class="font-weight-bold">${home.title}</span>
                            </span>
	                    </div>
	                    <hr class="my-2">
	                    <div class="my-2">
	                        <span>
	                        Description:
	                        <span class="font-weight-bold">${home.description}</span>
                            </span>
	                    </div>
	                    <hr class="my-2">
	                    <div class="my-2">
	                        <span>
	                        Address:
                            <a href="<c:url value="/addresses/${home.address.id}"/>"> ${ home.address.displayName } </a>
                            </span>
	                    </div>
	                    <hr class="my-2">
	                    <div class="my-2">
	                        <span>
	                        Users:
                                <div class="m-2">
                                <c:if test="${ empty home.users }">[...]</c:if>
                                <c:forEach items="${ home.users }" var="users_single">
                                <span>
                                    <a href="<c:url value="/users/${users_single.id}"/>"> ${ users_single.displayName } </a>
                                </span>
                                <br>
                                </c:forEach>
                                </div>
                            </span>
	                    </div>
	                    <hr class="my-2">
                    </div>
                </div>
                <a  href="<c:url value="/homes/edit?id=${home.id}"/>" class="btn btn-sm btn-outline-primary mt-4">Edit</a>
                <a  href="<c:url value="/homes/delete?id=${home.id}"/>" class="btn btn-sm btn-outline-danger mt-4">Delete</a>
            </div>
        </div>
    </body>
</html>