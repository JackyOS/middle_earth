<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="modal-body" style="margin-top: 80px">
	<div class="mb-3">
		<label for="name" class="col-form-label">Nombre:</label> <input
			type="text" class="form-control" id="name" name="name" required
			value="${promotion.name}">
	</div>

	<div class="mb-3" id="select">
		<label for="type" class="col-form-label">Tipo: </label><input
			type="text" class="form-control" id="type" name="type" required
			value="Porcentual" disabled="disabled">
	</div>


	<div class="mb-3">
		<label for="discount"
			class='col-form-label ${promotion.errors.get("discount") != null ? "is-invalid" : "" }'>Descuento:</label>
		<input class="form-control" type="number" id="discount" name="discount"
			required value="${promotion.discount}"
			min=0.1 max=0.99 step=0.01
			></input>	
			
		<div class="invalid-feedback">
			<c:out value='${promotion.errors.get("discount")}'></c:out>
		</div>
	</div>


	<div class="mb-3">
		<div class="multiple-selection show">
			<label for="" class="col-form-label">Lista de atracciones:</label>
			<div class="checkbox-dropdown">
				<c:forEach items="${attractions}" var="attraction">
					<input type="checkbox" name="attractions"
						value="${attraction.name}" 
						${promotion.included(attraction) ? "checked" : ""}
						>${attraction.name} <br>
			</c:forEach>
			</div>
		</div>
	</div>


	<div class="mb-3">
		<label for="description" class="col-form-label">Descripción:</label> <input
			type="text" class="form-control" id="description" name="description" required
			value="${promotion.description}">
	</div>

	<div class="mb-3">
		<label for="image" class="col-form-label">Imagen:</label> <input
			type="text" class="form-control" id="image" name="image" required
			value="${promotion.image}">
	</div>
	
</div>

<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>