<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>

    <c:if test="${not empty listaAlumnos}">
        <div class="container-fluid">
            <div class="row">
                <div class="col">
                    <table class="table table-responsive table-danger">
                        <tr>
                            <td>Nombre</td>
                            <td>Email</td>
                            <td>Telefono</td>
                            <td>Acciones</td>
                        </tr>
                        <c:forEach items="${listaAlumnos}" var="alumnos">
                            <tr>
                                <td>${alumnos.nombre}</td>
                                <td>${alumnos.email}</td>
                                <td>${alumnos.telefono}</td>
                                <td><a href="formulario-modificar-alumno-lista?idAlumno=${alumnos.id}"
                                       class="btn btn-warning">Modificar</a></td>
                                <td><a href="alumno-detalle?idAlumno=${alumnos.id}" class="btn btn-primary">Ver
                                    detalles</a></td>
                                <td><a href="eliminar-alumno?idAlumno=${alumnos.id}" class="btn btn-danger">Eliminar
                                    alumno</a>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </c:if>
<c:if test="${not empty listaAlumnosVacia}">
    <div class="container">
        <div class="card-header mt-5">
            <div class="row">
                <div class="col-9">
                        <h2>${listaAlumnosVacia}</h2>
                </div>
                <div class="col-3">
                    <a href="formulario-alumno" class="btn btn-primary btn-lg">Registro de alumnos</a>
                </div>
            </div>
        </div>
    </div>
</c:if>


<jsp:include page="/WEB-INF/includes/footer.jsp"/>
