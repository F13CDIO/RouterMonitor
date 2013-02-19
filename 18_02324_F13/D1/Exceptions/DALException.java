package Exceptions;

import Users.User.Rights;

public class DALException extends Exception {

	/**
	 * 
	 * @param oprID The out of bounds ID
	 */
	public DALException(int oprID) {
		super("Index: " + oprID + " out of bounds!");
	}

	/**
	 * 
	 */
	public DALException() {
		super("No list");
	}

	public DALException(Rights rights) {
		super(rights + " not valid user type!");
	}

	public DALException(boolean valid) {
		super(
				"Your pasword need to contain one of the four following: \n"
				+"An uppercase letter from 'A' to 'Z'\n"
				+"A lowercase letter from 'a' to 'z'\n"
				+"A number from '0' to '9'\n"
				+"A special char of {'.', '-', '_', '+', '!', '?', '='}");
	}
}