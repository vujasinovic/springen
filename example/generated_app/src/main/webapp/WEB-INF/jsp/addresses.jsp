<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>List of addresses</title>
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
	        <h5 class="text-center">List of addresses</h5>
	        <div>
			    <a class="btn btn-outline-primary btn-sm float-right mb-3" href="<c:url value="/addresses"/>">Add new address</a>
		    </div>
  		    <div>
			    <table id="table_id" class="table table-sm table-hover table-bordered text-center mt-3">
				    <thead>
				        <tr>
                            <th>Id</th>
                            <th>Street</th>
                            <th>Number</th>
                            <th>Apartment</th>
                            <th>Description</th>
                            <th>ZipCode</th>
                            <th>Action</th>
			            </tr>
			        </thead>
			        <tbody>
			            <c:forEach items="${addresses}" var="address">
				        <tr>
                            <td>${address.id}</td>
                            <td>${address.street}</td>
                            <td>${address.number}</td>
                            <td>${address.apartment}</td>
                            <td>${address.description}</td>
                            <td>${address.zipCode}</td>
					        <td>
					            <a class="btn btn-sm btn-info" href="<c:url value="/addresses/${address.id}"/>">Detail</a>
					            <a class="btn btn-sm btn-primary" href="<c:url value="/addresses/edit?id=${address.id}"/>">Edit</a>
					            <a class="btn btn-sm btn-danger" href="<c:url value="/addresses/delete?id=${address.id}"/>">Delete</a>
				            </td>
				        </tr>
			            </c:forEach>
			        </tbody>
		        </table>
	        </div>
	    </div>
    </body>
</html>