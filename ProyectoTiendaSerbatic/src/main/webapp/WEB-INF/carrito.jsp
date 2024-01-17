<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="models.ArticuloModel"%>

<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>

<html lang="es">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Tienda - Carrito</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />

<!-- Pruebas -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">

<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/carritoStyles.css" rel="stylesheet" />
<link href="css/styles.css" rel="stylesheet" />

</head>
<body>
	<%
	String errorAddArticulo = (String) request.getAttribute("errorAddArticulo");
	%>

	<%
	if (errorAddArticulo != null ) {
	%>
	<%@include file="../fragments/ErrorModal.jsp"%>

	<script type="text/javascript">
		function redireccionar() {
			$('#exampleModalCenter').modal('show');
		}
		setTimeout("redireccionar()", 100); //tiempo expresado en milisegundos
	</script>

	<script>
		function assign() {
			$('#exampleModalCenter').modal('hide');
		}
	</script>

	<%
	}
	%>
	<!-- Header -->
	<%@ include file="../fragments/Header2.jsp"%>

	<div class="container bootstrap snippets bootdey">
		<div class="col-md-12 col-sm-8 content">

			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-info ">
						<td colspan="6">&nbsp;</td>
						<div class="panel-heading">
							<h3>
								<img class="img-circle img-thumbnail"
									src="https://bootdey.com/img/Content/user_3.jpg"> Usuario
							</h3>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th>Imagen</th>
											<th>Nombre/Categoría</th>
											<th>Unidades</th>
											<th>Precio</th>
											<th>Total</th>
										</tr>
									</thead>
									<tbody>
										<%
										double totalPedido = 0;
										HashMap<Integer, ArticuloModel> carrito = (HashMap<Integer, ArticuloModel>) session.getAttribute("carrito");
										%>
										<%
										if (carrito.size() > 0 && carrito != null) {
										%>
										<%
										for (Map.Entry<Integer, ArticuloModel> entry : carrito.entrySet()) {
										%>
										<%
										ArticuloModel articulo = entry.getValue();
										%>
										<tr>
											<td><img src="<%=articulo.getImagen()%>"
												class="img-cart h-100"></td>
											<td><strong><%=articulo.getNombre()%></strong>
												<p>Categoría</p></td>
											<td>
												<form action="carritoeditarmodprod" method="get"
													class="form-inline">
													<input type="hidden" name="idProdModificar"
														value="<%=articulo.getId()%>">
													<%
													int stock = articulo.getStock();
													%>
													<input class="form-control w-50" name="cantidadProducto"
														type="number" min="1" max="<%=stock%>"
														value="<%=articulo.getCantidad()%>"
														onChange="()if(this.value>this.max)this.value=this.max;)(if(this.value<this.min)this.value=this.min;)">
													<button type="submit" rel="tooltip" class="btn btn-default">
														<i class="fa fa-pencil"></i>
													</button>
												</form>

												<form action="carritoeditarmodprod" method="post"
													class="form"
													onsubmit="return confirm('¿Estás seguro que deseas eliminar el producto?');">
													<input type="hidden" name="idProdEliminar"
														value="<%=articulo.getId()%>">
													<button type="submit" class="btn btn-primary">
														<i class="fa fa-trash-o"></i>
													</button>
												</form>
											</td>
											<td><%=articulo.getPrecio()%> &euro;</td>
											<td><%=articulo.getPrecio()%> &euro;</td>
										</tr>

										<%
										totalPedido += (articulo.getPrecio() * articulo.getCantidad());
										%>
										<%
										}
										%>
										<%
										} else {
										%>
										<tr>
											<td colspan="4" class="text-center">No hay ningún
												producto</td>
										</tr>
										<%
										}
										%>
										<tr>
											<td colspan="6">&nbsp;</td>
										</tr>
										<tr>
											<td colspan="4" class="text-right">Total productos</td>
											<td>86.00 &euro;</td>
										</tr>
										<tr>
											<td colspan="4" class="text-right">Total envío</td>
											<td>2.00 &euro;</td>
										</tr>
										<tr>
											<td colspan="4" class="text-right"><strong>Total</strong></td>
											<td><%=totalPedido%> &euro;</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<a href="./" class="btn btn-success"><span
						class="glyphicon glyphicon-arrow-left"></span>&nbsp;Continuar
						comprando</a> <a href="finalizarcarrito"
						class="btn btn-primary pull-right">Finalizar pedido<span
						class="glyphicon glyphicon-chevron-right"></span>
					</a>
				</div>
			</div>
		</div>
	</div>

	<td colspan="6">&nbsp;</td>

	<!-- Footer-->
	<%@ include file="../fragments/Footer.jsp"%>

	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="../jquery-3.6.4.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>

</body>
</html>