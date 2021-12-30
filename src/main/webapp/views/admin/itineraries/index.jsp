<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>



</head>
<body>

	<jsp:include page="/partials/nav.jsp"></jsp:include>

	<main class="container">


		<div class="bg-light p-4 mb-3 rounded" style="margin-top: 80px">
			<h1>Itinerarios de los usuarios</h1>
		</div>


		<form action="/turismo/admin/itineraries/index.admin">
			<label for="usuario" class='col-form-label'>Usuarios</label> <select
				class="form-select" aria-label="Default select example" id="usuario"
				name="usuario">
				<option selected>Usuarios</option>
				<c:forEach items="${users}" var="usuario">
					<option value="${usuario.username}">${usuario.username}</option>
				</c:forEach>
			</select>


			<div>
				<button type="submit" class="btn btn-primary">Seleccionar</button>
			</div>
			
		</form>
		
		<h2>Usuario: <c:out value="${userItinerary.username}"></c:out> </h2>
		
		<p>Dinero disponible: <c:out value="${userItinerary.coins}"></c:out> </p>
		
		<p>Tiempo disponible: <c:out value="${userItinerary.time}"></c:out> </p>
		
		
		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<th>Oferta</th>
					<th>Costo</th>
					<th>Duración</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${itineraries}" var="itinerary">
					<tr>
						<td><strong><c:out value="${itinerary.name}"></c:out></strong>
							<p>
								<c:out value="${itinerary.names}"></c:out>
							</p></td>
						<td><c:out value="${itinerary.cost}"></c:out></td>
						<td><c:out value="${itinerary.duration}"></c:out></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</main>

</body>
</html>