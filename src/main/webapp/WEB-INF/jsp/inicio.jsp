<%-- 
    Document   : inicio
    Created on : 30-ene-2018, 11:54:38
    Author     : jcpm0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

  <title>Inicio</title>
</head>
<body>
  <div class="container ">
    <header class="row align-items-start">
      <div class="col">
        <nav class="navbar navbar-expand-sm navbar-dark bg-primary ">
          <a class="navbar-brand" href="#">#BienMeSabe</a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse " id="navbarSupportedContent">
            <ul class="navbar-nav   ">
              <li class="nav-item">
                  <a class="nav-link active" href="${pageContext.servletContext.contextPath}/platos.htm">Platos</a>
              </li>
              <li class="nav-item dropdown ">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPlatos" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Carta</a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownPlatos">
                  <a class="dropdown-item" href="#">Crear carta</a>
                  <a class="dropdown-item" href="#">Eliminar carta</a>
                  <a class="dropdown-item" href="#">Listado de cartas</a>
                </div>
              </li>
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Menú del día</a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <a class="dropdown-item" href="#">Crear</a>
                  <a class="dropdown-item" href="#">Eliminar</a>
                </div>
              </li>
              <li class="nav-item">
                <a class="nav-link active" href="#">Reservas</a>
              </li>
              <li class="nav-item dropdown ">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPlatos" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Notificaciones</a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownPlatos">
                  <a class="dropdown-item" href="#">Sistema</a>
                  <a class="dropdown-item" href="#">Cliente</a>
                  <a class="dropdown-item" href="#">Listado de Notificaciones</a>
                </div>
              </li>
              <li class="nav-item">
                <a class="nav-link " href="#">Salir</a>
              </li>
            </ul>

          </div>
        </nav>
      </div>
    </header>
    <main class="row justify-content-md-center ">
      <div class="col-8 ">
        <div class="card ">
          <h5 class="card-header">Reservas</h5>
          <div class="card-body">
            <table class="table">
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
                        
                        <td><c:out value="${consulta.fechaHora}"></c:out></td>
                        <td><c:out value="${consulta.cliente.email}"></c:out></td>
                        
                    </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </main>
  </div>
</body>
</html>