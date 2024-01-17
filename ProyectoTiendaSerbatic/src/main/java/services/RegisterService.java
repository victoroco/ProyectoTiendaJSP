package services;

import dao.UserDAO;
import models.UserModel;

public class RegisterService {

	
	
	public boolean registroVacio(UserModel usuario) {

		if (usuario.getNombre().equalsIgnoreCase("") || usuario.getApellidos().equalsIgnoreCase("")
				|| usuario.getEmail().equalsIgnoreCase("") || usuario.getPassword().equalsIgnoreCase("")
				|| usuario.getDireccion().equalsIgnoreCase("") || usuario.getProvincia().equalsIgnoreCase("")
				|| usuario.getCiudad().equalsIgnoreCase("") || usuario.getTelefono().equalsIgnoreCase("")
				|| usuario.getDni().equalsIgnoreCase("")) {
			return true;

		} else {
			return false;
		}
	}

	public boolean actualizarDatos(UserModel usuario) {

		if (usuario.getNombre().equalsIgnoreCase("") || usuario.getApellidos().equalsIgnoreCase("")
				|| usuario.getDireccion().equalsIgnoreCase("") || usuario.getProvincia().equalsIgnoreCase("")
				|| usuario.getCiudad().equalsIgnoreCase("") || usuario.getTelefono().equalsIgnoreCase("")
				|| usuario.getDni().equalsIgnoreCase("")) {
			return true;

		} else {
			return false;
		}
	}

	public boolean emailExiste(String email) {

		if (UserDAO.getAllUserData(email) != null) {
			return true;
		}
		return false;
	}

	public boolean insertarNuevoUsuario(UserModel userModel) {

		if (UserDAO.insertarUsuario(userModel)) {
			return true;
		}

		return false;

	}

	public boolean actualizarDatosUsuario(UserModel userModel) {

		if (UserDAO.updateDatosUsuario(userModel)) {
			return true;
		}

		return false;

	}

	public UserModel integrarUpdateDatosUserSesion(UserModel userModelSesion, UserModel userModel) {

		userModelSesion.setNombre(userModel.getNombre());
		userModelSesion.setApellidos(userModel.getApellidos());
		userModelSesion.setDireccion(userModel.getDireccion());
		userModelSesion.setProvincia(userModel.getProvincia());
		userModelSesion.setCiudad(userModel.getCiudad());
		userModelSesion.setTelefono(userModel.getTelefono());
		userModelSesion.setDni(userModel.getDni());

		return userModelSesion;
	}

	public boolean validarEmailPasswordViejos(String email, String password) {

		if (UserDAO.checkUserPasswordDataDB(email, password)) {

			return true;

		}

		return false;
	}

	public boolean emailPasswordCamposVacios(String email, String password) {

		if (email.equals("") || password.equals("")) {
			return true;
		}

		return false;
	}

	public boolean actualizarEmailPassword(UserModel userModel, String email, String passwordNueva) {

		if (UserDAO.updateEmailPassword(userModel, email, passwordNueva)) {

			return true;

		}

		return false;
	}

	public boolean validarPasswordNuevasSiVacias(String passwordNueva, String repetirPasswordNueva) {

		
		if((passwordNueva.equals("") && repetirPasswordNueva.equals(""))){
			return true;
		}
		
		
		
		
		if((passwordNueva.equals("") && !repetirPasswordNueva.equals("")) ||(!passwordNueva.equals("") && repetirPasswordNueva.equals(""))) {
			return false;
		}
		

		return false;
	}

}
