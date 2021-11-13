<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
    <link href="css/styles.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
          integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <title>Bon Appetite</title>
</head>
<body>
<!-- barra navegacion-->
<div class="container-fluid m-0 p-0">
    <div class="row m-0 p-0">
        <div class="col m-0 p-0">
            <!-- navbar-dark o navbar-light cambia el color del texto de los items -->
            <!-- ojo que a partir del navbar-expand-tamaño será donde el menu estará expandido
            caso contrario estará oculto -->
            <!-- con el fixed-top el menu quedara fijo aunque se haga scroll -->
            <nav class="navbar navbar-expand-md bg-dark navbar-dark text-lg-center">
                <!-- este será el boton que se apretara para sacar el menu colapsable con todas propiedades -->
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#uno">
                    <!-- este es el icono del menu de navegacion -->
                    <span class="navbar-toggler-icon"></span>
                </button>
                <!-- aca es donde se llama al menu -->
                <div class="collapse navbar-collapse" id="uno">
                    <ul class="navbar-nav">
                        <li class="nav-item"><a href="#" class="nav-link">Link 1</a></li>
                        <li class="nav-item"><a href="#" class="nav-link">Link 2</a></li>
                        <li class="nav-item"><a href="#" class="nav-link">Link 3</a></li>
                        <li class="nav-item dropdown">
                            <!-- aca hacemos un menu propio dentro del item -->
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" id="navbardrop">Link
                                4</a>
                            <div class="dropdown-menu">
                                <a href="#" class="dropdown-item">Sub item 1</a>
                                <a href="#" class="dropdown-item">Sub item 2</a>
                                <a href="#" class="dropdown-item">Sub item 3</a>
                                <a href="#" class="dropdown-item">Sub item 4</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
</div>
