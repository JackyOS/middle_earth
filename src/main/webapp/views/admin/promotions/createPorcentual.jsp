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

		<c:if test="${promotion != null && !promotion.isValid()}">
			<div class="alert alert-danger">
				<p>Se encontraron errores al crear la Promoción.</p>
			</div>
		</c:if>

		<form action="/turismo/admin/promotions/createPorcentual.admin" method="post">
			<jsp:include page="/views/admin/promotions/formPorcentual.jsp"></jsp:include>
		</form>
	</main>
</body>
</html>