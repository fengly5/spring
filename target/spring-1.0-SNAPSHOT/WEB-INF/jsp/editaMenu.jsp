<%-- 
    Document   : crearMenu
    Created on : 08-mar-2018, 11:00:08
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
    <title>Menu</title>
    <meta  charset="UTF-8">
    <meta name="viewport" content="width=device-width,
          initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/open-iconic-bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/open-iconic-foundation.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/estilos.css" type="text/css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js" ></script>

    <script src="${pageContext.request.contextPath}/resources/js/popper.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.validate.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>

 <script type="text/javascript">
      $(document).on('change','.sel',function(){
  $(this).siblings().find('option[value="'+$(this).val()+'"]').remove();
});
   
      $(document).ready(function() {
        $('#crearMenu').validate({
          rules:{
             "primeros[0].plato":{
              required:true
             }
          },
          messages:{

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
              <div class="row align-items-start">

                <div class=" col justify-content-end  ">
                                <c:if test="${msg != null}" >
                <p class="alert alert-danger"><c:out value="${msg}"></c:out></p>
              </c:if>
                  <form:form  method="POST" action="/spring/actualizaMenu" id="crearMenu" modelAttribute="detalle"  >
                    <form:hidden path="menu.fecha"></form:hidden>
                    <form:hidden path="menu.idmenu"></form:hidden>
                    <div class="form-row  justify-content-md-end align-items-md-end">
                    <div class="form-group col-md-8"> 
                      <form:label path="menu.fecha" for="fecha" id="labelFecha" class="form-text">Fecha</form:label>
                      <select name="menu_anterior" class="form-control form-control-sm">
                        <option value="">Selecciona fecha..</option>
                      <c:forEach items="${menus}" var="m">
                        <option value="${m.idmenu}">${m.fecha}</option>
                      </c:forEach>
                        
                      </select>
                      </div>
                      <div class="form-group col-md-4  ">
                        <a href="copiaMenu/${detalle.menu.fecha}"
                               class="btn btn-info btn-sm oi oi-pencil">
                            Copiar Menu </a>
                        
                      </div>
                    </div>
                      <div class="form-row">
                        <div class="form-group col-md-auto">
                          <label id="primeros" class="form-text">Primeros</label>
                          <form:select  path="primeros[0].plato" class="sel form-control form-control-sm">
                            <c:choose>
                              <c:when test="${detalle.primeros[0].plato == 'Selecciona plato...'}">
                                <form:option value="">${detalle.primeros[0].plato}</form:option>
                              </c:when>
                              <c:otherwise>
                                 <form:option value="${detalle.primeros[0].idplato}">${detalle.primeros[0].plato}</form:option>
                              </c:otherwise>
                            </c:choose>
                            <c:forEach items="${plato}" var="p">
                              <c:set var="nombre" value="${p.nombre}"></c:set>
                              <c:out value="${nombre}"/>
                              <c:set var="plato1" value="${detalle.primeros[0].plato}"></c:set>
                              <c:if test="${nombre ne plato1}">
                               <form:option value="${p.idPlato}">${p.nombre}</form:option>
                              </c:if>
                            </c:forEach>
                          </form:select>
                          <form:select  path="primeros[1].plato" class="sel form-control form-control-sm">
                               <c:choose>
                              <c:when test="${detalle.primeros[1].plato == 'Selecciona plato...'}">
                                <form:option value="">${detalle.primeros[1].plato}</form:option>
                              </c:when>
                              <c:otherwise>
                                 <form:option value="${detalle.primeros[1].idplato}">${detalle.primeros[1].plato}</form:option>
                              </c:otherwise>
                            </c:choose>
                         
                            <c:forEach items="${plato}" var="p1">
                              <c:set var="nombre" value="${p1.nombre}"></c:set>
                              <c:set var="plato1" value="${detalle.primeros[1].plato}"></c:set>
                              <c:if test="${nombre ne plato1}">
                               <form:option value="${p1.idPlato}">${p1.nombre}</form:option>
                              </c:if>
                            </c:forEach>
                           
                        </form:select>
                        <form:select  path="primeros[2].plato" class="sel form-control form-control-sm">
                               <c:choose>
                              <c:when test="${detalle.primeros[2].plato == 'Selecciona plato...'}">
                                <form:option value="">${detalle.primeros[2].plato}</form:option>
                              </c:when>
                              <c:otherwise>
                                 <form:option value="${detalle.primeros[2].idplato}">${detalle.primeros[2].plato}</form:option>
                              </c:otherwise>
                            </c:choose>
                         
                             <c:forEach items="${plato}" var="p2">
                              <c:set var="nombre" value="${p2.nombre}"></c:set>
                              <c:set var="plato1" value="${detalle.primeros[2].plato}"></c:set>
                              <c:if test="${nombre ne plato1}">
                               <form:option value="${p2.idPlato}">${p2.nombre}</form:option>
                              </c:if>
                            </c:forEach>                          
                            
                        </form:select>
                      
                        <label id="Segundos" class="form-text">Segundos</label>
                        <form:select  path="segundos[0].plato" class="sel form-control form-control-sm">
                              <c:choose>
                              <c:when test="${detalle.segundos[0].plato == 'Selecciona plato...'}">
                                <form:option value="">${detalle.segundos[0].plato}</form:option>
                              </c:when>
                              <c:otherwise>
                                 <form:option value="${detalle.segundos[0].idplato}">${detalle.segundos[0].plato}</form:option>
                              </c:otherwise>
                            </c:choose>
                          
                            <c:forEach items="${plato}" var="p3">
                              <c:set var="nombre" value="${p3.nombre}"></c:set>
                              <c:set var="plato1" value="${detalle.segundos[0].plato}"></c:set>
                              <c:if test="${nombre ne plato1}">
                               <form:option value="${p3.idPlato}">${p3.nombre}</form:option>
                              </c:if>
                            </c:forEach>
                          
                        </form:select>
                      <form:select  path="segundos[1].plato" class="sel form-control form-control-sm">
                            <c:choose>
                              <c:when test="${detalle.segundos[1].plato == 'Selecciona plato...'}">
                                <form:option value="">${detalle.segundos[1].plato}</form:option>
                              </c:when>
                              <c:otherwise>
                                 <form:option value="${detalle.segundos[1].idplato}">${detalle.segundos[1].plato}</form:option>
                              </c:otherwise>
                            </c:choose>
                         
                            <c:forEach items="${plato}" var="p4">
                              <c:set var="nombre" value="${p.nombre}"></c:set>
                              <c:set var="plato1" value="${detalle.segundos[1].plato}"></c:set>
                              <c:if test="${nombre ne plato1}">
                               <form:option value="${p4.idPlato}">${p4.nombre}</form:option>
                              </c:if>
                            </c:forEach>
                           
                        </form:select>
                        <form:select  path="segundos[2].plato" class="sel form-control form-control-sm">
                              <c:choose>
                              <c:when test="${detalle.segundos[2].plato == 'Selecciona plato...'}">
                                <form:option value="">${detalle.segundos[2].plato}</form:option>
                              </c:when>
                              <c:otherwise>
                                 <form:option value="${detalle.segundos[2].idplato}">${detalle.segundos[2].plato}</form:option>
                              </c:otherwise>
                            </c:choose>
                       
                            <c:forEach items="${plato}" var="p5">
                              <c:set var="nombre" value="${p5.nombre}"></c:set>
                              <c:set var="plato1" value="${detalle.segundos[2].plato}"></c:set>
                              <c:if test="${nombre ne plato1}">
                               <form:option value="${p5.idPlato}">${p5.nombre}</form:option>
                              </c:if>
                            </c:forEach>
                           
                        </form:select>
                      
                          <label id="postres" class="form-text">Postres</label>
                         <form:select  path="postres[0].plato" class="sel form-control form-control-sm">
                              <c:choose>
                              <c:when test="${detalle.postres[0].plato == 'Selecciona plato...'}">
                                <form:option value="">${detalle.postres[0].plato}</form:option>
                              </c:when>
                              <c:otherwise>
                                 <form:option value="${detalle.postres[0].idplato}">${detalle.postres[0].plato}</form:option>
                              </c:otherwise>
                            </c:choose>
                         
                            <c:forEach items="${plato}" var="p6">
                              <c:set var="nombre" value="${p6.nombre}"></c:set>
                              <c:set var="plato1" value="${detalle.postres[0].plato}"></c:set>
                              <c:if test="${nombre ne plato1}">
                               <form:option value="${p6.idPlato}">${p6.nombre}</form:option>
                              </c:if>
                            </c:forEach>
                           
                        </form:select>
                          <form:select  path="postres[1].plato" class="sel form-control form-control-sm">
                            <c:choose>
                              <c:when test="${detalle.postres[1].plato == 'Selecciona plato...'}">
                                <form:option value="">${detalle.postres[1].plato}</form:option>
                              </c:when>
                              <c:otherwise>
                                 <form:option value="${detalle.postres[1].idplato}">${detalle.postres[1].plato}</form:option>
                              </c:otherwise>
                            </c:choose>
                          
                            <c:forEach items="${plato}" var="p7">
                              <c:set var="nombre" value="${p7.nombre}"></c:set>
                              <c:set var="plato1" value="${detalle.postres[1].plato}"></c:set>
                              <c:if test="${nombre ne plato1}">
                               <form:option value="${p7.idPlato}">${p7.nombre}</form:option>
                              </c:if>
                            </c:forEach>
                            
                        </form:select>
                       <form:select  path="postres[2].plato" class="sel form-control form-control-sm">
                            <c:choose>
                              <c:when test="${detalle.postres[2].plato == 'Selecciona plato...'}">
                                <form:option value="">${detalle.postres[2].plato}</form:option>
                              </c:when>
                              <c:otherwise>
                                 <form:option value="${detalle.postres[2].idplato}">${detalle.postres[2].plato}</form:option>
                              </c:otherwise>
                            </c:choose>
                          
                            <c:forEach items="${plato}" var="p8">
                              <c:set var="nombre" value="${p8.nombre}"></c:set>
                              <c:set var="plato1" value="${detalle.postres[2].plato}"></c:set>
                              <c:if test="${nombre ne plato1}">
                               <form:option value="${p8.idPlato}">${p8.nombre}</form:option>
                              </c:if>
                            </c:forEach>
                            
                        </form:select>
                      </div>
                      
                        
                    </div>  
                                 <div class="form-row justify-content-md-end">
                          <button type="submit" id="save"    class="btn btn-info btn-sm oi oi-file ">Guardar </button>
                        </div>
                  </div>
                </form:form>  
              </div>
            </div>

          </div> <!-- card boy -->
        </div><!-- card -->
    </div><!-- col -->

  </main>



</div><!-- Fin contenedor -->
</body>
</html>