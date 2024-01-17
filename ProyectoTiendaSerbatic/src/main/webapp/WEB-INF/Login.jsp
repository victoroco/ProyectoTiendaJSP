<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<html lang="es">

<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Tienda - Login</title>


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

<!-- font awesome  -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">


<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
</head>

<body>

	<%
	if (request.getAttribute("exitoRegistro") != null) {
	%>
	<%@include file="../fragments/RegistroExitoModal.jsp"%>

	<script type="text/javascript">
		function redireccionar() {
			$('#exampleModalRegistro').modal('show');
		}
		setTimeout("redireccionar()", 100); //tiempo expresado en milisegundos
	</script>

	<script>
		function assign() {
			$('#exampleModalRegistro').modal('hide');
		}
	</script>

	<%
	}
	%>



	<!-- Header -->
	<%@ include file="../fragments/Header2.jsp"%>

	<!-- LogIn -->
	<%@ include file="../fragments/LoginFragment.jsp"%>

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