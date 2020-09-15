<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>List of users</title>
    </head>
    <body>
    	<%@ include file="navbar.jsp"%>
        <script>
	        $(document).ready( function () {
    	        var table = $('#table_id').DataTable(
    			    {
    		            responsive: true
    		        }
    	        );

	        } );
        </script>
	    <div class="container">
	        <h5 class="text-center">List of users</h5>
	        <div>
			    <a class="btn btn-outline-primary btn-sm float-right mb-3" href="<c:url value="/users"/>">Add new user</a>
		    </div>
  		    <div>
			    <table id="table_id" class="table table-sm table-hover table-bordered text-center mt-3">
				    <thead>
				        <tr>
                            <th>Id</th>
                            <th>FirstName</th>
                            <th>LastName</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Address</th>
                            <th>Active</th>
                            <th>Home</th>
                            <th>Action</th>
			            </tr>
			        </thead>
			        <tbody>
			            <c:forEach items="${users}" var="user">
				        <tr>
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.username}</td>
                            <td>${user.password}</td>
                            <td><a href="<c:url value="/addresses/${user.address.id}"/>">${ user.address.displayName }</a></td>
                            <td>${user.active}</td>
                            <td><a href="<c:url value="/homes/${user.home.id}"/>">${ user.home.displayName }</a></td>
					        <td>
					            <a class="btn btn-sm btn-info" href="<c:url value="/users/${user.id}"/>">Detail</a>
					            <a class="btn btn-sm btn-primary" href="<c:url value="/users/edit?id=${user.id}"/>">Edit</a>
					            <a class="btn btn-sm btn-danger" href="<c:url value="/users/delete?id=${user.id}"/>">Delete</a>
				            </td>
				        </tr>
			            </c:forEach>
			        </tbody>
		        </table>
	        </div>
	    </div>
    </body>
</html>