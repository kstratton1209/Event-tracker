
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
<body>
	
	<div class="container">
        <h1>Welcome, ${user.firstName} </h1>
        <h6>Here are some of the events in your state:</h6>
        
        <div class="row">
            
            <div class="col">
               
               <table class="table table-striped mt-3">
		            <tbody>
		                <tr>
		                    <th>Name</th>
		                    <th>Date</th>
		                    <th>Location</th>
		                    <th>Host</th>
		                    <th>Action/Status</th>
		                    
		                </tr>
		                <c:forEach items="${allEvents}" var="ev">
		            		<c:if test="${ev.state == user.state}">            
		            		
		            		<tr>
		                        <td><a href="/events/${ev.id}/details">${ev.name}</a></td>
		                        <td>${ev.eventDate}</td>
		                        <td>${ev.location}</td>
		                        <td>${ev.user.firstName}</td>
		                        <td>
		                        
		                        
										<c:if test="${ev.isAttending(user.id)}">
										  	<p></p>
										</c:if> 
										<c:if test="${!ev.isAttending(user.id)}">
										  	<a href="/events/${ev.id}/join">Join</a>
										</c:if> 
										<c:if test="${ev.isAttending(user.id)}">
										  	<a href="/events/${ev.id}/cancel">Cancel</a>
										</c:if> 
										

			                        	<c:if test="${ev.user.id == user.id}">            
			                        		<a href="/events/${ev.id}/edit">Edit</a>
			                        		<a href="/events/${ev.id}/delete">Delete</a>
			                        	</c:if>
		                        	
		                        	
		                        </td>                       
		                    </tr>
		                    
		                    </c:if>
 		                </c:forEach>
					</tbody>
       		   </table>
       		   
       		  
       		 <h6>Here are some of the events in other states:</h6>
       		   
       		   
       		 <table class="table table-striped mt-3">
		            <tbody>
		                <tr>
		                    <th>Name</th>
		                    <th>Date</th>
		                    <th>Location</th>
		                    <th>Host</th>
		                    <th>Action/Status</th>
		                    
		                </tr>
 		               	<c:forEach items="${allEvents}" var="ev">
		            		<c:if test="${ev.state != user.state}">            
		            		
		            		<tr>
		                        <td><a href="/events/${ev.id}/details">${ev.name}</a></td>
		                        <td>${ev.eventDate}</td>
		                        <td>${ev.location}</td>
		                        <td>${ev.user.firstName}</td>
		                        <td>
		                        
		                        
										<c:if test="${ev.isAttending(user.id)}">
										  	<p></p>
										</c:if> 
										<c:if test="${!ev.isAttending(user.id)}">
										  	<a href="/events/${ev.id}/join">Join</a>
										</c:if> 

			                        	<c:if test="${ev.user.id == user.id}">            
			                        		<a href="/events/${ev.id}/edit">Edit</a>
			                        		<a href="/events/${ev.id}/delete">Delete</a>
			                        	</c:if>
		                        	
		                        	
		                        </td>                       
		                    </tr>
		                    
		                    </c:if>
 		                </c:forEach>
		            </tbody>
       		   </table>
       		   
       		  
       		  
       		<div class="row">
            <div class="col">
                <form:form action="/createEvent" method="post" modelAttribute="newEvent">
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
                    
                    
                    <input type="submit" class="btn btn-primary" value="Create" />
               </form:form> 
            </div>
            <div class="col">
            </div>
            </div>   
       		  
       		        
          </div>   
	</div>   
            
          
            
            
            
                
                
            
    
</div>

</body>
</html>