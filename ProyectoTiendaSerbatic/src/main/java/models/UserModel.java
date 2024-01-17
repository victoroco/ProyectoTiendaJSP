package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserModel {

	private int idUsuario;
	private int rol_id;
	private String email;
	private String password;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String provincia;
	private String ciudad;
	private String telefono;
	private String dni;
	private boolean baja;

	public UserModel(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public UserModel(String email, String password, String nombre, String apellidos, String direccion, String provincia,
			String ciudad, String telefono, String dni) {
		super();
		this.rol_id = 2;
		this.email = email;
		this.password = password;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.provincia = provincia;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.dni = dni;
		this.baja = false;
	}

	public UserModel(String nombre, String apellidos, String direccion, String provincia, String ciudad,
			String telefono, String dni) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.provincia = provincia;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.dni = dni;
	}

	
	
}
