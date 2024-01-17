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
	List<ArticuloModel> catalogo = (List<ArticuloModel>) request.getAttribute("catalogo");
	%>
	<!-- Header -->
	<%@  include file="../fragments/Header2.jsp"%>

	<!-- Banner-->
	<header class="bg-dark py-1">
		<div class="container px-4 px-lg-5 my-5">
			<div class="text-center text-white">
				<h1 class="display-4 fw-bolder">Bienvenido a nuestra tienda</h1>
				<p class="lead fw-normal text-white-50 mb-0">- Los mejores
					productos cercanos -</p>
			</div>
		</div>
	</header>

	<%-- <a href="ProbandoModal.jsp">Enlace</a>

	<button class="btn btn-outline-dark mt-auto" data-toggle="modal"
		data-target="#exampleModalCenter">Mostrar modal</button>--%--%>


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

	<form action="./" method="get">
		<div class="container mt-4 text-lg-center">
			<p class="text-lg-center">Filtros:&emsp;</p>
			<%@include file="../fragments/FiltrosCatalogo.jsp"%>
		</div>
	</form>
	<!-- Products -->
	<section class="py-5">
		<div class="container px-4 px-lg-5 mt-5">
			<div
				class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

				<%
				for (ArticuloModel articulo : catalogo) {
				%>
				<%
				if (articulo.getStock() > 0 && articulo.isBaja() == false) {
				%>
				<div class="col mb-5">
					<div class="card h-100 text-center">
						<%
						if (articulo.getPrecio() < 150) {
						%>
						<!-- Sale badge-->
						<div class="badge bg-dark text-white position-absolute"
							style="top: 0.5rem; right: 0.5rem;">Oferta</div>
						<%
						}
						%>
						<!-- Product image-->
						<a class="img-fluid w-100 h-100 "
							href="DetalleProducto?articuloId=<%=articulo.getId()%>"> <img
							class="card-img-top " name="imagen"
							style="width: auto; max-height: 255px;"
							src="<%=articulo.getImagen()%>" alt="..." />
						</a>
						<!-- Product details-->
						<div class="card-body p-4">
							<div class="text-center">
								<!-- Product name-->
								<h5 class="fw-bolder ">
									<a class="nav-link"
										href="DetalleProducto?articuloId=<%=articulo.getId()%>"><%=articulo.getNombre()%></a>
								</h5>
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
									name="precio"><%=articulo.getPrecio()%> &euro;</span>
							</div>
						</div>
						<!-- Product actions-->
						<form action="carrito" method="post">
							<input type="hidden" name="id" value="<%=articulo.getId()%>">
							<input type="hidden" name="nombre"
								value="<%=articulo.getNombre()%>"> <input type="hidden"
								name="descripcion" value="<%=articulo.getDescripcion()%>">
								<input type="hidden"
								name="categoria" value="<%=articulo.getCategoriaModel()%>">
							<input type="hidden" name="precio"
								value="<%=articulo.getPrecio()%>"> <input type="hidden"
								name="imagen" value="<%=articulo.getImagen()%>"> <input
								type="hidden" name="stock" value="<%=articulo.getStock()%>">
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
				<%
				}
				%>
				<%
				}
				%>
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
