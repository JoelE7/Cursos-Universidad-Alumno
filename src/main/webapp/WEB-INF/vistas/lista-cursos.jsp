<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>


<c:if test="${not empty listaCursos}">
    <div class="container-fluid">
        <div class="row">
            <div class="col">
                <table class="table table-responsive table-danger">
                    <tr>
                        <td>Id</td>
                        <td>Nombre</td>
                        <td>Codigo del curso</td>
                        <td>Cantidad de alumnos</td>
                        <td>Acciones</td>
                    </tr>
                    <c:forEach items="${listaCursos}" var="cursos">
                        <tr>
                            <td>${cursos.id}</td>
                            <td>${cursos.nombre}</td>
                            <td>${cursos.codigo}</td>
                            <td>${cursos.cantidadDeAlumnos}</td>
                            <td><a href="formulario-modificar-curso-lista?idCurso=${cursos.id}"
                                   class="btn btn-warning">Modificar</a></td>
                            <td><a href="curso-detalle?idCurso=${cursos.id}" class="btn btn-primary">Ver
                                detalles</a></td>
                            <c:if test="${cursos.cantidadDeAlumnos gt 0}">
                            <td><a href="eliminar-curso?idCurso=${cursos.id}" class="btn btn-danger">Eliminar
                                alumno</a></td>
                            </c:if>
                            <td><a href="eliminar-curso?idCurso=${cursos.id}" class="btn btn-danger">Eliminar
                                curso</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
    >
</c:if>

<c:if test="${not empty listaCursosVacia}">
    <div class="container">
        <div class="card-header mt-5">
            <div class="row">
                <div class="col-9">
                    <h2>${listaCursosVacia}</h2>
                </div>
                <div class="col-3">
                    <a href="formulario-curso" class="btn btn-primary btn-lg">Registro de cursos</a>
                </div>
            </div>
        </div>
    </div>
</c:if>


<jsp:include page="/WEB-INF/includes/footer.jsp"/>
