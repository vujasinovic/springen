<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link type="text/css" rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <title>Form for home</title>
    </head>
    <body>
        <%@ include file="navbar.jsp"%>
        <div class="container">
            <form:form method="POST" modelAttribute="home" action="/homes" >
                <form:hidden path="id" />
                    <div class="form-group">
                        <label for="id">Id</label>
                        <form:input type="number" class="form-control" id="id" path="id"  disabled="true"  />
                    </div>
                    <div class="form-group">
                        <label for="title">Title</label>
                        <form:input type="text" class="form-control" id="title" path="title" />
                    </div>
                    <div class="form-group">
                        <label for="description">Description</label>
                        <form:input type="text" class="form-control" id="description" path="description" />
                    </div>
                    <div class="form-group">
                        <label for="address">Address</label>
                         <form:select name="address.id" id="address.id" path="address.id">
                         <c:forEach var="item" items="${ addresses }">
                            <option value="${item.id}" ${ home.address.id == item.id ? 'selected' : '' }>${item.displayName}</option>
                         </c:forEach>
                        </form:select>
                    </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form:form>
        </div>
    </body>
</html>