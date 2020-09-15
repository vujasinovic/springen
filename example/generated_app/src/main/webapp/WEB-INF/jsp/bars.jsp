<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>List of bars</title>
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
	        <h5 class="text-center">List of bars</h5>
	        <div>
			    <a class="btn btn-outline-primary btn-sm float-right mb-3" href="<c:url value="/bars"/>">Add new bar</a>
		    </div>
  		    <div>
			    <table id="table_id" class="table table-sm table-hover table-bordered text-center mt-3">
				    <thead>
				        <tr>
                            <th>Id</th>
                            <th>Pff</th>
                            <th>Action</th>
			            </tr>
			        </thead>
			        <tbody>
			            <c:forEach items="${bars}" var="bar">
				        <tr>
                            <td>${bar.id}</td>
                            <td>${bar.pff}</td>
					        <td>
					            <a class="btn btn-sm btn-info" href="<c:url value="/bars/${bar.id}"/>">Detail</a>
					            <a class="btn btn-sm btn-primary" href="<c:url value="/bars/edit?id=${bar.id}"/>">Edit</a>
					            <a class="btn btn-sm btn-danger" href="<c:url value="/bars/delete?id=${bar.id}"/>">Delete</a>
				            </td>
				        </tr>
			            </c:forEach>
			        </tbody>
		        </table>
	        </div>
	    </div>
    </body>
</html>