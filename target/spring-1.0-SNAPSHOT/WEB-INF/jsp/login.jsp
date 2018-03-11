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
    <title>Login</title>
     
     <meta  charset="UTF-8">
     <meta name="viewport" content="width=device-width,
              initial-scale=1, shrink-to-fit=no">
        
    <link rel="stylesheet" href="resources/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="resources/css/open-iconic-bootstrap.css">
    <link rel="stylesheet" href="resources/css/open-iconic-foundation.css">
    <link rel="stylesheet" href="resources/css/estilos.css" type="text/css">
    <script src="resources/js/jquery-3.3.1.js" ></script>
    <script src="resources/js/popper.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/jquery.validate.js"></script>
  
 
    <script type="text/javascript">
      $(document).ready(function() {
        $('#loginForm').validate({
          rules:{
             login:{
              required:true
             },
             paswd:{
              required:true
             }
          },
          messages:{
             login:{
              required:"El campo login es obligatorio"
             },
             paswd: {
              required: "Debes introducir una contraseña"
            }
          },
            errorClass: "is-invalid"
        });
      });
    </script> 
 </head>
 <body>
  <div  class="login_main  container-fluid  row align-items-center ">
    <div class="col-sm"></div>
    <div class="col-sm ">
      <div class="card">
        <div class="card-body ">
         <form:form method="post" 
              action="login" id="loginForm"  modelAttribute="empleado" >
          <div class="form-group">
            <label for="empleado">Login</label>
            <form:input id="login" path="login" class="form-control"  
                              aria-describedby="loginHelp" 
                              placeholder="Introduzca el usuario"  
                                 />
                         
          </div>
          <div class="form-group">
            <label for="paswd">Password</label>
            <form:password class="form-control"  
                                       placeholder="Password" path="paswd"/>
            
          </div>
  	  <button type="submit" class="btn btn-primary">Entrar</button>
        </form:form>
          </div>
          </div>
        </div>
       <div class="col-sm"></div>
      </div>
      
    </body>
</html>
