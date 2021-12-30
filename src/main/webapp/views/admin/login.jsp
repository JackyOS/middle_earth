<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="/turismo/assets/stylesheets/styleLoginAdmin.css">
<script src="/turismo/assets/js/loginAdmin.js" defer></script>

<jsp:include page="/partials/head.jsp"></jsp:include>
</head>


<body>
	<main>
		<div class="bg-img">
			<div class="content">
				<header>Turismo en la Tierra Media</header>
					
					<h2 style="color:#fff">Administradores</h2>
	
				<c:if test="${flash != null}">
					<div class="alert alert-danger">
						<p>
							<c:out value="${flash}" />
						</p>
					</div>
				</c:if>

				<c:if test="${success != null}">
					<div class="alert alert-success">
						<p>
							<c:out value="${success}" />
						</p>
					</div>
				</c:if>

				<form action="login" method="post">
					<div class="field">
						<span class="fa fa-user"></span> <input class="form-control"
							name="username" placeholder="Usuario">
					</div>


					<div class="field space">
						<span class="fa fa-lock"></span> <input type="password"
							class="pass-key" placeholder="password" name="password">
						<span class="show">SHOW</span>
					</div>


					<div class="pass"></div>
					<div class="field">
						<input type="submit" value="LOGIN">
					</div>


				</form>





			</div>
		</div>

	</main>
</body>
</html>