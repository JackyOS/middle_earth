<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>

<script
	src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.19/dist/sweetalert2.all.min.js"
	defer></script>

<script defer>
    function confirmarEliminar(id) {
        Swal.fire({
            title: '¿Desea eliminar la promoción?',
            text: "Una vez borrada, no se puede recuperar",
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
                    text: "La promoción fue eliminada",
                    showConfirmButton: false,
                    timer: 1500
                })
                setTimeout(() => { window.location = "/turismo/admin/promotions/delete.admin?id=" + id }, 1600)
            }
        })
    }
</script>


</head>
<body>

	<jsp:include page="/partials/nav.jsp"></jsp:include>

	<main class="container">

		<div class="bg-light p-4 mb-3 rounded" style="margin-top: 80px">
			<h1>Estas son las Promociones de la Tierra Media</h1>
		</div>

		<div style="display: flex; flex-wrap: wrap; justify-content:space-around;">

			<div class="mb-3" >
				<a href="/turismo/admin/promotions/createAbsoluta.admin"
					class="btn btn-primary" role="button"> <i class="bi bi-plus-lg"></i>
					Nueva Promoción Absoluta
				</a>
			</div>

			<div class="mb-3">
				<a href="/turismo/admin/promotions/createPorcentual.admin"
					class="btn btn-primary" role="button"> <i class="bi bi-plus-lg"></i>
					Nueva Promoción Porcentual
				</a>
			</div>

			<div class="mb-3">
				<a href="/turismo/admin/promotions/createAxB.admin"
					class="btn btn-primary" role="button"> <i class="bi bi-plus-lg"></i>
					Nueva Promoción AxB
				</a>
			</div>
			
		</div>

		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<th>Promoción</th>
					<th>Costo</th>
					<th>Duración</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${promotions}" var="promotion">
					<tr>
						<td><strong><c:out value="${promotion.name}"></c:out>

						</strong>
							<p>
								<c:out value="${promotion.names}"></c:out>

							</p>
							</p></td>
						<td><c:out value="${promotion.cost}"></c:out></td>
						<td><c:out value="${promotion.duration}"></c:out></td>

						<td><a
							href="/turismo/admin/promotions/edit${promotion.type}.admin?id=${promotion.id}"
							class="btn btn-light rounded-0" role="button"><i
								class="bi bi-pencil-fill"></i></a> <a
							onclick="confirmarEliminar(${promotion.id})"
							class="btn btn-danger rounded" role="button"><i
								class="bi bi-x-circle-fill"></i></a></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>

	</main>

</body>
</html>