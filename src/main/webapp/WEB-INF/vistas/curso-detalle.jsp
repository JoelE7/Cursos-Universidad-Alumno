<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<c:if test="${not empty cursoBuscado}">
    <div class="container-fluid animate__animated animate__backInDown mt-5">
        <div class="row">
            <div class="col-7">
                <h1 class="animate__animated animate__backInLeft font-italic text-right mb-5">Detalle de curso</h1>
            </div>
            <div class="col-5">
                <a href="listar-cursos" class="float-right letra btn btn-success">Volver a la lista de cursos</a>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <table class="table table-hover table-striped table-bordered table-responsive-xl table-active mt-3">
                    <tr>
                        <td class="letra">Nombre del curso</td>
                        <td class="letra">Codigo del curso</td>
                        <td class="letra">Cantidad de alumnos</td>
                        <td colspan="3" class="letra">Acciones</td>
                    </tr>
                    <tr>
                        <td>${cursoBuscado.nombre}</td>
                        <td>${cursoBuscado.codigo}</td>
                        <td>${cursoBuscado.cantidadDeAlumnos}</td>
                        <td><a href="formulario-modificar-curso-detalle?idCurso=${cursoBuscado.id}"
                               class="btn btn-warning">Modificar</a></td>
                        <td><a href="eliminar-curso?idCurso=${cursoBuscado.id}" class="btn btn-danger">Eliminar
                            curso</a>
                        </td>
                        <td><a href="agregar-alumno?idCurso=${cursoBuscado.id}" class="btn btn-block btn-success">Agregar alumno
                        </a></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</c:if>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>