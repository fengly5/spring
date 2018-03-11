<%-- 
    Document   : inicio
    Created on : 30-ene-2018, 11:54:38
    Author     : jcpm0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/jsp/taglib/customTaglib.tld"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta  charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/open-iconic-bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/open-iconic-foundation.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/estilos.css" type="text/css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js" ></script>
    <script src="${pageContext.request.contextPath}/resources/js/popper.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.validate.js"></script>

  <title>Inicio</title>
</head>
<body>
  <div class="container ">
<header class="row justify-content-md-endr ">
        <div class="col "><!-- columna única para la barra de navegación -->
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
                <a class="nav-link " href="${pageContext.request.contextPath}/menu">Menú del día</a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" href="#">Reservas</a>
              </li>
              <li class="nav-item dropdown ">
                <a class="nav-link dropdown-toggle" href="#" 
                   id="navbarDropdownPlatos" role="button" 
                   data-toggle="dropdown" aria-haspopup="true" 
                   aria-expanded="false">Notificaciones</a>
                <div class="dropdown-menu" 
                     aria-labelledby="navbarDropdownPlatos">
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/notificaciones">Sistema</a>
                  <a class="dropdown-item" href="${pageContext.request.contextPath}/notificacionesClientes">Cliente</a>
                  
                </div>
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
      <div class="col-8 ">
        <div class="card ">
          <h5 class="card-header">Reservas</h5>
          <div class="card-body">
            <table class="table table-hover table-striped">
              <thead>
                <tr>
                      <th scope="row">Id</th>
                    <th scope="row">ncomensales</th>
                    <th scope="row">turno</th>
                   
                    <th scope="row">fecha</th>
                    <th scope="row">cliente</th>
                </tr>
              </thead>
              <tbody>

                
                                <c:forEach items="${reservas}" var="consulta">
                    <tr>
                        <th scope="row"><c:out value="${consulta.idreservas}"></c:out></th>
                        <td><c:out value="${consulta.nComensales}"></c:out></td>
                        <td><c:out value="${consulta.turno}"></c:out></td>
                        
                        <td>
                          <fmt:formatDate type = "both" 
         dateStyle = "medium" timeStyle = "medium" value = "${consulta.fechaHora}" var="fecha" />
                        <c:out value="${fecha}"></c:out></td>
                        <td><c:out value="${consulta.cliente.email}"></c:out></td>
                        
                    </tr>
                </c:forEach>
              </tbody>
            </table>
                        <tag:paginate max="10" offset="${offset}" count="${count}"
   uri="inicio.htm" next="&raquo;" previous="&laquo;" />
          </div>
        </div>
      </div>
    </main>
  </div>
</body>
</html>