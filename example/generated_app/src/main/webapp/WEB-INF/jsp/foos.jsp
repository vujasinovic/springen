<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>List of foos</title>
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
	        <h5 class="text-center">List of foos</h5>
	        <div>
			    <a class="btn btn-outline-primary btn-sm float-right mb-3" href="<c:url value="/foos"/>">Add new foo</a>
		    </div>
  		    <div>
			    <table id="table_id" class="table table-sm table-hover table-bordered text-center mt-3">
				    <thead>
				        <tr>
                            <th>Id</th>
                            <th>Data</th>
                            <th>Bar</th>
                            <th>Action</th>
			            </tr>
			        </thead>
			        <tbody>
			            <c:forEach items="${foos}" var="foo">
				        <tr>
                            <td>${foo.id}</td>
                            <td>${foo.data}</td>
                            <td><a href="<c:url value="/bars/${foo.bar.id}"/>">${ foo.bar.displayName }</a></td>
					        <td>
					            <a class="btn btn-sm btn-info" href="<c:url value="/foos/${foo.id}"/>">Detail</a>
					            <a class="btn btn-sm btn-primary" href="<c:url value="/foos/edit?id=${foo.id}"/>">Edit</a>
					            <a class="btn btn-sm btn-danger" href="<c:url value="/foos/delete?id=${foo.id}"/>">Delete</a>
				            </td>
				        </tr>
			            </c:forEach>
			        </tbody>
		        </table>
	        </div>
	    </div>
    </body>
</html>