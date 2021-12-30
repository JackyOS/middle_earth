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
			<h1>¡Bienvenido, <c:out value="${user.username}"></c:out>!</h1>
		</div>
		

		<h4>Cantidad de atracciones: <c:out value="${attractions.size()}"></c:out> </h4>
		
		<h4>Cantidad de promociones: <c:out value="${promotions.size()}"></c:out> </h4>
		
		<h4>Cantidad de usuarios: <c:out value="${users.size()}"></c:out> </h4>
	


	</main>

</body>
</html>