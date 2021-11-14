<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>


<c:if test="${not empty listaCursos}">
    <div class="container-fluid mt-5">
        <div class="row">
            <div class="col-7">
                <h1 class="mb-5 text-right text-white font-italic">Lista de cursos</h1>
            </div>
            <div class="col-5">
                <a href="formulario-curso" class="float-right letra btn btn-success">Registrar curso</a>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <table class="table table-hover table-striped table-bordered table-responsive-xl table-active mt-3">
                    <tr>
                        <td class="letra">Nombre</td>
                        <td class="letra">Codigo del curso</td>
                        <td class="letra">Cantidad de alumnos</td>
                        <td class="letra" colspan="10">Acciones</td>
                    </tr>
                    <c:forEach items="${listaCursos}" var="cursos">
                        <tr>
                            <td class="letra">${cursos.nombre}</td>
                            <td class="letra">${cursos.codigo}</td>
                            <td class="letra">${cursos.cantidadDeAlumnos}</td>
                            <td class="letra"><a href="formulario-modificar-curso-lista?idCurso=${cursos.id}"
                                   class="btn btn-block btn-warning">Modificar</a></td>
                            <td><a href="curso-detalle?idCurso=${cursos.id}" class=" btn btn-block btn-primary">Ver
                                detalles</a></td>
                            <td><a href="eliminar-curso?idCurso=${cursos.id}" class=" btn btn-block btn-danger">Eliminar
                                curso</a></td>
                            <c:if test="${cursos.cantidadDeAlumnos gt 0}">
                                <td><a href="quitar-alumno?idCurso=${cursos.id}" class="btn btn-block btn-danger">Quitar
                                    alumno del curso</a></td>
                            </c:if>
                            <td><a href="agregar-alumno?idCurso=${cursos.id}" class="btn btn-block btn-success">Agregar alumno
                            </a></td>
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
                    <h2 class="text-warning letra">${listaCursosVacia}</h2>
                </div>
                <div class="col-3">
                    <a href="formulario-curso" class="btn btn-primary btn-lg">Registro de cursos</a>
                </div>
            </div>
        </div>
    </div>
</c:if>


<jsp:include page="/WEB-INF/includes/footer.jsp"/>
