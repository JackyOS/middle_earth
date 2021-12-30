<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
<title>Tierra Media</title>

<link rel="stylesheet" href="/turismo/assets/stylesheets/styleHome.css">
<script src="codigo.js" defer></script>
</head>


<body>
	<nav
		class="navbar nav-style navbar-dark fixed-top navbar-expand scrolling-navbar">
		<!--  bg-dark -->
		<!-- 	navbar fixed-top navbar-expand-lg navbar-dark scrolling-navbar -->
		<div class="container">
			<a class="navbar-brand" href="#">Turismo en la Tierra Media</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
				aria-controls="navbarCollapse" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>


			<div class="collapse navbar-collapse" id="navbarCollapse">
				<ul class="navbar-nav me-auto mb-2 mb-md-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#promociones">Promociones</a></li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#atracciones">Atracciones</a></li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#testimonios">Testimonios</a></li>
				</ul>
				<ul class="navbar-nav">
					<li class="nav-item final" style=""><a href="/turismo/login"
						class="btn button">Login</a></li>
				</ul>
			</div>
		</div>
	</nav>



	<main class="main">

		<div class="imagenDestacada">

			<!--  
			<div class="texto-imagen">
				<h2>Bienvenidos a</h2>
				<h3>TIERRA MEDIA</h3>
				<p>Conoce el más encantador de los destinos</p>
				<p>y sus fantásticas atracciones</p>
			</div>
			-->

		</div>


		<div class="promociones-contenedor">
			<div class="container " id="promociones" style="padding-top: 50px">
				<h2 class="contenedor-tarjetas-titulo">Promociones</h2>
				<div class="contenedor-curso active">
					<div class="contenedor-tarjetas">
						<c:forEach items="${promotions}" var="promotion">
							<div class="tarjeta-curso">
								<img src="${promotion.image}" alt="foto" class="tarjeta-img">
								<div class="tarjeta-texto">
									<h3 class="tarjeta__titulo">
										<c:out value="${promotion.name}"></c:out>
									</h3>
									<hr>
									<span class="tarjeta__p" style="margin-right: 20px;">
										<i class="far fa-money-bill-alt"></i>   Costo:$
										<fmt:formatNumber type="number" maxFractionDigits="2"
											value="${promotion.cost}" />
									</span>
									

									<span class="tarjeta__p">
										<i class="far fa-clock"></i> Duración:
										<c:out value="${promotion.duration}"></c:out>
									</span>
									<hr>
									<p class="tarjeta__p">
										<c:out value="${promotion.description}"></c:out>
									</p>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>


		<div class="atracciones-contenedor">
			<div class="container" id="atracciones" style="padding-top: 50px">
				<h2 class="contenedor-tarjetas-titulo">Atracciones</h2>
				<div class="contenedor-curso active">
					<div class="contenedor-tarjetas">
						<c:forEach items="${attractions}" var="attraction">

							<div class="tarjeta-curso">
								<img src="${attraction.image}" alt="foto" class="tarjeta-img">
								<div class="tarjeta-texto">
									<h3 class="tarjeta__titulo">
										<c:out value="${attraction.name}"></c:out>
									</h3>
									<hr>
									<span class="tarjeta__p" style="margin-right: 20px;">
										<i class="far fa-money-bill-alt"></i>   Costo:$
										<fmt:formatNumber type="number" maxFractionDigits="2"
											value="${attraction.cost}" />
									</span>
									
									<span class="tarjeta__p">
										<i class="far fa-clock"></i> Duración:
										<c:out value="${attraction.duration}"></c:out>
									</span>
									<hr>
									<p class="tarjeta__p">
										<c:out value="${attraction.description}"></c:out>
									</p>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>



		<div id="testimonios" class="testimonios container"
			style="padding-top: 50px">

			<h2>Testimonios</h2>

			<div
				style="display: flex; flex-wrap: wrap; justify-content: space-around;">

				<img alt="testimonio" src="assets/img/testimonio1.png"
					class="img-testimonios"> <img alt="testimonio2"
					src="assets/img/testimonio2.png" class="img-testimonios"> <img
					alt="testimonio3" src="assets/img/testimonio3.png"
					class="img-testimonios">
			</div>
		</div>
		
		
</main>


<jsp:include page="/partials/footer.jsp"></jsp:include>


</body>


</html>

