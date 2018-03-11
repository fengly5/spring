<%-- 
    Document   : editaCarta
    Created on : 28-feb-2018, 11:20:31
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
    <title>Edita carta</title>
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


      $.validator.addMethod("valueNotEquals", function (value, element, arg) {
        return arg !== value;
      }, "Value must not equal arg.");
      $(document).ready(function () {
        $('#editaCarta').validate({
          rules: {
            idPlato: {
              required: true
            },
            aparece: {
              required: true
            }
          },
          messages: {
            idPlato: {
              required: "Debes elegir un plato."
            },
            aparece: {
              required: "Debes indicar si el plato aparece en la carta."
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
      <main class="row justify-content-md-center">
        <div class="col-md-auto">
          <div class="card "><!-- card -->
            <div class="card-header"> <!-- card header -->
              <h5 ><c:out value="${carta.noombre}"></c:out>

                </h5>

              </div><!-- card header -->
              <div class="card-body"><!-- card boy -->
              <c:if test="${msg != null}" >
                <p class="alert alert-danger"><c:out value="${msg}"></c:out></p>
              </c:if>
              <div class="row "><!-- row de card body -->
                <div class="col">
                  <form:form  method="POST" action="/spring/actualizaCarta" id="editaCarta" modelAttribute="nuevoPlatoCarta"  >
                    <div class=" row justify-content-end ">

                      <div class="form-group col-md-5"> 
                        <label class="form-text" for="platos">Plato</label>
                        <form:select multiple="false" class="form-control form-control-sm " id="platos" path="idPlato"   >
                          <form:option value="">Selecciona plato...</form:option>
                          <form:options items="${platos}"/>
                        </form:select>
                        <form:hidden path="idCarta" value="${carta.idcarta}"></form:hidden>
                        </div>
                        <div class="form-group col-md-4"> 
                          <label class="form-text" for="aparece" >Aparece</label>
                        <form:select path="aparece" id="aparece" class="form-control form-control-sm" >
                          <form:option value="">Aparece?</form:option>
                          <form:option value="1">Sí</form:option>
                          <form:option value="0">No</form:option>
                        </form:select> 
                      </div>

                      <div class="form-group col-md-3 align-self-end ">

                        <button type="submit" id="save"    class="btn btn-info btn-sm ">Añadir Plato
                        </button>
                      </form:form>
                    </div>

                  </div>

                </div>

              </div><!-- row de card body -->

              <div class="row table-responsive-md"> <!-- Tabla -->

                <table class="table table-sm table-hover table-striped">
                  <thead>
                    <tr>
                      <th scope="row">Id</th>
                      <th scope="row">Nonbre</th>
                      <th scope="row">Aparece</th>
                      <th scope="row">Eliminar</th>
                    </tr>
                  </thead>

                  <tbody>

                    <c:forEach items="${platosEnCarta}" var="consulta" >
                      <tr>
                        <th scope="row"><c:out value="${consulta.primaryKey.getPlato().getIdPlato()}"></c:out>
                          </th>
                          <td><c:out value="${consulta.primaryKey.getPlato().getNombre()}"></c:out></td>
                          <td>
                          <c:choose>
                            <c:when test="${consulta.aparece == 1}">
                              <a href="../actualizaEstado/${consulta.primaryKey.getPlato().getNombre()}/${carta.idcarta}/off"
                                 class="btn btn-check btn-sm">
                                <span class="oi oi-check "></span>                              
                              </a>
                            </c:when>
                            <c:otherwise >
                              <a href="../actualizaEstado/${consulta.primaryKey.getPlato().getNombre()}/${carta.idcarta}/on"
                                 class="btn btn-check btn-sm">
                                <span class="oi oi-ban "></span>                              
                              </a>
                            </c:otherwise>
                          </c:choose>


                        </td>
                        <td><a href="../borraPlatoCarta/${consulta.primaryKey.getPlato().getNombre()}/${carta.idcarta}"
                               class="btn btn-danger btn-sm">
                            <span class="oi oi-trash "></span>                              
                          </a>
                        </td>
                      </tr>
                    </c:forEach>

                  </tbody>


                </table>

              </div><!-- Tabla -->


            </div> <!-- card boy -->
          </div><!-- card -->
        </div><!-- col -->

      </main>
    </div><!-- container -->

  </body>
</html>
