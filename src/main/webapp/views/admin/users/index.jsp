<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.19/dist/sweetalert2.all.min.js" defer></script>

<script defer>
    function confirmarEliminar(id) {
        Swal.fire({
            title: '¿Desea eliminar el usuario?',
            text: "Una vez borrado, no se puede recuperar",
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
                    title: 'Eliminado',
                    text: "El usuario fue eliminado",
                    showConfirmButton: false,
                    timer: 1500
                })
                setTimeout(() => { window.location = "/turismo/admin/users/delete.admin?id=" + id }, 1600)
            }
        })
    }
</script>

</head>
<body>

	<jsp:include page="/partials/nav.jsp"></jsp:include>

	<main class="container">

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

		<div class="bg-light p-4 mb-3 rounded" style="margin-top: 80px">
			<h1>Usuarios</h1>
		</div>

		<c:if test="${user.isAdmin()}">
			<div class="mb-3">
				<a href="/turismo/admin/users/create.admin" class="btn btn-primary"
					role="button"> <i class="bi bi-plus-lg"></i> Nuevo Usuario
				</a>
			</div>
		</c:if>
		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Monedas</th>
					<th>Tiempo</th>
					<th>Rol</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="tmp_user">
					<tr>
						<td><strong><c:out value="${tmp_user.username}"></c:out></strong></td>
						<td><c:out value="${tmp_user.coins}"></c:out></td>
						<td><c:out value="${tmp_user.time}"></c:out></td>
						<td>
							<c:choose>
								<c:when test="${tmp_user.admin}">
									Admin
								</c:when>
								<c:otherwise>
									Normal
								</c:otherwise>
							</c:choose>						
						</td>
						<td><c:if test="${user.admin && (!tmp_user.admin || tmp_user.id == user.id)}">
								<a href="/turismo/admin/users/edit.admin?id=${tmp_user.id}"
									class="btn btn-light rounded-0" role="button"><i
									class="bi bi-pencil-fill"></i></a>
									
								<a 	onclick="confirmarEliminar(${tmp_user.id})"
									class="btn btn-danger rounded" role="button"><i
									class="bi bi-x-circle-fill"></i></a>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</main>

</body>
</html>