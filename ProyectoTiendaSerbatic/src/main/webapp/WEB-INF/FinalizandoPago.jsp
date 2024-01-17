<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="models.ArticuloModel"%>
<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="http://code.jquery.com/jquery-2.0.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<meta charset="UTF-8">
<title>Tienda - Proceso de pago</title>

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

	<div class="container rounded bg-white">
		<div class="row d-flex justify-content-center pb-5">
			<div class="col-sm-5 col-md-5 ml-1">
				<div class="py-4 d-flex flex-row">
					<h5>
						<span class="fa fa-check-square-o"></span><b>Tienda</b> |
					</h5>
					<span class="pl-2">Pay</span>
				</div>

				<h4>Proceso de pago</h4>
				<div class="d-flex pt-2">
					<div>
						<p>
							<b>Listado de productos.</b>
						</p>
					</div>

				</div>

				<table class="table">
					<thead>
						<tr>
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
						for (Map.Entry<Integer, ArticuloModel> entry : carrito.entrySet()) {
						%>
						<%
						ArticuloModel articulo = entry.getValue();
						%>
						<tr>

							<td><strong><%=articulo.getNombre()%></strong>
								<p>Categoría</p></td>
							<td><p class="text-center"><%=articulo.getCantidad()%></p></td>

							<td><%=articulo.getPrecio()%> &euro;</td>
							<td><%=articulo.getPrecio()%> &euro;</td>
						</tr>

						<%
						totalPedido += (articulo.getPrecio() * articulo.getCantidad());
						%>
						<%
						}
						%>

					</tbody>
				</table>
				<hr>
				<div class="pt-2">
					<div class="d-flex">
						<div>
							<p>
								<b>Selección de método de pago.</b>
							</p>
						</div>
						<div class="ml-auto p-2">
							<p class="text-primary">
								<i class="fa fa-plus-circle text-primary"></i>Add payment card
							</p>
						</div>
					</div>
					<p></p>
					<form action="<%=request.getContextPath() %>/ProcesarPago" class="pb-3" method="get">
						<div class="d-flex flex-row align-content-center">
							<div class="pt-2 pr-2">
								<input type="radio" name="pagos" id="pago1" value="1" checked>
							</div>
							<div class="rounded d-flex w-100 px-2">
								<div class="pt-2">
									<p>
										<i class="fa fa-cc-visa text-primary pr-2"></i>&emsp; Visa -
										Tarjeta de crédito
									</p>
								</div>
								<div class="ml-auto pt-2">************3456</div>
							</div>
						</div>

						<div class="d-flex flex-row w-100">
							<div class="pt-2 pr-2">
								<input type="radio" name="pagos" id="pago2" value="2">
							</div>
							<div class="rounded d-flex w-100 px-2">
								<div class="pt-2">
									<p>
										<i class="fa fa-cc-paypal pr-2"></i> &emsp;Paypal
									</p>
								</div>
								<div class="ml-auto pt-2">vve******@****.***</div>
							</div>
						</div>
						<div>
							<button type="submit" class="btn-outline-primary">Proceder
								al pago</button>
						</div>
					</form>

				</div>
			</div>
			<div class="col-sm-3 col-md-4 offset-md-1 mobile">
				<div class="py-4 d-flex justify-content-end">
					<h6>
						<a href="/redirIndexCarritoController">Cancelar y volver a la
							web</a>
					</h6>
				</div>
				<div class="bg-light rounded d-flex flex-column">
					<div class="p-2 ml-3">
						<h4>Resumen de pedido</h4>
					</div>
					<div class="p-2 d-flex">
						<div class="col-8">Precio total productos</div>
						<div class="ml-auto">186.76&euro;</div>
					</div>

					<div class="border-top px-4 mx-3"></div>

					<div class="p-2 d-flex">
						<div class="col-8">
							Gastos de envío <span
								class="fa fa-question-circle text-secondary"></span>
						</div>
						<div class="ml-auto">
							<b>0.00&euro;</b>
						</div>
					</div>
					<div class="border-top px-4 mx-3"></div>
					<div class="p-2 d-flex pt-3">
						<div class="col-8">
							<b>Total</b>
						</div>
						<div class="ml-auto">
							<b class="green"><%=totalPedido%>&euro;</b>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

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