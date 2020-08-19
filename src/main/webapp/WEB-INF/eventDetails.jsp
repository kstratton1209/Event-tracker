
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
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
        <h1>${event.name}</h1>
        <p></p>
        <div class="row">
            
            
	            <div class="col">
	          
	          
	                    <p>Name: ${event.name}</p>
	                    <p>Date: ${event.eventDate}</p>
	                    <p>Location: ${event.location}</p>
	                    <p>People attending: ${event.attendees.size()}</p>
	                    
	                    
	              <table class="table table-striped mt-3">
			            <tbody>
			                <tr>
			                    <th>Name</th>
			         		    <th>Location</th>
			                    
			                </tr>
			                <c:forEach items="${event.attendees}" var="people">
			            		
			            		<tr>
			                         <th>${people.firstName}</th>
			                         <th>${people.location}</th>                     
			                                           
			                    </tr>
			                    
	 		                </c:forEach>
						</tbody>
	       		   </table>
	            
	            
	            </div>
            
	            <div class="col">
	            
	            	<h4>Message wall</h4>
	            	
	            	<c:forEach items="${allComments}" var="comment">
			            <tr>
			               <th>${comment.commenter.firstName} said: ${comment.message}</th>
			            </tr>
	 		         </c:forEach>
					
	            	
	            	
                   <p></p>
                   <form:form action="/${event.id}/addComment" method="post" modelAttribute="newComment">
	                    <input type="hidden" name = "commenter" value = "${user.id}">
	                    <input type="hidden" name = "event" value = "${event.id}">
	                    <div class="form-group">
	                        <label>Comment:</label>
	                        <form:textarea path="message" class="form-control" />
	                        <form:errors path="message" class="text-danger" />
	                    </div>
	                    
	                    
	                    <input type="submit" class="btn btn-primary" value="Comment" />
                	</form:form> 
	            	
	            	
	            </div>
          </div>   
       		  
       		        
    </div>   
            
          
            

</body>
</html>