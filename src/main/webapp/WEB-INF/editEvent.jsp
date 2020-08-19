
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Event</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
    <script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<style>
.container {
margin-top: 3rem;
}
</style>
<body>
	
	<div class="container">
        <h1>${eventToUpdate.name}</h1>
        <p></p>
        <div class="row">
            
            
	            <div class="col">
	          
 					<form:form action="/events/${eventToUpdate.id}/update" method="post" modelAttribute="eventToUpdate">
 					<input type="hidden" name="_method" value="put">
                    <input type="hidden" name="user" value="${user.id}" />
                    
                    
                
                    <h4>Create an Event</h4>
                    <div class="form-group">
                        <label>Name:</label>
                        <form:textarea path="name" class="form-control" />
                        <form:errors path="name" class="text-danger" />
                    </div>
                    
                    <div class="form-group">
                        <label>Date:</label>
                        <form:input path="eventDate" class="date" type="date"/>
                        <form:errors path="eventDate" class="text-danger" />
                    
					</div>
                    <div class="form-group">
                        <label>Location:</label>
                        <form:textarea path="location" class="form-control" />
                        <form:errors path="location" class="text-danger" />
                    </div>
                    
                    <div class="form-group">
                        <label>State:</label>
                        <form:input path="state" class="form-control" />
                        <form:errors path="state" class="text-danger" />
                    </div>
                    
                    
                    <input type="submit" class="btn btn-primary" value="Update" />
                    
                </form:form>
	            
	            </div>
            
	            <div class="col">
	            </div>
          </div>   
       		  
       		        
    </div>   
            
          
            

</body>
</html>