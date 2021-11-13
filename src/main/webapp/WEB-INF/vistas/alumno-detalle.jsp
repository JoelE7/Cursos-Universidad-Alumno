<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<c:if test="${not empty alumnoBuscado}">
    <div class="container-fluid">
        <div class="row">
            <div class="col">
                <table class="table table-responsive table-danger">
                    <tr>
                        <td>Nombre</td>
                        <td>Email</td>
                        <td>Telefono</td>
                        <td>Acciones</td>
                        <td><a href="listar-alumnos" class="btn btn-success">Volver lista de alumnos</a>
                    </tr>
                    <tr>
                        <td>${alumnoBuscado.nombre}</td>
                        <td>${alumnoBuscado.email}</td>
                        <td>${alumnoBuscado.telefono}</td>
                        <td><a href="formulario-modificar-alumno-detalle?idAlumno=${alumnoBuscado.id}" class="btn btn-warning">Modificar</a></td>
                        <td><a href="eliminar-alumno?idAlumno=${alumnoBuscado.id}" class="btn btn-danger">Eliminar alumno</a>
                        <td><a href="eliminar-alumno?idAlumno=${alumnoBuscado.id}" class="btn btn-light">Agregar alumno a un curso</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${not empty alumnoNoEncontrad}">
    <div class="container-fluid">
        <div class="row">
            <div class="col">
                <h1>${AlumnoNoEncontrad}</h1>
            </div>
        </div>
    </div>
</c:if>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>

