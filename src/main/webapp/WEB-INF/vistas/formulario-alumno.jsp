<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>

    <div class="animate__animated animate__backInDown container mt-5">
        <div class="row">
            <div class="col">
                <h1 class="animate__animated animate__backInLeft mb-5 font-italic text-center text-white">Registro de alumnos</h1>
            </div>
        </div>
        <div class="card-header bg-warning justify-content-center align-items-center vh-50 m-0">
            <form:form action="registro-validacion" modelAttribute="alumnoRegistro">
                <div class="row justify-content-center align-items-center">
                    <div class="col">
                        <input type="text" name="nombre" class="form-control mt-3" id="nombre" placeholder="Nombre"
                               value="${nombreDefault}"/>
                        <c:if test="${not empty erroresValidacion.get('nombreError')}">
                            <p class="text-center letra text-danger">${erroresValidacion.get('nombreError')}</p>
                        </c:if>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <input type="email" name="email" class="form-control mt-3" id="email" placeholder="Email"
                               value="${emailDefault}"/>
                        <c:if test="${not empty erroresValidacion.get('emailError')}">
                            <p class="text-center letra text-danger">${erroresValidacion.get('emailError')}</p>
                        </c:if>
                        <c:if test="${not empty mailExistente}">
                            <p class="text-center letra text-danger">${mailExistente}</p>
                        </c:if>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <input type="text" name="telefono" class="form-control mt-3" placeholder="Telefono"
                               id="telefono" value="${telefonoDefault}"/>
                        <c:if test="${not empty erroresValidacion.get('telefonoError')}">
                            <p class="text-center letra text-danger">${erroresValidacion.get('telefonoError')}</p>
                        </c:if>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <input type="submit" value="Registrar alumno" class="btn btn-block btn-success mt-3">
                    </div>
                </div>
                <c:if test="${not empty mensajeRegistro}">
                    <p class="text-center display-4 text-white">${mensajeRegistro}</p>
                </c:if>
            </form:form>
        </div>
    </div>


<jsp:include page="/WEB-INF/includes/footer.jsp"/>