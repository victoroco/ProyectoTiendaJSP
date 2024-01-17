<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="models.ArticuloModel"%>
<%@ page import="models.UserModel"%>
<%@ page import="models.CategoriaModel"%>

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
	CategoriaModel categoriaModel = articulo.getCategoriaModel();
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
	<a class="btn btn-warning pt-2 m-3" href="./">Volver a inicio</a>
	<!-- content -->
	<section class="py-2">
		<div class="container">
			<div class="row gx-5">
				<aside class="col-lg-6">
					<div class="border rounded-4 mb-3 d-flex justify-content-center">
						<a data-fslightbox="mygalley" class="rounded-4" target="_blank"
							data-type="image"> <img
							style="max-width: 100%; max-height: 100vh; margin: auto;"
							class="rounded-4 fit" src="<%=articulo.getImagen()%>" />
						</a>
					</div>
					<%-- <div class="d-flex justify-content-center mb-3">
          <a data-fslightbox="mygalley" class="border mx-1 rounded-2" target="_blank" data-type="image" href="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big1.webp" class="item-thumb">
            <img width="60" height="60" class="rounded-2" src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big1.webp" />
          </a>
          <a data-fslightbox="mygalley" class="border mx-1 rounded-2" target="_blank" data-type="image" href="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big2.webp" class="item-thumb">
            <img width="60" height="60" class="rounded-2" src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big2.webp" />
          </a>
          <a data-fslightbox="mygalley" class="border mx-1 rounded-2" target="_blank" data-type="image" href="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big3.webp" class="item-thumb">
            <img width="60" height="60" class="rounded-2" src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big3.webp" />
          </a>
          <a data-fslightbox="mygalley" class="border mx-1 rounded-2" target="_blank" data-type="image" href="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big4.webp" class="item-thumb">
            <img width="60" height="60" class="rounded-2" src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big4.webp" />
          </a>
          <a data-fslightbox="mygalley" class="border mx-1 rounded-2" target="_blank" data-type="image" href="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big.webp" class="item-thumb">
            <img width="60" height="60" class="rounded-2" src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big.webp" />
          </a>
        </div>--%>
					<!-- thumbs-wrap.// -->
					<!-- gallery-wrap .end// -->
				</aside>
				<main class="col-lg-6">
					<div class="ps-lg-3">
						<h4 class="title text-dark">
							<%=articulo.getNombre()%>
						</h4>
						<div class="d-flex flex-row my-3">
							<div class="text-warning mb-1 me-2">
								<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fas fa-star-half-alt"></i> <span class="ms-1"> <%-- Valoraciones --%></span>
							</div>

							<%
							if (articulo.getStock() > 0) {
							%>
							<%
							if (articulo.getStock() < 10) {
							%>
							<span class="text-danger">Últimas unidades: <%=articulo.getStock()%>
								<i class="bi bi-exclamation-lg"></i></span>
							<%
							}
							%>
							<span class="text-success ms-2">- En stock</span>
							<%
							} else {
							%>
							<span class="text-danger ms-2">Sin stock</span>

							<%
							}
							%>
						</div>

						<div class="mb-3">
							<span class="h5"><%=articulo.getPrecio()%> &euro;</span> <span
								class="text-muted"></span>
						</div>

						<p><%=articulo.getDescripcion()%>
						</p>

						<div class="row">
							<dt class="col-3">Categoría:</dt>
							<dd class="col-9"><%=categoriaModel.getCategoria()%></dd>
							
						</div>

						<hr />

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
				</main>
			</div>
		</div>
	</section>
	<!-- content -->



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
