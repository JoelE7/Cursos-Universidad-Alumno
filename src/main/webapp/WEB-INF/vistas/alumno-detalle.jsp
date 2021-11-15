<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<c:if test="${not empty alumnoBuscado}">
    <div class="container-fluid mt-5">
        <div class="row">
            <div class="col-7">
                <h1 class="mb-5 text-right text-white font-italic">Detalle de alumno</h1>
            </div>
            <div class="col-5">
                <a href="listar-alumnos" class="float-right letra btn btn-success">Volver a la lista de alumnos</a>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <table class="table table-hover table-striped table-bordered table-responsive-xl table-active mt-3">
                    <tr>
                        <td class="letra">Nombre</td>
                        <td class="letra">Email</td>
                        <td class="letra">Telefono</td>
                        <td colspan="10" class="letra">Acciones</td>
                    </tr>
                    <tr>
                        <td class="letra">${alumnoBuscado.nombre}</td>
                        <td class="letra">${alumnoBuscado.email}</td>
                        <td class="letra">${alumnoBuscado.telefono}</td>
                        <td><a href="formulario-modificar-alumno-detalle?idAlumno=${alumnoBuscado.id}"
                               class="btn btn-block btn-warning">Modificar</a></td>
                        <td><a href="eliminar-alumno?idAlumno=${alumnoBuscado.id}" class="btn btn-block btn-danger">Eliminar
                            alumno</a>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</c:if>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>

