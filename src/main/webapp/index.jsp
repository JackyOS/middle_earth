<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<jsp:include page="partials/head.jsp"></jsp:include>
</head>

<body>

	<jsp:include page="partials/navUsers.jsp"></jsp:include>


<!-- INICIO CARROUSEL -->
	<div id="carouselExampleCaptions" class="carousel slide carousel-fade"
		data-bs-ride="carousel" >
		
		<div class="carousel-indicators">
			<button type="button" data-bs-target="#carouselExampleCaptions"
				data-bs-slide-to="0" class="active" aria-current="true"
				aria-label="Slide 1"></button>
			<button type="button" data-bs-target="#carouselExampleCaptions"
				data-bs-slide-to="1" aria-label="Slide 2"></button>
			<button type="button" data-bs-target="#carouselExampleCaptions"
				data-bs-slide-to="2" aria-label="Slide 3"></button>
		</div>
		
		
		<div class="carousel-inner">
			<div class="carousel-item active" >
				<img src="/turismo/assets/img/fondo7.jpg" class="d-block w-100 h-100" alt="1">
				<div class="carousel-caption d-none d-md-block">
					<h1>- TOURS EN FAMILIA -</h1>
					<h3>UN AUTENTICO AGASAJO HOBBIT CON FOGONES Y <br> 
					MUSICA EN VIVO PARA TODAS LAS EDADES</h3>
				</div>
			</div>
			<div class="carousel-item">
				<img src="/turismo/assets/img/fondo1.jpeg" class="d-block w-100 h-100" alt="2">
				<div class="carousel-caption d-none d-md-block">
					<h1>- ADRENALINA -</h1>
					<h3>AVENTURA EXTREMA VOLANDO CON AGUILAS <br>
					ENTRENADAS Y GUÍAS EXPERIMENTADOS</h3>
				</div>
			</div>
			<div class="carousel-item">
			<img src="/turismo/assets/img/fondo5.jpg" class="d-block w-100 h-100" alt="3">
				<div class="carousel-caption d-none d-md-block">
					<h1 style="background-color: rgba(192, 52, 84, 0.6);">- GOURMET XP -</h1>
					<h3>DISFRUTA UNA INOLVIDABLE DEGUSTACIÓN DE LOS <br> 
					TÍPICOS PLATOS DE COCINA DE LA EDAD MEDIA</h3>
				</div>
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>
	<!-- FIN CARROUSEL -->


	<jsp:include page="/partials/footer.jsp"></jsp:include>
</body>
</html>
