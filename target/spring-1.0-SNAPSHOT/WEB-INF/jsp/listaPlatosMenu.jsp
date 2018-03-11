<%-- 
Document   : listaPlatosMenu
Created on : 09-mar-2018, 0:30:29
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
    <title>Listado platos del menu</title>
    <meta  charset="UTF-8">
    <meta name="viewport" content="width=device-width,
          initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/open-iconic-bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/open-iconic-foundation.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/estilos.css" type="text/css">
    <script src="resources/js/jquery-3.3.1.js" ></script>

    <script src="${pageContext.request.contextPath}/resources/js/popper.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.validate.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>

    <script type="text/javascript">
      $.validator.addMethod("valueNotEquals", function (value, element, arg) {
        return arg !== value;
      }, "Value must not equal arg.");
      $(document).ready(function () {
        $('#crearMenu').validate({
          rules: {
            noombre: {
              required: true
            }
          },
          messages: {
            noombre: {
              required: "El campo nombre es obligatorio"
            }
          },
          errorClass: "is-invalid"
        });
      });
    </script>
    <script type="text/javascript">
      jQuery(function ($) {
        $.datepicker.regional['es'] = {
          closeText: 'Cerrar',
          prevText: '&#x3c;Ant',
          nextText: 'Sig&#x3e;',
          currentText: 'Hoy',
          monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
            'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
          monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun',
            'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
          dayNames: ['Domingo', 'Lunes', 'Martes', 'Mi&eacute;rcoles', 'Jueves', 'Viernes', 'S&aacute;bado'],
          dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mi&eacute;', 'Juv', 'Vie', 'S&aacute;b'],
          dayNamesMin: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'S&aacute;'],
          weekHeader: 'Sm',
          dateFormat: 'dd/mm/yy',
          firstDay: 1,
          isRTL: false,
          showMonthAfterYear: false,
          yearSuffix: ''};
        $.datepicker.setDefaults($.datepicker.regional['es']);
      });

      $(document).ready(function () {
        $("#datepicker").datepicker({
          altField: "#alternate",
          altFormat: "yy-mm-dd",
          minDate: "0"
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
              <h5 >Menú del día ${detalle.menu.fecha} </h5>
            </div><!-- card header -->
            <div class="card-body"><!-- card boy -->
              <div class="row align-items-end">
                <div class="row table-responsive-md"> <!-- Tabla -->
                  <div class="col-md-auto">
                    <table class="table table-hover table-striped">
                      <thead>
                        <tr>
                          <th scope="col">Primeros</th>
                        
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${mhp}" var="c" >
                          <tr>
                            <c:set var= "tipo" value="${c.tipo}"/>
                            <c:if test="${tipo == 'Primero'}">
                              <td><c:out value="${c.plato.nombre}"></c:out></td>
                            </c:if>
                           
                          </tr>
                     </c:forEach>
                          
                      </tbody>
                    </table>
                  </div>
                  <div class="col-md-auto"> 
                    <table class="table table-hover table-striped">
                      <thead>
                        <tr>
                          <th scope="col">Segundos</th>


                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${mhp}" var="c" >
                          <tr>
                            <c:set var= "tipo1" value="${c.tipo}"/>
                            <c:if test="${tipo1 == 'Segundo'}">
                              <td><c:out value="${c.plato.nombre}"></c:out></td>
                            </c:if>


                            </tr>
                        </c:forEach>
                      </tbody>
                    </table>
                  </div>
                   <div class="col-md-auto"> 
                    <table class="table table-hover table-striped">
                      <thead>
                        <tr>
                          <th scope="col">Postres</th>


                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${mhp}" var="c" >
                          <tr>
                            <c:set var= "tipo2" value="${c.tipo}"/>
                            <c:if test="${tipo2 == 'Postre'}">
                              <td><c:out value="${c.plato.nombre}"></c:out></td>
                            </c:if>


                            </tr>
                        </c:forEach>
                      </tbody>
                    </table>
                  </div>   
                   
                </div><!-- Tabla -->  
                <div class="row align-items-end">
                 <a href="${pageContext.request.contextPath}/menu"
                               class="btn btn-info btn-sm oi oi-pencil">
                            Volver </a>
 <a href="${pageContext.request.contextPath}/editaMenu/${detalle.menu.idmenu}"
                               class="btn btn-info btn-sm oi oi-pencil">
                            Modificar </a>
                </div>               
              </div>
            </div>

          </div> <!-- card boy -->
        </div><!-- card -->
    
 
  </main>



</div><!-- Fin contenedor -->
</body>
</html>
