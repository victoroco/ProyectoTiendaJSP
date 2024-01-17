<%@ page import="models.UserSessionModel"%>

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

	<%
	UserModel userModelCambios = (UserModel) request.getAttribute("datosCambiados");
	%>
	<%
	UserSessionModel userSession = (UserSessionModel) request.getSession().getAttribute("sesionUsuario");
	%>
	<%
	UserModel userModel = userSession.getUsuario();
	%>

	<div class="container rounded bg-white mt-5 mb-5">
		<div class="row">
			<div class="col-md-3 border-right">
				<div
					class="d-flex flex-column align-items-center text-center p-3 py-5">
					<img class="rounded-circle mt-5" width="150px"
						src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span
						class="font-weight-bold"><h1>Tu perfil</h1></span><span
						class="text-black-50"></span><span> </span>
				</div>
			</div>
			<div class="col-md-5 border-right">
				<div class="p-3 py-5">
					<div class="d-flex justify-content-between align-items-center mb-3">
						<h4 class="text-right">Modificar tus datos</h4>
					</div>

					<form action="MisDatos" method="post">
						<div class="row mt-2">
							<div class="col-md-6">
								<label class="labels">&nbsp;* Nombre</label><input type="text"
									class="form-control" name="nombre" placeholder="Nombre"
									value="<%=userModel.getNombre()%>" required>
							</div>
							<div class="col-md-6">
								<label class="labels">&nbsp;* Apellidos</label><input
									type="text" class="form-control" name="apellidos"
									value="<%=userModel.getApellidos()%>" placeholder="Apellidos"
									required>
							</div>
						</div>
						<div class="row mt-3">
							<div class="col-md-12">
								<label class="labels">&nbsp;* Teléfono</label><input type="text"
									class="form-control" name="telefono" min="1" step="1" placeholder="Teléfono"
									value="<%=userModel.getTelefono()%>" required>
							</div>
							<div class="col-md-12">
								<label class="labels">&nbsp;* Dirección</label><input
									type="text" class="form-control" name="direccion"
									placeholder="Dirección" value="<%=userModel.getDireccion()%> "
									required>
							</div>

							<div class="col-md-6">
								<label class="form-label" for="form3Prov">&nbsp;*
									Provincia </label>
								<!-- Provincias -->
								<input type="text" name="provincia" id="form3Prov"
									class="form-control form-control-lg" placeholder="Tu provincia"
									value="<%=userModel != null ? userModel.getProvincia() : ""%>"
									required />

							</div>
							<div class="col-md-6">
								<label class="form-label" for="form3Ciud">&nbsp;*
									Localidad </label>
								<!-- Ciudades -->
								<input type="text" name="ciudad" id="form3Ciud"
									class="form-control form-control-lg" placeholder="Tu ciudad"
									value="<%=userModel != null ? userModel.getCiudad() : ""%>"
									required />
							</div>

							<div class="col-md-12">
								<label class="labels">&nbsp;* Dni</label><input type="text"
									class="form-control" name="dni" placeholder="Dni"
									value="<%=userModel.getDni()%>" required>
							</div>
						</div>

						<%
						if (request.getAttribute("errRegistVacio") != null) {
						%>
						<div class="row">
							<label class="form-label text-right text-danger p-3"><%=request.getAttribute("errRegistVacio")%></label>
						</div>
						<%
						}
						%>

						<div class="mt-5 text-center">
							<button class="btn btn-primary profile-button" type="submit">Save
								Profile</button>
						</div>
					</form>
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