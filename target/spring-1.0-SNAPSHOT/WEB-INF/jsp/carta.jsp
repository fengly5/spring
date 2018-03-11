<%-- 
    Document   : carta
    Created on : 27-feb-2018, 20:20:38
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
    <title>Crear plato</title>
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
    <div class=" container-fluid   align-items-center"><!-- container -->
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
                <a class="nav-link active" href="${pageContext.request.contextPath}/carta">Carta</a>
              </li>
              <li class="nav-item">
                <a class="nav-link " href="#">Menú del día</a>
              </li>
              <li class="nav-item">
                <a class="nav-link " href="#">Reservas</a>
              </li>
               <li class="nav-item">
                <a class="nav-link " href="#">Notificaciones</a>
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
        
        <div class="col-md-auto">
          <div class="card "><!-- card -->
            <div class="card-header"> <!-- card header -->
            <h5 >Cartas</h5>
            </div><!-- card header -->
            <div class="card-body"><!-- card boy -->
              <div class="row align-items-start">
                 <form:form  method="POST" action="/spring/crearCarta" id="crearCarta" modelAttribute="carta"  >
                <div class=" row justify-content-end ">
                     
                        <div class="form-group col-md-9"> 
                          <label class="form-text" for="nombre">Nombre</label>
                          <form:input  class="form-control form-control-sm " id="nombre" path="noombre"   ></form:input>
                       
                        </div>
              
                      
                     <div class="form-group col-md-3 align-self-end ">
                      
                      <button type="submit" id="save"    class="btn btn-info btn-sm ">Crear carta
                      </button>
                     </form:form>
                  </div>
                     
                </div>
              </div>
              <div class="row table-responsive-md"> <!-- Tabla -->
                <table class="table table-hover table-striped">
                  <thead>
                    <tr>
                      <th scope="row">Id</th>
                      <th scope="row">Nonbre</th>
                      <th scope="row">Editar</th>
                      <th scope="row">Eliminar</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach items="${cartas}" var="consulta" >
                 <tr>
                   <th scope="row"><c:out value="${consulta.idcarta}"></c:out>
                   </th>
                   <td><c:out value="${consulta.noombre}"></c:out></td>
                   <td><a href="editaCarta/${consulta.idcarta}"
                       class="btn btn-info btn-xs"><i class="oi oi-pencil"></i>
                       Editar
                       </a>
                   </td>
                   <td><a href="borraCarta/${consulta.idcarta}"
                          class="btn btn-danger btn-xs">
                          <span class="oi oi-delete "></span>                              
                             Eliminar</a>
                   </td>
                 </tr>
                </c:forEach>
                </tbody>
               </table>
              </div><!-- Tabla -->
               <tag:paginate max="10" offset="${offset}" count="${count}"
                 uri="carta.htm" next="&raquo;" previous="&laquo;" />
             </div> <!-- card boy -->
          </div><!-- card -->
        </div><!-- col -->
        
    </main>
   
        
        
      </div><!-- Fin contenedor -->
  </body>
</html>
