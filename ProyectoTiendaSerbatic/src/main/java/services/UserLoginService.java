package services;

import dao.UserDAO;
import models.UserModel;

public class UserLoginService {

	public boolean loginSuccess(String email, String password) {
		// UserDAO userDAO = new UserDAO();

		return UserDAO.checkUserPasswordDataDB(email, password);

	}

	public UserModel getUserData(String email) {
		// UserDAO userDAO = new UserDAO();

		UserModel u = UserDAO.getAllUserData(email);

		return u;

	}

	/*public static void main(String[] args) {

		String email = "aaaa@aa.a";
		String password = "123";

		if (loginSuccess(email, password)) {
			System.out.println("Login correcto");

			UserModel u = UserDAO.getAllUserData(email);

			System.out.println("Datos usuario: " + u.getEmail());
		}

	}*/

}
