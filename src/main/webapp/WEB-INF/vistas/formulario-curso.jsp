<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>

<div class="container">
    <div class="row">
        <div class="col">
            <h1 class="text-center">Registro de cursos</h1>
        </div>
    </div>
    <div class="card-header">
        <form:form action="curso-validacion" modelAttribute="cursoRegistro">
            <div class="row">
                <div class="col">
                    <input type="text" name="nombreCurso" class="form-control mt-3" id="nombre" placeholder="Nombre"
                           value="${nombreDefault}"/>
                    <c:if test="${not empty erroresValidacion.get('nombreError')}">
                        <p>${erroresValidacion.get('nombreError')}</p>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <input type="text" name="codigoCurso" class="form-control mt-3" id="codigoCurso" placeholder="Codigo del curso"
                           value="${codigoDefault}"/>
                    <c:if test="${not empty erroresValidacion.get('codigoError')}">
                        <p>${erroresValidacion.get('codigoError')}</p>
                    </c:if>
                    <c:if test="${not empty codigoExistente}">
                        <p>${codigoExistente}</p>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <input type="submit" value="Registrar" class="btn btn-block btn-success mt-3">
                </div>
            </div>
            <c:if test="${not empty mensajeRegistro}">
                <p>${mensajeRegistro}</p>
            </c:if>
        </form:form>
    </div>
</div>


<jsp:include page="/WEB-INF/includes/footer.jsp"/>