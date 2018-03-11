<%-- 
    Document   : editaPlato
    Created on : 18-feb-2018, 16:22:20
    Author     : jcpm0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edición</title>
         <meta  charset="UTF-8">
     <meta name="viewport" content="width=device-width,
              initial-scale=1, shrink-to-fit=no">
        
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/open-iconic-bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/open-iconic-foundation.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/estilos.css" type="text/css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js" ></script>
    <script src="${pageContext.request.contextPath}/resources/js/popper.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.validate.js"></script>
  
 
    <script type="text/javascript">
      $(document).ready(function() {
        $('#loginForm').validate({
          rules:{
             nombre:{
              required:true
             }
          },
          messages:{
             nombre:{
              required:"El campo nombre es obligatorio"
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
    <div class=" container-fluid   align-items-center"><!-- container -->
      <header class="row justify-content-md-end ">
        <div class="col-md-12 "><!-- columna única para la barra de navegación -->
          <nav class="navbar navbar-expand-sm navbar-dark bg-primary ">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/inicio">#BienMeSabe</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent" 
                    aria-controls="navbarSupportedContent" 
                    aria-expanded="false" 
                    aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>

          <div class="collapse navbar-collapse " id="navbarSupportedContent">
            <ul class="navbar-nav   ">
                <li class="nav-item">
                <a class="nav-link active" href="${pageContext.request.contextPath}/platos">Platos</a>
              </li>
                <li class="nav-item">
                <a class="nav-link " href="${pageContext.request.contextPath}/carta">Carta</a>
              </li>
              <li class="nav-item">
                <a class="nav-link " href="${pageContext.request.contextPath}/menu">Menú del día</a>
              </li>
              <li class="nav-item">
                <a class="nav-link " href="#">Reservas</a>
              </li>
               <li class="nav-item">
                <a class="nav-link " href="${pageContext.request.contextPath}/notificaciones">Notificaciones</a>
              </li>
              <li class="nav-item">
                <a class="nav-link " href="#">Salir</a>
              </li>
            </ul>

          </div>
          </nav>
        </div><!-- columna única para la barra de navegación -->
      </header>
      <main class="login_main row align-items-center">
        <div class="col-md-4"></div>
        <div class="col-md-4 ">
      <div class="card">
        <div class="card-body ">
         <form:form  method="post" 
              action="/spring/editaPlato?offset=${offset}" id="editaPlato"  modelAttribute="plato" >
                          
          <div class="form-row">
  
                     
             
             <form:hidden id="idPlato" path="idPlato" class="form-control form-control-sm"  
                              aria-describedby="loginHelp"  />           
          
           <div class=" form-group col-md-6">
            <label for="Tipo ">Tipo</label>
            <form:select multiple="false" class="form-control form-control-sm "
                         id="tipo" path="tipo_plato" 
                        size="1"  aria-describedby="loginHelp" >
              <form:option value="${tipoActual}"/>
              <form:options items="${tipoPlato}"/>
            </form:select>
           </div>
           <div class=" form-group col-md-6">
             <label for="nombre ">Nombre</label>
             <form:input id="nombre" path="nombre" class="form-control form-control-sm"  
                              aria-describedby="loginHelp" />           
          </div>
         </div>
            <div class="form-row">
         
            <div class="col-md-4 form-group">
              <label for="precioTapa">Precio Tapa</label>
              <form:input class="form-control form-control-sm " path="precioTapa"/>
            </div>
            <div class="col-md-4 form-group">
              <label for="precioMedia">Precio Media</label>
              <form:input class="form-control form-control-sm " path="precioMedia"/>
            </div>
            <div class="col-md-4 form-group">
              <label for="precioRacion">Precio Ración</label>
              <form:input class="form-control form-control-sm " path="precioRacion"/>
            </div>
          
           </div>
            <div class="form-row "  >
              <div class="form-group col-md-6"></div>
              <div class="form-group col-md-3">
                <button type="reset" class="btn btn-secondary">Cancelar</button>
              </div>
              <div class="form-group col-md-3">
                <button type="submit" class="btn btn-primary">Guardar</button>
              </div>
          </div>
        </form:form>
          </div>
          </div>
        </div>
            <div class="col-md-4"></div>
      </main>
    </div><!-- container -->
    
  </body>
</html>
