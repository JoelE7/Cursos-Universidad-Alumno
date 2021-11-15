<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>


<c:if test="${not empty listaAlumnos}">
    <div class="animate__animated animate__fadeInDown container-fluid mt-5">
        <div class="row">
            <div class="col-6">
                <h1 class="animate__animated animate__backInLeft mb-5 text-right text-white font-italic">Lista de alumnos</h1>
            </div>
            <c:if test="${empty curso}">
                <div class="col-5">
                    <a href="formulario-alumno" class="float-right letra btn btn-success">Registrar alumno</a>
                </div>
            </c:if>
            <c:if test="${not empty curso}">
                <div class="col-3">
                    <a href="formulario-alumno" class="float-right letra btn btn-success">Registrar alumno</a>
                </div>
                <div class="col-3">
                    <td><a href="listar-cursos" class="letra btn btn-success">Volver a la lista de cursos</a>

                </div>
            </c:if>

        </div>
        <div class="row">
            <div class="col">
                <table class="table table-hover table-striped table-bordered table-responsive-xl table-active mt-3">
                    <tr>
                        <td class="letra">Nombre</td>
                        <td class="letra">Email</td>
                        <td colspan="10" class="letra">Acciones</td>
                    </tr>
                    <c:forEach items="${listaAlumnos}" var="alumnos">
                        <tr>
                            <td class="letra">${alumnos.nombre}</td>
                            <td class="letra">${alumnos.email}</td>
                            <c:if test="${empty curso}">
                                <td><a href="alumno-detalle?idAlumno=${alumnos.id}" class="btn btn-block btn-primary">Ver
                                    detalles</a></td>
                                <td><a href="formulario-modificar-alumno-lista?idAlumno=${alumnos.id}"
                                       class="btn btn-block btn-warning">Modificar</a></td>
                                <td><a href="eliminar-alumno?idAlumno=${alumnos.id}" class="btn btn-block btn-danger">Eliminar
                                    alumno</a></td>
                            </c:if>
                            <c:if test="${not empty curso}">
                                <td><a href="agregar-alumno-curso?idCurso=${curso.id}&idAlumno=${alumnos.id}"
                                       class="btn btn-block btn-success">Agregar
                                    alumno</a></td>
                                <c:if test="${not empty curso.listaAlumnos}">
                                    <c:forEach items="${curso.listaAlumnos}" var="eliminar">
                                        <c:if test="${eliminar.id == alumnos.id}">
                                            <td>
                                                <a href="eliminar-alumno-curso?idCurso=${curso.id}&idAlumno=${alumnos.id}"
                                                   class="btn btn-block btn-danger">Eliminar
                                                    alumno del curso</a></td>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:if>
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
