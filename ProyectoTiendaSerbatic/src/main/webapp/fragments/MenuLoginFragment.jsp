<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
	<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
		id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown"
		aria-expanded="false">Bienvenido <%=userSessionModel.getUsuario().getNombre()%></a>
		<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
			<li><a class="dropdown-item" href="#!">Mis datos</a></li>
			<li><a class="dropdown-item" href="#!">Ajustes de cuenta</a></li>
			<li><hr class="dropdown-divider" /></li>
			<li><a class="dropdown-item" href="finalizarloginstatus">Cerrar
					sesión</a></li>
		</ul></li>
</ul>