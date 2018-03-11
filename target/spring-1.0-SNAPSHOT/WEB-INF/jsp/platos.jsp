<%-- 
    Document   : platos
    Created on : 01-feb-2018, 19:23:01
    Author     : jcpm0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/jsp/taglib/customTaglib.tld"%>
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
    </header>
    <main class="row  ">
      <div class="col ">
        <div class="card ">
          <div class="card-header">
          <h5 >Platos</h5>
          </div>
          <div class="card-body">
            <div class="row align-items-start">
            <div class="col-10"></div>
            <div class="col-2">
              <a href="crearPlato" class="btn btn-info btn-xs"> 
                <i class="oi oi-pencil"></i> Crear Plato
             </a>           
            </div>
            </div>
            <div class="row table-responsive-md">
            <table class="table table-sm table-hover table-striped">
              <thead>
                <tr>
                      <th scope="row">Id</th>
                    <th scope="row">Tipo</th>
                    <th scope="row">Nonbre</th>
                    <th scope="row">Precio 1</th>
                    <th scope="row">Precio 2</th>
                    <th scope="row">Precio 3</th>
                    <th scope="row">Editar</th>
                    <th scope="row">Eliminar</th>
                </tr>
              </thead>
              <tbody>

                
                <c:forEach items="${platos}" var="consulta" >
                    <tr>
                        <th scope="row"><c:out value="${consulta.idPlato}"></c:out></th>
                        <c:forEach items="${consulta.tipo_plato}" var="tipo">
                        <td><c:out value="${tipo.tipo}"></c:out></td>
                        </c:forEach>
                        <td><c:out value="${consulta.nombre}"></c:out></td>
                        <td><c:out value="${consulta.precioTapa}"></c:out></td>
                        <td><c:out value="${consulta.precioMedia}"></c:out></td>
                        <td><c:out value="${consulta.precioRacion}"></c:out></td>
                        <td><a href="editaPlato/${consulta.idPlato}?offset=${offset}"
                               class="btn btn-info btn-sm">
                            <i class="oi oi-pencil">
                              
                            </i> </a></td>
                        <td><a href="borraPlato/${consulta.idPlato}"
                               class="btn btn-danger btn-sm">
          <span class="oi oi-trash "></span>                              
                             </a></td>
                    </tr>
                </c:forEach>
              </tbody>
            </table>
              </div>
            <tag:paginate max="10" offset="${offset}" count="${count}"
   uri="platos.htm" next="&raquo;" previous="&laquo;" />
            
            </div>
        </div>
      </div>
    </main>
  </div>
</body>
</html>