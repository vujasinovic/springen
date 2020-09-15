<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>List of homes</title>
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
	        <h5 class="text-center">List of homes</h5>
	        <div>
			    <a class="btn btn-outline-primary btn-sm float-right mb-3" href="<c:url value="/homes"/>">Add new home</a>
		    </div>
  		    <div>
			    <table id="table_id" class="table table-sm table-hover table-bordered text-center mt-3">
				    <thead>
				        <tr>
                            <th>Id</th>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Address</th>
                            <th>Action</th>
			            </tr>
			        </thead>
			        <tbody>
			            <c:forEach items="${homes}" var="home">
				        <tr>
                            <td>${home.id}</td>
                            <td>${home.title}</td>
                            <td>${home.description}</td>
                            <td><a href="<c:url value="/addresses/${home.address.id}"/>">${ home.address.displayName }</a></td>
					        <td>
					            <a class="btn btn-sm btn-info" href="<c:url value="/homes/${home.id}"/>">Detail</a>
					            <a class="btn btn-sm btn-primary" href="<c:url value="/homes/edit?id=${home.id}"/>">Edit</a>
					            <a class="btn btn-sm btn-danger" href="<c:url value="/homes/delete?id=${home.id}"/>">Delete</a>
				            </td>
				        </tr>
			            </c:forEach>
			        </tbody>
		        </table>
	        </div>
	    </div>
    </body>
</html>