<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<c:if test="${not empty cursoBuscado}">
    <div class="container-fluid">
        <div class="row">
            <div class="col">
                <table class="table table-responsive table-danger">
                    <tr>
                        <td>Nombre del curso</td>
                        <td>Codigo del curso</td>
                        <td>Cantidad de alumnos</td>
                        <td>Acciones</td>
                        <td><a href="listar-cursos" class="btn btn-success">Volver lista de cursos</a>
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
                    </tr>
                </table>
            </div>
        </div>
    </div>
</c:if>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>