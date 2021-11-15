<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>

<div class="animate__animated animate__backInDown container mt-5">
    <div class="row">
        <div class="col">
            <h1 class="animate__animated animate__backInLeft mb-5 text-center text-white font-italic">Registro de cursos</h1>
        </div>
    </div>
    <div class="card-header bg-warning justify-content-center align-items-center vh-50 m-0">
        <form:form action="curso-validacion" modelAttribute="cursoRegistro">
            <div class="row">
                <div class="col">
                    <input type="text" name="nombreCurso" class="form-control mt-3" id="nombre" placeholder="Nombre"
                           value="${nombreDefault}"/>
                    <c:if test="${not empty erroresValidacion.get('nombreError')}">
                        <p class="text-center letra text-danger">${erroresValidacion.get('nombreError')}</p>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <input type="text" name="codigoCurso" class="form-control mt-3" id="codigoCurso" placeholder="Codigo del curso"
                           value="${codigoDefault}"/>
                    <c:if test="${not empty erroresValidacion.get('codigoError')}">
                        <p class="text-center letra text-danger">${erroresValidacion.get('codigoError')}</p>
                    </c:if>
                    <c:if test="${not empty codigoExistente}">
                        <p class="text-center letra text-danger">${codigoExistente}</p>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <input type="submit" value="Registrar curso" class="btn btn-block btn-success mt-3">
                </div>
            </div>
            <c:if test="${not empty mensajeRegistro}">
                <p class="text-center display-4 text-white">${mensajeRegistro}</p>
            </c:if>
        </form:form>
    </div>
</div>


<jsp:include page="/WEB-INF/includes/footer.jsp"/>