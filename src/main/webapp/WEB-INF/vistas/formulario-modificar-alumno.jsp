<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>

<c:if test="${not empty redirect}">
    <div class="container animate__animated animate__backInDown mt-5">
        <div class="row">
            <div class="col">
                <h1 class="animate__animated animate__backInLeft font-italic text-center">Modificar alumno</h1>
            </div>
        </div>
        <div class="card-header bg-warning justify-content-center align-items-center vh-50 mt-5">
            <form:form action="modificar-alumno-detalle" modelAttribute="datosAModificar">
                <div class="row justify-content-center align-items-center">
                    <div class="col">
                        <input type="text" name="nombre" class="form-control mt-3" id="nombre" placeholder="Nombre"
                               value="${alumnoModificar.nombre}"/>
                        <c:if test="${not empty erroresValidacion.get('nombreError')}">
                            <p>${erroresValidacion.get('nombreError')}</p>
                        </c:if>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <input type="email" name="email" class="form-control mt-3" id="email" placeholder="Email"
                               value="${alumnoModificar.email}"/>
                        <c:if test="${not empty erroresValidacion.get('emailError')}">
                            <p>${erroresValidacion.get('emailError')}</p>
                        </c:if>
                        <c:if test="${not empty mailExistente}">
                            <p>${mailExistente}</p>
                        </c:if>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <input type="text" name="telefono" class="form-control mt-3" placeholder="Telefono"
                               id="telefono" value="${alumnoModificar.telefono}"/>
                        <c:if test="${not empty erroresValidacion.get('telefonoError')}">
                            <p>${erroresValidacion.get('telefonoError')}</p>
                        </c:if>
                        <input type="hidden" name="idAlumno" value="${alumnoModificar.id}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <input type="submit" value="Modificar" class="btn btn-block btn-warning mt-3">
                    </div>
                </div>
                <c:if test="${not empty mensajeRegistro}">
                    <p>${mensajeRegistro}</p>
                </c:if>
            </form:form>
        </div>
    </div>
</c:if>
<c:if test="${empty redirect}">
    <div class="container animate__animated animate__backInDown mt-5">
        <div class="row">
            <div class="col">
                <h1 class="text-center animate__animated animate__backInLeft font-italic">Modificar alumno</h1>
            </div>
        </div>
        <div class="card-header bg-warning justify-content-center align-items-center vh-50 mt-5">
            <form:form action="modificar-alumno-lista" modelAttribute="datosAModificar">
                <div class="row">
                    <div class="col">
                        <input type="text" name="nombre" class="form-control mt-3" id="nombre" placeholder="Nombre"
                               value="${alumnoModificar.nombre}"/>
                        <c:if test="${not empty erroresValidacion.get('nombreError')}">
                            <p>${erroresValidacion.get('nombreError')}</p>
                        </c:if>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <input type="email" name="email" class="form-control mt-3" id="email" placeholder="Email"
                               value="${alumnoModificar.email}"/>
                        <c:if test="${not empty erroresValidacion.get('emailError')}">
                            <p>${erroresValidacion.get('emailError')}</p>
                        </c:if>
                        <c:if test="${not empty mailExistente}">
                            <p>${mailExistente}</p>
                        </c:if>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <input type="text" name="telefono" class="form-control mt-3" placeholder="Telefono"
                               id="telefono" value="${alumnoModificar.telefono}"/>
                        <c:if test="${not empty erroresValidacion.get('telefonoError')}">
                            <p>${erroresValidacion.get('telefonoError')}</p>
                        </c:if>
                        <input type="hidden" name="idAlumno" value="${alumnoModificar.id}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <input type="submit" value="Modificar" class="btn btn-block btn-success mt-3">
                    </div>
                </div>
                <c:if test="${not empty mensajeRegistro}">
                    <p>${mensajeRegistro}</p>
                </c:if>
            </form:form>
        </div>
    </div>
</c:if>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>
