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
</style>

<script
	src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.19/dist/sweetalert2.all.min.js"
	defer></script>

<script defer>
    function confirmarCompra(id, soyPromo) {
        Swal.fire({
            title: '¿Desea comprar la oferta?',
            text: "Una vez comprada, no se puede volver atras",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Si',
            cancelButtonText: 'No'
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire({
                    icon: 'success',
                    title: 'Listo',
                    text: "Compra realizada",
                    showConfirmButton: false,
                    timer: 1500
                })
                setTimeout(() => { window.location = "/turismo/ofertables/buy.do?id="+id+"&soyPromo="+soyPromo}, 1600)                
            }
        })
    }
</script>
</head>
<body>

	<jsp:include page="/partials/navUsers.jsp"></jsp:include>
	<main class="container">

		<div class="bg-light p-4 mb-3 rounded" style="margin-top: 80px">
			<h1>Estas son las Ofertas de la Tierra Media</h1>
		</div>


<div class="container">
		<c:if test="${success != null}">
			<div class="alert alert-success">
				<p>
					<c:out value="${success}" />
				</p>
			</div>
		</c:if>
		<c:if test="${flash != null}">
			<div class="alert alert-danger">
				<p>
					<c:out value="${flash}" />
					<c:if test="${errors != null}">
						<ul>
							<c:forEach items="${errors}" var="entry">
								<li><c:out value="${entry.getValue()}"></c:out></li>
							</c:forEach>
						</ul>
					</c:if>
				</p>
			</div>
		</c:if>
</div>


		<div class="container " id="ofertable" style="padding-top: 50px">
			<div class="contenedor-curso active">
				<div class="contenedor-tarjetas">


					<c:forEach items="${ofertables}" var="ofertable">
						<div class="tarjeta-curso">
							<img src="${ofertable.image}" alt="foto" class="tarjeta-img">
							<div class="tarjeta-texto">
								<h3 class="tarjeta__titulo">
									<c:out value="${ofertable.name}"></c:out>
								</h3>
								<hr>
								<span class="tarjeta__p" style="margin-right: 20px;"> <i
									class="far fa-money-bill-alt"></i> Costo:$ <fmt:formatNumber
										type="number" maxFractionDigits="2" value="${ofertable.cost}" />
								</span> <span class="tarjeta__p"> <i class="far fa-clock"></i>
									Duración: <c:out value="${ofertable.duration}"></c:out>
								</span>
								<hr>
								<p class="tarjeta__p">
									<c:out value="${ofertable.description}"></c:out>
								</p>

								<hr>

								<c:choose>
									<c:when
										test="${user.canBuy(ofertable) && user.canAfford(ofertable) && user.canAttend(ofertable) && ofertable.canHost(1)}">

										<!--									
									<a
										href="/turismo/ofertables/buy.do?id=${ofertable.id}&soyPromo=${ofertable.soyPromocion()}"
										class="btn btn-success rounded" role="button">Comprar</a>
									
-->
										<a
											onclick="confirmarCompra(${ofertable.id},${ofertable.soyPromocion()})"
											class="btn btn-success rounded" role="button">Comprar</a>


									</c:when>
									<c:otherwise>
										<a href="#" class="btn btn-secondary rounded disabled"
											role="button">No se puede comprar</a>
									</c:otherwise>
								</c:choose>
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