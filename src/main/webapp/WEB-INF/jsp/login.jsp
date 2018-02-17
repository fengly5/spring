<%-- 
    Document   : login
    Created on : 28-ene-2018, 22:45:20
    Author     : jcpm0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta  charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
  <link rel="stylesheet" href="css/bootstrap.min.css" >
  <link rel="stylesheet" href="css/estilos.css">
  <script src="js/jquery-3.3.1.js" ></script>
  <script src="js/popper.js"></script>
  <script src="js/bootstrap.min.js"></script>
        <title>Login</title>
    </head>
    <body>
        <div class="container row align-items-center">
           <div class="col"></div>
	   <div class="col">
		<div class="card">
		 <div class="card-body ">
                     <form:form method="post" action="login" modelAttribute="empleado" >
			<div class="form-group">
		         <label for="empleado">Login</label>
                         <form:input path="login" />
                         <form:errors path="login"/>
			</div>
			<div class="form-group">
			 <label for="paswd">Password</label>
			 <form:password path="paswd"/>
                         <form:errors path="paswd"/>
			</div>
  			<button type="submit" class="btn btn-primary">Entrar</button>
         	    </form:form>
		 </div>
	        </div>
	   </div>
	   <div class="col"></div>
        </div>
    </body>
</html>
