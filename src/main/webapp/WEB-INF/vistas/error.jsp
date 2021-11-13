<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<div class="container">
    <div class="card-header mt-5">
        <div class="row">
            <div class="col-9">
                <c:if test="${not empty alumnoNoEncontrado}">
                    <h2>${alumnoNoEncontrado}</h2>
                </c:if>
            </div>
            <div class="col-3">
                <a href="listar-alumnos" class="btn btn-primary btn-lg">Volver lista de alumnos</a>
            </div>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/includes/footer.jsp"/>
