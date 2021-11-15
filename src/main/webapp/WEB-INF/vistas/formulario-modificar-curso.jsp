<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>

<c:if test="${empty redirect}">
<div class="container animate__animated animate__backInDown mt-5">
    <div class="row">
        <div class="col">
            <h1 class="animate__animated animate__backInLeft font-italic text-center mb-5">Modificar curso</h1>
        </div>
    </div>
    <div class="card-header bg-warning justify-content-center align-items-center vh-50 m-0">
        <form:form action="modificar-curso-lista" modelAttribute="datosAModificar">
            <div class="row">
                <div class="col">
                    <c:if test="${empty nombreDefault}">
                        <input type="text" name="nombreCurso" class="form-control" id="nombreCurso"
                               value="${cursoModificar.nombre}" placeholder="Nombre del curso">
                    </c:if>
                    <c:if test="${not empty nombreDefault}">
                        <input type="text" name="nombreCurso" class="form-control" id="nombreCurso"
                               value="${nombreDefault}" placeholder="Nombre del curso">
                    </c:if>
                    <c:if test="${not empty erroresValidacion.get('nombreError')}">
                        <p class="text-center letra text-danger">${erroresValidacion.get('nombreError')}</p>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <c:if test="${empty codigoDefault}">
                        <input type="text" name="codigoCurso" class="form-control mt-3" id="nombreCurso"
                               value="${cursoModificar.codigo}" placeholder="Codigo del curso">
                    </c:if>
                    <c:if test="${not empty codigoDefault}">
                        <input type="text" name="codigoCurso" class="form-control mt-3" id="codigoCurso"
                               value="${codigoDefault}" placeholder="Codigo del curso">
                    </c:if>
                    <c:if test="${not empty erroresValidacion.get('codigoError')}">
                        <p class="text-center letra text-danger">${erroresValidacion.get('codigoError')}</p>
                    </c:if>
                    <c:if test="${not empty codigoExistente}">
                        <p class="text-center letra text-danger">${codigoExistente}</p>
                    </c:if>
                    <input type="hidden" name="idCurso" value="${cursoModificar.id}"/>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <input type="submit" value="Modificar curso" class="btn btn-block btn-success mt-3">
                </div>
            </div>
        </form:form>
    </div>
</div>
</c:if>
<c:if test="${not empty redirect}">
    <div class="container animate__animated animate__backInDown mt-5">
        <div class="row">
            <div class="col">
                <h1 class="animate__animated animate__backInLeft font-italic text-center mb-5">Modificar curso</h1>
            </div>
        </div>
        <div class="card-header mt-5">
            <form:form action="modificar-curso-detalle" modelAttribute="datosAModificar">
                <div class="row">
                    <div class="col">
                        <c:if test="${empty nombreDefault}">
                            <input type="text" name="nombreCurso" class="form-control" id="nombreCurso"
                                   value="${cursoModificar.nombre}" placeholder="Nombre del curso">
                        </c:if>
                        <c:if test="${not empty nombreDefault}">
                            <input type="text" name="nombreCurso" class="form-control" id="nombreCurso"
                                   value="${nombreDefault}" placeholder="Nombre del curso">
                        </c:if>
                        <c:if test="${not empty erroresValidacion.get('nombreError')}">
                            <p class="text-center letra text-danger">${erroresValidacion.get('nombreError')}</p>
                        </c:if>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <c:if test="${empty codigoDefault}">
                            <input type="text" name="codigoCurso" class="form-control mt-5" id="nombreCurso"
                                   value="${cursoModificar.codigo}" placeholder="Codigo del curso">
                        </c:if>
                        <c:if test="${not empty codigoDefault}">
                            <input type="text" name="codigoCurso" class="form-control mt-5" id="codigoCurso"
                                   value="${codigoDefault}" placeholder="Codigo del curso">
                        </c:if>
                        <c:if test="${not empty erroresValidacion.get('codigoError')}">
                            <p class="text-center letra text-danger">${erroresValidacion.get('codigoError')}</p>
                        </c:if>
                        <c:if test="${not empty codigoExistente}">
                            <p class="text-center letra text-danger">${codigoExistente}</p>
                        </c:if>
                        <c:if test="${empty idDefault}">
                            <input type="hidden" name="idCurso" value="${cursoModificar.id}"/>
                        </c:if>
                        <c:if test="${not empty idDefault}">
                            <input type="hidden" name="idCurso" value="${idDefault}"/>
                        </c:if>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <input type="submit" value="Modificar curso" class="btn btn-block btn-success mt-3">
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</c:if>


<jsp:include page="/WEB-INF/includes/footer.jsp"/>