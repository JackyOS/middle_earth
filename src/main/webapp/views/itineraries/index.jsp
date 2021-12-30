<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>


<style type="text/css">
body {
	background-image: url(/turismo/assets/img/fondo2.jpg);
	display: table;
	margin: 0;
	top: 0;
	background-size: cover;
	width: 100%;
	height: 100%;
}

/* Contenedor */
.contenedor-tarjetas {
	display: flex;
	width: 100%;
	justify-content: space-evenly;
	align-items: center;
	flex-wrap: wrap;
}

.tarjeta-curso {
	background: #283048; /* fallback for old browsers */
	background: -webkit-linear-gradient(to right, #283048, #859398);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to right, #283048, #859398);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	border-radius: 10px;
	max-width: 400px;
	min-width: 250px;
	min-height: 58vh;
	flex-grow: 1;
	margin: 0 5px 25px;
	padding: 15px 5px;
	text-align: center;
	box-shadow: 0 2px 15px #000;
	transition: transform 0.6s;
}

.tarjeta-curso:hover {
	box-shadow: 0 0 6px 0 rgba(0, 0, 0, .5);
	transform: scale(1.03);
	/* cursor: pointer; */
}


.tarjeta-img {
	width: 95%;
	height: 30vh;
	border-radius: 10px;
}

.tarjeta-texto {
	padding: 0 5px;
	/* height: 20vh; */
}

.tarjeta__titulo {
	color: #fff;
	font-size: 35px;
	margin-top: 5px;
	/* line-height: 35px; */
}


.container__p{
	display: flex;
	alig-item: center;
	justify-content:space-around;
}

.tarjeta__p {
	color: #fff;
	font-weight: 300;
	margin: 0;
	
}



</style>



</head>
<body>

	<jsp:include page="/partials/navUsers.jsp"></jsp:include>

	<main class="container">



		<div class="p-4 mb-3 rounded" style="margin-top: 80px; text-align:center; ">
			<h1><span class="fire">ITINERARIO</span></h1>
		</div>


	<div class="container " id="itinerarios" style="padding-top: 50px">
				<div class="contenedor-curso active">
					<div class="contenedor-tarjetas">
						
						<c:forEach items="${itineraries}" var="itinerario">
							<div class="tarjeta-curso">
								<img src="${itinerario.image}" alt="foto" class="tarjeta-img">
								<div class="tarjeta-texto">
									<h3 class="tarjeta__titulo">
										<c:out value="${itinerario.name}"></c:out>
									</h3>
									<hr>
									<span class="tarjeta__p" style="margin-right: 20px;">
										<i class="far fa-money-bill-alt"></i>   Costo:$
										<fmt:formatNumber type="number" maxFractionDigits="2"
											value="${itinerario.cost}"/>
									</span>
									

									<span class="tarjeta__p">
										<i class="far fa-clock"></i> Duración:
										<c:out value="${itinerario.duration}"></c:out>
									</span>
									<hr>
									<p class="tarjeta__p">
										<c:out value="${itinerario.description}"></c:out>
									</p>									
								</div>
								
							</div>
						</c:forEach>						
					</div>
				</div>
			</div>
	




	</main>

<jsp:include page="/partials/footer.jsp"></jsp:include>

</body>
</html>
