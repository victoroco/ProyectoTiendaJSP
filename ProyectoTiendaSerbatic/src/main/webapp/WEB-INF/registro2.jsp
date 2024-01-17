<%@ page import="models.UserModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

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

<link rel="stylesheet" type="text/css"
	href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
<script
	src="https://unpkg.com/bootstrap-show-password@1.2.1/dist/bootstrap-show-password.min.js"></script>

<!-- Core theme CSS (includes Bootstrap)-->
<link href="../css/styles.css" rel="stylesheet" />

</head>
<body>

	<!-- Header -->
	<%@ include file="../fragments/Header2.jsp"%>


	<section class="h-100 bg-dark">
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col">
					<div class="card card-registration my-4">
						<div class="row g-0">
							<div class="col-xl-6 d-none d-xl-block">
								<img
									src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/img4.webp"
									alt="Sample photo" class="img-fluid"
									style="border-top-left-radius: .25rem; border-bottom-left-radius: .25rem;" />
							</div>
							<div class="col-xl-6">
								<div class="card-body p-md-5 text-black">
									<h3 class="mb-5 text-uppercase font-weight-bold">Registrarse
										en Tienda</h3>

									<%
									UserModel userModel = (UserModel) request.getAttribute("datosRegistro");
									%>
									<form action="registrosubmit" method="post">

										<div class="row">
											<label class="form-label text-right" for="form3Example97">&nbsp;
												* campos requeridos.</label>
											<div class="col-md-6 mb-4">
												<div class="form-outline">
													<label class="form-label" for="form3Example1m">&nbsp;*
														Nombre </label> <input type="text" name="nombre"
														id="form3Example1m" class="form-control form-control-lg"
														placeholder="Tu nombre"
														value="<%=userModel != null ? userModel.getNombre() : ""%>"
														required />
												</div>
											</div>
											<div class="col-md-6 mb-4">
												<div class="form-outline">
													<label class="form-label" for="form3Example1n">&nbsp;*
														Dni</label> <input type="text" name="dni" id="form3Example1n"
														class="form-control form-control-lg" placeholder="Tu dni"
														value="<%=userModel != null ? userModel.getDni() : ""%>"
														required />
												</div>
											</div>
										</div>

										<div class="row">
											<div class="col-md-12 mb-4">
												<div class="form-outline">
													<label class="form-label" for="form3Example1m1">&nbsp;*
														Apellidos</label> <input type="text" name="apellidos"
														id="form3Example1m1" class="form-control form-control-lg"
														placeholder="Tus apellidos"
														value="<%=userModel != null ? userModel.getApellidos() : ""%>"
														required />
												</div>
											</div>
										</div>

										<div class="form-outline mb-4">
											<label class="form-label" for="form3Example8">&nbsp;*
												Dirección </label> <input type="text" name="direccion"
												id="form3Example8" class="form-control form-control-lg"
												placeholder="Tu dirección"
												value="<%=userModel != null ? userModel.getApellidos() : ""%>"
												required />
										</div>

										<div class="row">
											<div class="col-md-6 mb-4">
												<label class="form-label" for="form3Prov">&nbsp;*
													Provincia </label>
												<!-- Provincias -->
												<%@ include file="../fragments/Provincias.jsp"%>

											</div>
											<div class="col-md-6 mb-4">
												<label class="form-label" for="form3Ciud">&nbsp;*
													Localidad </label>
												<!-- Ciudades -->
												<%@ include file="../fragments/Ciudades.jsp"%>
											</div>
										</div>

										<div class="form-outline mb-4">
											<label class="form-label" for="form3Example9">&nbsp;*
												Teléfono</label> <input type="text" name="telefono"
												id="form3Example9" class="form-control form-control-lg"
												placeholder="Tu teléfono móvil"
												value="<%=userModel != null ? userModel.getTelefono() : ""%>"
												required />
										</div>

										<div class="form-outline mb-4">
											<label class="form-label" for="form3Example90">&nbsp;*
												Email</label> <input type="text" name="email" id="form3Example90"
												class="form-control form-control-lg" placeholder="Tu email"
												value="<%=userModel != null ? userModel.getEmail() : ""%>"
												required />
										</div>

										<div class="form-outline mb-4">
											<label class="form-label" for="form3Example99">&nbsp;*
												Contraseña</label> <input data-toggle="password" type="password"
												name="password" id="password"
												class="form-control form-control-lg"
												placeholder="Elige una contraseña" value="" required />
										</div>

										<div class="form-outline mb-4">
											<label class="form-label" for="form3Example97">&nbsp;*
												Repetir contraseña</label> <input data-toggle="password"
												type="password" name="repetirPassword" id="repetirPassword"
												class="form-control form-control-lg"
												placeholder="Repite la misma contraseña" value="" required />
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
										<%
										if (request.getAttribute("errRegistEmailExist") != null) {
										%>
										<div class="row">
											<label class="form-label text-right text-danger p-3"><%=request.getAttribute("errRegistEmailExist")%></label>
										</div>
										<%
										}
										%>
										<%
										if (request.getAttribute("errRegistPasswords") != null) {
										%>
										<div class="row">
											<label class="form-label text-right text-danger p-3"><%=request.getAttribute("errRegistPasswords")%></label>
										</div>
										<%
										}
										%>
										<div class="d-flex justify-content-end pt-3">
											<form action="">
												<button type="button" class="btn btn-dark btn-lg ms-3"
													onclick="history.back()">Volver</button>
											</form>
											<button type="button" class="btn btn-light btn-lg ms-2">Vaciar
												campos</button>
											<button type="submit" class="btn btn-warning btn-lg ms-2">Registrar</button>
										</div>
									</form>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>


	<!-- Footer-->
	<%@ include file="../fragments/Footer.jsp"%>


</body>
</html>