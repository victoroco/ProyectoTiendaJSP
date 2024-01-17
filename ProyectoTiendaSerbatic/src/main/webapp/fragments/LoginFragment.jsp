<div class="vh-100 d-flex justify-content-center align-items-center">
	<div class="container">
		<div class="row d-flex justify-content-center">
			<div class="col-12 col-md-8 col-lg-6">
				<div class="card bg-white">
					<div class="card-body p-5">
						<form class="mb-3 mt-md-4" action="Login" method="post">
							<h2 class="fw-bold mb-2 text-uppercase ">Iniciar sesión</h2>
							<p class=" mb-5">Introduce tu email y contraseña.</p>
							<div class="mb-3">

								<label for="email" class="form-label ">Email</label>

								<%
								if (request.getAttribute("mensajeErrorLogin") != null) {
								%>
								<span class="text-danger p-3"><%=request.getAttribute("mensajeErrorLogin")%></span>
								<%
								}
								%>

								<input type="email" class="form-control" name="email" id="email"
									placeholder="ejemplo@tuejemplo.com"
									value="<%=request.getAttribute("email") != null ? request.getAttribute("email") : ""%>"
									required>

							</div>
							<div class=" mb-3">
								<label for="password" class="form-label ">Contraseña</label>
								<div class="input-group mb-3" id="show_hide_password">
									<input data-toggle="password" type="password"
										class="input form-control" name="password" id="password"
										placeholder="************"
										value="<%=request.getAttribute("email") != null ? request.getAttribute("password") : ""%>"
										required>
									<div class="input-group-addon">
										<span class="input-group-text"> <a href=""><i
												class="fa fa-eye-slash" aria-hidden="true"></i></a>
										</span>
									</div>
								</div>


							</div>
							<p class="small">
								<a class="text-primary" href="forget-password.html">¿Olvidaste
									la contraseña?</a>
							</p>
							<div class="d-grid">
								<button class="btn btn-outline-dark" type="submit">Iniciar
									sesión</button>
							</div>
						</form>
						<div>
							<p class="mb-0  text-center">
								¿No tienes una cuenta creada? <a href="registro1"
									class="text-primary fw-bold">Registrarse</a>
							</p>
						</div>


					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(
			function() {
				$("#show_hide_password a")
						.on(
								'click',
								function(event) {
									event.preventDefault();
									if ($('#show_hide_password input').attr(
											"type") == "text") {
										$('#show_hide_password input').attr(
												'type', 'password');
										$('#show_hide_password i').addClass(
												"fa-eye-slash");
										$('#show_hide_password i').removeClass(
												"fa-eye");
									} else if ($('#show_hide_password input')
											.attr("type") == "password") {
										$('#show_hide_password input').attr(
												'type', 'text');
										$('#show_hide_password i').removeClass(
												"fa-eye-slash");
										$('#show_hide_password i').addClass(
												"fa-eye");
									}
								});
			});
</script>
