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

	<div class="row">
		<div class="mx-auto mt-5 col-10 col-md-8 col-lg-6">

			<form action="Contacto" method="post">
				<!-- Name input -->
				<div class="form-outline mb-4">
				<label
						class="form-label" for="form4Example1">Email</label>
					<input type="email" id="form4Example1" name="email" class="form-control" /> 
				</div>

				<!-- Email input -->
				<div class="form-outline mb-4">
				<label
						class="form-label" for="form4Example2">Asunto</label>
					<input type="text" id="form4Example2" name="asunto" class="form-control" required/> 
				</div>

				<!-- Message input -->
				<div class="form-outline mb-4">
				<label class="form-label" for="form4Example3">Mensaje</label>
					<textarea class="form-control" name="mensaje" id="form4Example3" rows="4"  required></textarea>
					
				</div>

				<!-- Checkbox -->
				<div class="container mt-4 mb-4 text-lg-center">
					<input class="form-check-input me-2" name="copia" type="checkbox" value="1"
						id="form4Example4"   /> <label class="form-check-label"
						for="form4Example4"> Enviarme una copia del mensaje </label>
				</div>

				<!-- Submit button -->
				<button type="submit" class="btn btn-primary btn-block mb-4">Send</button>
			</form>
		</div>
	</div>


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