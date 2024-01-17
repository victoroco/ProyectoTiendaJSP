<%@ page import="java.util.List"%>
<%@ page import="models.LineaPedidoModel"%>
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

	<!-- Header -->
	<%@ include file="../fragments/Header2.jsp"%>

	<div class="justify-content-center">
		<p class="text-xl-center  m-5">
					Pedido:
			<%=request.getAttribute("idPedido")%></p>
	</div>
	<div class="container mt-5">
		<div class="d-flex justify-content-center row">

			<div class="col-md-10">
				<div class="rounded">
					<div class="table-responsive table-borderless">

						<table class="table table-hover table-striped text-center">
							<thead>
								<tr>
									<th class="text-center">
										<div class="toggle-btn">
											<div class="inner-circle"></div>
										</div>
									</th>
									<th>Nombre producto</th>
									<th>Precio Unidad</th>
									<th>Cantidad</th>
									<th>Impuesto</th>
									<th>Precio envío</th>
									<th>Total</th>
									<th></th>
								</tr>
							</thead>
							<tbody class="table-body ">
								<%
								List<LineaPedidoModel> lineasPedido = (List<LineaPedidoModel>) request.getAttribute("lineasPedido");
								%>
								<%
								if (lineasPedido.size() > 0 && lineasPedido != null) {
								%>
								<%
								for (LineaPedidoModel lineaPedido : lineasPedido) {
								%>
								<%
								ArticuloModel articuloModel = (ArticuloModel) lineaPedido.getArticulo();
								%>
								<tr class="cell-1 ">
									<td class="text-center">
										<div class="toggle-btn">
											<div class="inner-circle"></div>
										</div>
									</td>
									<td><%=articuloModel.getNombre()%></td>
									<td><%=lineaPedido.getPreciounidad()%></td>
									<td><%=lineaPedido.getUnidades()%></td>
									<td><%=lineaPedido.getImpuesto()%></td>
									<td><%=lineaPedido.getPrecioEnvio()%></td>
									<td><%=lineaPedido.getTotal()%></td>
									<td><i class="fa fa-ellipsis-h text-black-50"></i></td>
								</tr>

								<%
								}
								%>
								<%
								} else {
								%>
								<tr class="cell-1">
									<td colspan="6" class="text-center">No existen lineas de
										pedido</td>
								</tr>
								<%
								}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

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