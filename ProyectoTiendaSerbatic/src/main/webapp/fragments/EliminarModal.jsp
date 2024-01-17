<!-- Modal -->
<div class="modal fade " id="exampleModalCenter" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalCenterTitle"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLongTitle">Eliminar
					producto del carrito</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">¿ Está seguro que desea eliminar el
				producto?</div>
			<div class="modal-footer">

				<button type="button" class="btn btn-secondary" data-dismiss="modal"
					onclick="javascript:assign();">Cancelar</button>
				<form class="modal-form" action="carritoeditarmodprod" method="post">
					<label hidden="hidden" for="recipient-name" class="col-form-label"></label>
					<input type="text" name="idProdEliminar" id="recipient-name">
					<button type="submit" class="btn btn-secondary">Aceptar</button>
				</form>
			</div>
		</div>
	</div>
</div>