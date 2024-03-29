<%@ page import="models.UserSessionModel"%>
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
<title></title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />

</head>
<body>
	<!-- Header -->
	<nav
		class="navbar navbar-expand-lg sticky-top navbar-light bg-light border-bottom border-dark border-2">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="./">Tienda</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="./">Inicio</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">Tienda</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="#!">Todos los
									productos</a></li>
							<li><hr class="dropdown-divider" /></li>
							<li><a class="dropdown-item" href="#!">Productos
									populares</a></li>
							<li><a class="dropdown-item" href="#!">Nuevos productos</a></li>
						</ul></li>
					<%

					%>
					<%
					if (request.getSession().getAttribute("sesionUsuario") == null) {
					%>
					<li class="nav-item"><a class="nav-link" href="Login1">Iniciar
							sesión</a></li>
					<%
					} else {
					%>
					<%
					UserSessionModel userSession = (UserSessionModel) request.getSession().getAttribute("sesionUsuario");
					%>
					<%
					UserModel userModel = userSession.getUsuario();
					%>

					<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
							role="button" data-bs-toggle="dropdown" aria-expanded="false">Bienvenido
								<%=userModel.getNombre()%></a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="#!">Mis datos</a></li>
								<li><a class="dropdown-item" href="#!">Ajustes de
										cuenta</a></li>
								<li><hr class="dropdown-divider" /></li>
								<li><a class="dropdown-item" href="finalizarloginstatus">Cerrar
										sesión</a></li>
							</ul></li>
					</ul>
					<%--@ include file="MenuLoginFragment.jsp"--%>
					<%
					}
					%>
				</ul>

				<form action="redirIndexCarritoController" class="d-flex"
					method="post">
					<button class="btn btn-outline-dark" type="submit">
						<i class="bi-cart-fill me-1"></i> Carrito <span
							class="badge bg-dark text-white ms-1 rounded-pill"><%=request.getSession().getAttribute("cantidadTotalCarrito")%></span>
					</button>
				</form>
			</div>
		</div>
	</nav>
</body>
</html>