<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Bar</title>
    </head>
    <body>
	    <%@ include file="navbar.jsp"%>
	    <div class="container">
            <div class="text-center mt-2">
                <h5>Bar details</h5>
                <div>
                    <div>
	                    <div class="my-2">
	                        <span>
	                        Id:
	                        <span class="font-weight-bold">${bar.id}</span>
                            </span>
	                    </div>
	                    <hr class="my-2">
	                    <div class="my-2">
	                        <span>
	                        Pff:
	                        <span class="font-weight-bold">${bar.pff}</span>
                            </span>
	                    </div>
	                    <hr class="my-2">
	                    <div class="my-2">
	                        <span>
	                        Fooici:
                                <div class="m-2">
                                <c:if test="${ empty bar.fooici }">[...]</c:if>
                                <c:forEach items="${ bar.fooici }" var="fooici_single">
                                <span>
                                    <a href="<c:url value="/fooici/${fooici_single.id}"/>"> ${ fooici_single.displayName } </a>
                                </span>
                                <br>
                                </c:forEach>
                                </div>
                            </span>
	                    </div>
	                    <hr class="my-2">
                    </div>
                </div>
                <a  href="<c:url value="/bars/edit?id=${bar.id}"/>" class="btn btn-sm btn-outline-primary mt-4">Edit</a>
                <a  href="<c:url value="/bars/delete?id=${bar.id}"/>" class="btn btn-sm btn-outline-danger mt-4">Delete</a>
            </div>
        </div>
    </body>
</html>