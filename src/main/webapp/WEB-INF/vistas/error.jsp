<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<div class="container">
    <div class="card-header mt-5">
        <div class="row">
            <div class="col-8">
                <c:if test="${not empty mensaje}">
                    <h2 class="text-warning">${mensaje}</h2>
                </c:if>
            </div>
            <div class="col-2">
                <a href="listar-alumnos" class="btn btn-primary btn-lg">Volver lista de alumnos</a>
            </div>
            <div class="col-2">
                <a href="listar-cursos" class="btn btn-success btn-lg">Volver lista de cursos</a>
            </div>
        </div>
    </div>
</div>

<c:if test="${not empty errorMsg}">
    <div class="container">
        <div class="card-header mt-5">
            <div class="row">
                <div class="col">
                    <h2>${errorMsg}</h2>
                </div>
            </div>
        </div>
    </div>
</c:if>


<jsp:include page="/WEB-INF/includes/footer.jsp"/>
