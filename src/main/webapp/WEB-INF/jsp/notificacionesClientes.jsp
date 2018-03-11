<%-- 
    Document   : notificacionesClientes
    Created on : 06-mar-2018, 18:50:44
    Author     : jcpm0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tag" uri="/WEB-INF/jsp/taglib/customTaglib.tld"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Notificaciones</title>
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
      $.validator.addMethod("valueNotEquals", function(value, element, arg){
  return arg !== value;
 }, "Value must not equal arg.");
      $(document).ready(function() {
        $('#crearCarta').validate({
          rules:{
             noombre:{
              required:true
             }
          },
          messages:{
             noombre:{
              required:"El campo nombre es obligatorio"
             }
          },
            errorClass: "is-invalid"
        });
      });
    </script>
  </head>
  <body>
    <div class=" container  align-items-center"><!-- container -->
<header class="row justify-content-md-center ">
        <div class="col-auto "><!-- columna única para la barra de navegación -->
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
                <a class="nav-link " href="${pageContext.request.contextPath}/platos">Platos</a>
              </li>
                <li class="nav-item">
                <a class="nav-link " href="${pageContext.request.contextPath}/carta">Carta</a>
              </li>
              <li class="nav-item">
                <a class="nav-link " href="#">Menú del día</a>
              </li>
              <li class="nav-item">
                <a class="nav-link " href="#">Reservas</a>
              </li>
               <li class="nav-item">
                <a class="nav-link active" href="${pageContext.request.contextPath}/carta">Notificaciones</a>
              </li>
              <li class="nav-item">
                <a class="nav-link " href="#">Salir</a>
              </li>
            </ul>

          </div>
          </nav>
        </div><!-- columna única para la barra de navegación -->
      </header>
      <main class="row justify-content-md-center ">
        <div class="row">
        
        </div>
        <div class="row ">
          <div class="col">
          <div class="card "><!-- card -->
            <div class="card-header"> <!-- card header -->
            <h5 >Notificaciones a clientes</h5>
            </div><!-- card header -->
            <div class="card-body"><!-- card boy -->
              <div class="row justify-content-md-end">
                  <div class="col-md-3 " >
                        <a href="crearNotificacionClientes"
                       class="btn btn-info btn-sm"><i class="oi oi-plus"></i>
                       Nueva notificación
                       </a>
                </div>
              </div>
              <div class="row table-responsive-md"> <!-- Tabla -->
                <table class="table table-sm table-hover table-striped">
                  <thead>
                    <tr>
                      <th scope="row">Fecha</th>
                      <th scope="row">e-mail</th>
                      <th scope="row">Nombre</th>
                      <th scope="row">Mensaje</th>
                      <th scope="row">Enviada</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach items="${notif}" var="notif1" >
                 <tr>
                   
                   <th scope="row"><c:out value="${notif1.notificacion.fecha}"></c:out>
                   </th>
                   
                   <td><c:out value="${notif1.cliente.email}"></c:out></td>
                   <td><c:out value="${notif1.cliente.usuario.nombre} ${notif1.cliente.usuario.apellido1}"></c:out></td>
                  
                   <td><c:out value="${notif1.notificacion.mensaje}"></c:out>
                   </td>
                   <td>
                   <c:choose>
                     <c:when test="${notif1.notificacion.entregada == 1}">
                         
                          <span class="oi oi-check "></span>                              
                            
                     </c:when>
                           <c:otherwise >
                         
                          <span class="oi oi-ban "></span>                              
                             
                     </c:otherwise>
                   </c:choose>
                   
                   </td>
                 </tr>
                  
                </c:forEach>
                </tbody>
               </table>
              </div><!-- Tabla -->
               <tag:paginate max="10" offset="${offset}" count="${count}"
                 uri="notificacionesClientes.htm" next="&raquo;" previous="&laquo;" />
             </div> <!-- card boy -->
          </div><!-- card -->
        </div><!-- col -->
        </div>
    </main>
   
        
        
      </div><!-- Fin contenedor -->
  </body>
</html>
