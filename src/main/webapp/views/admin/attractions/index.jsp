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
            title: '¿Desea eliminar la atracción?',
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
                    text: "La atracción fue eliminada",
                    showConfirmButton: false,
                    timer: 1500
                })
                setTimeout(() => { window.location = "/turismo/admin/attractions/delete.admin?id=" + id }, 1600)
            }
        })
    }
</script>
</head>
<body>

	<jsp:include page="/partials/nav.jsp"></jsp:include>

	<main class="container">



		<div class="bg-light p-4 mb-3 rounded" style="margin-top: 80px">
			<h1>Estas son las atracciones de la Tierra Media</h1>
		</div>

		<div class="mb-3">
			<a href="/turismo/admin/attractions/create.admin"
				class="btn btn-primary" role="button"> <i class="bi bi-plus-lg"></i>
				Nueva Atracción
			</a>
		</div>

		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<th>Atracción</th>
					<th>Costo</th>
					<th>Duración</th>
					<th>Cupo</th>
					<th>Descripción</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${attractions}" var="attraction">
					<tr>
						<td><strong><c:out value="${attraction.name}"></c:out></strong></td>
						<td><c:out value="${attraction.cost}"></c:out></td>
						<td><c:out value="${attraction.duration}"></c:out></td>
						<td><c:out value="${attraction.capacity}"></c:out></td>
						<td><c:out value="${attraction.description}"></c:out></td>

						<td><a
							href="/turismo/admin/attractions/edit.admin?id=${attraction.id}"
							class="btn btn-light rounded-0" role="button"><i
								class="bi bi-pencil-fill"></i></a> <a
							onclick="confirmarEliminar(${attraction.id})"
							class="btn btn-danger rounded" role="button"><i
								class="bi bi-x-circle-fill"></i></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</main>

</body>
</html>