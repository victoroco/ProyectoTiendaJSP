<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="models.ArticuloModel"%>
<%@ page import="models.UserModel"%>

<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>

<html lang="es">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Tienda - Productos cercanos</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />


<!-- Pruebas -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />

<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />

</head>
<body>
	<%
	String errorAddArticulo = (String) request.getAttribute("errorAddArticulo");
	ArticuloModel articulo = (ArticuloModel) request.getAttribute("articulo");
	%>
	<!-- Header -->
	<%@  include file="../fragments/Header2.jsp"%>

	<%
	if (errorAddArticulo != null) {
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
	<!-- Products -->
	<section class="py-5">
		<div class="container px-4 px-lg-5 mt-5 ">
			<div
				class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

				<div class="col mb-5">
					<div class="card h-100">
						<!-- Sale badge-->
						<div class="badge bg-dark text-white position-absolute"
							style="top: 0.5rem; right: 0.5rem">Oferta</div>
						<!-- Product image-->
						<img class="card-img-top h-50" name="imagen"
							src="<%=articulo.getImagen()%>" alt="..." />
						<!-- Product details-->
						<div class="card-body p-4">
							<div class="text-center">
								<!-- Product name-->
								<h5 class="fw-bolder "><%=articulo.getNombre()%></h5>
								<!-- Product reviews-->
								<div
									class="d-flex justify-content-center small text-warning mb-2">
									<%
									if (articulo.getStock() <= 5) {
									%>
									<div class="text-danger">
										Últimas unidades:
										<%=articulo.getStock()%>
										<i class="bi bi-exclamation-lg"></i>
									</div>
									<%
									} else {
									%>
									<div class="text-danger"></div>
									<%
									}
									%>

									<!--  
									<div class="bi-star-fill"></div>
									<div class="bi-star-fill"></div>
									<div class="bi-star-fill"></div>
									<div class="bi-star-fill"></div>
									<div class="bi-star-fill"></div> 
									-->
								</div>
								<!-- Product price-->
								<span class="text-muted text-decoration-line-through"></span> <span
									name="precio"><%=articulo.getPrecio()%>	&euro;</span>
							</div>
						</div>
						<!-- Product actions-->
						<form action="DetalleProductoAdd" method="post">
							<input type="hidden" name="id" value="<%=articulo.getId()%>">
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<button class="btn btn-outline-dark mt-auto">
										Añadir <i class="bi bi-cart-plus-fill"></i>
									</button>
								</div>
							</div>
						</form>

					</div>
				</div>

			</div>
		</div>
	</section>

	<!-- Footer-->
	<%@ include file="../fragments/Footer.jsp"%>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="../jquery-3.6.4.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
	<!-- Bootstrap core JS-->
	<!--  <script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>-->
</body>
</html>
