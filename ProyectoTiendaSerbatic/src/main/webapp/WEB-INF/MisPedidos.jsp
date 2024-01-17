<%@ page import="java.util.List"%>
<%@ page import="models.PedidoModel"%>

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

<!-- DateTime picker -->

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

	<div class="container mt-5">
		<div class="d-flex justify-content-center row">
			<div class="container mt-4 text-lg-center">
				<form class="form-" action="">
					<div class="form-row">
						<div class="col-md-3">
							<input name="fechaInicio" id="fechaInicio"
								class="form-control col-md-6 " type="date"
								onfocus="(this.type='date')" />
						</div>
						<div class="col-md-3">
							<input name="fechaFin" id="fechaFin"
								class="form-control col-md-6 " type="date" />
						</div>
						<div class="col">
							<input class="form-control col-md-2 " type="submit"
								value="Filtrar" />
						</div>
					</div>
				</form>
			</div>
			<div class="col-md-10">
				<div class="rounded">
					<div class="table-responsive table-borderless">

						<table class="table table-hover table-striped text-center">
							<thead>
								<tr>
									<th class="text-center"></th>
									<th>Pedido</th>
									<th>Metodo de pago</th>
									<th>Estado</th>
									<th>Total</th>
									<th>Fecha de compra</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<%
								List<PedidoModel> listadoPedidos = (List<PedidoModel>) request.getAttribute("listadoPedidos");
								%>
								<%
								if (listadoPedidos.size() > 0 && listadoPedidos != null) {
								%>
								<%
								for (PedidoModel pedido : listadoPedidos) {
								%>
								<tr>
									<td class="text-center">
										<form class="form-inline" action="DetallePedido" method="get">
											<input type="hidden" name="idPedido"
												value="<%=pedido.getId()%>">
											<button type="submit" class="btn btn-info">
												<i class="bi-eye-fill"></i>
											</button>
										</form>
									</td>
									<td><%=pedido.getId()%></td>
									<td><%=pedido.getMetodoPagoNombre()%></td>
									<td><span class="badge badge-info">En proceso</span></td>
									<td><%=pedido.getTotal()%></td>
									<td><%=pedido.getFecha()%></td>
									<td><i class="fa fa-ellipsis-h text-black-50"></i></td>
								</tr>

								<%
								}
								%>
								<%
								} else {
								%>
								<tr class="cell-1">
									<td colspan="6" class="text-center">No hay ningún pedido</td>
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

	<script type="text/javascript">
		
	</script>

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
	<!-- DateTime picker -->


</body>
</html>