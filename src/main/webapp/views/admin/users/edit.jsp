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

		<c:if test="${tmp_user != null && !tmp_user.isValid()}">
			<div class="alert alert-danger">
				<p>Se encontraron errores al actualizar el usuario.</p>
			</div>
		</c:if>

		<form action="/turismo/admin/users/edit.admin" method="post">
			<input type="hidden" name="id" value="${tmp_user.id}">
			<jsp:include page="/views/admin/users/form.jsp"></jsp:include>
		</form>
	</main>
</body>
</html>
