<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pedido completado</title>
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
	<!-- Header -->
	<%@ include file="../fragments/Header2.jsp"%>

	<section class="p-0 bg-img cover-background"
		style="background-image: url(https://bootdey.com/img/Content/bg1.jpg);">
		<div class="container-fluid d-flex flex-column">
			<div class="row align-items-center justify-content-center min-vh-100">
				<div class="col-md-9 col-lg-6 my-5">
					<div class="text-center">
						<h1 class="mb-0 text-secondary">
							<i class="bi bi-check-square-fill text-success"></i>
						</h1>
						<h2 class="mb-4 text-white">Pedido completado con éxito.</h2>
						<h2 class="mb-4 text-white">¡Gracias por confiar en nosotros!</h2>

						<p class="w-sm-80 mx-auto mb-4 text-white">En breve será
							redirigido a la página principal.</p>
						<div>
							<a href="./" class="btn btn-info btn-lg me-sm-2 mb-2 mb-sm-0">Página de inicio</a>
						</div>
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
</body>
</html>