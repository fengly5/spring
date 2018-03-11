<%-- 
    Document   : crearNotificacion
    Created on : 05-mar-2018, 12:05:07
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
      $.validator.addMethod("valueNotEquals", function(value, element, arg){
  return arg !== value;
 }, "Value must not equal arg.");
      $(document).ready(function() {
        $('#crearNotificacion').validate({
          rules:{
             tipos:{
              required:true
             }
          },
          messages:{
             tipos:{
              required:"El campo tipo es obligatorio"
             }
          },
            errorClass: "is-invalid"
        });
      });
    </script>
    <script type="text/javascript">
      jQuery(function($){
	$.datepicker.regional['es'] = {
		closeText: 'Cerrar',
		prevText: '&#x3c;Ant',
		nextText: 'Sig&#x3e;',
		currentText: 'Hoy',
		monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio',
		'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
		monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun',
		'Jul','Ago','Sep','Oct','Nov','Dic'],
		dayNames: ['Domingo','Lunes','Martes','Mi&eacute;rcoles','Jueves','Viernes','S&aacute;bado'],
		dayNamesShort: ['Dom','Lun','Mar','Mi&eacute;','Juv','Vie','S&aacute;b'],
		dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S&aacute;'],
		weekHeader: 'Sm',
		dateFormat: 'dd/mm/yy',
		firstDay: 1,
		isRTL: false,
		showMonthAfterYear: false,
		yearSuffix: ''};
	$.datepicker.setDefaults($.datepicker.regional['es']);
        });    
 
      $(document).ready(function() {
        $("#datepicker").datepicker({
          altField: "#alternate",
          altFormat: "yy-mm-dd"
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
                <a class="nav-link active" href="${pageContext.request.contextPath}/notificaciones">Notificaciones</a>
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
          <div class="card">
            <div class="card-header">
              <h5>Crear Notificación</h5>
            </div>
            <div class="card-body">
              <form:form method="POST" action="crearNotificacion" modelAttribute="notificacion" id="crearNotificacion">
              <div class="row">
                
                <div class="form-group col-md-6">
                  <form:label path="fecha" for="fecha" id="labelFecha" class="form-text">Fecha</form:label>
                  <form:hidden path="fecha"  id="alternate" ></form:hidden>
                  <input type="text" name="datepicker" class="form-control form-control-sm" id="datepicker" readonly="readonly" size="12" /> 
                </div>
                <div class="form-group col-md-6">
                  <label name="notif_sistema" for="tipo" id="tipo" class="form-text">Tipo</label>
              <select  name="tipos"  id="tipos"  class="form-control form-control-sm ">
                      <option value="" >Selecciona tipo...</option>
                    <c:forEach var ="tipo" items="${tipos_notif}" >
                      <option value="${tipo.idtipo_notif}">${tipo.tipo}</option>
                    </c:forEach>
                  </select>
                </div>
              </div>
              <div class="row">
                <div class="form-group col-md-8">
                  <form:label path="mensaje" for="mensaje" id="mensaje" class="form-text">Mensaje</form:label>
                  <form:textarea path="mensaje" class="form-control form-control-sm" id="mensaje"></form:textarea> 
                </div>
                <div class="form-group col-md-4 align-self-end">
                  <button type="submit" id="enviar" class="btn btn-sm btn-info">Enviar</button>
                </div>
              </div>
                </form:form>
            </div>
          </div>
          
        </div>
      </main>
  </body>
</html>
