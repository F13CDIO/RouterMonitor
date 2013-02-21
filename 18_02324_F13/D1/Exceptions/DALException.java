package Exceptions;

public class DALException extends Exception {

	/**
	 * @param oprID The out of bounds ID
	 */
	public DALException(int oprID) {
		super("Index: " + oprID + " out of bounds!");
	}

	/**
	 * No list!
	 */
	public DALException() {
		super("No list");
	}
	/**
	 * If the password is not valid you get this exception.
	 * @param valid
	 */
	public DALException(boolean valid) {
		super(
				"Your pasword need to contain one of the four following: \n"
				+"An uppercase letter from 'A' to 'Z'\n"
				+"A lowercase letter from 'a' to 'z'\n"
				+"A number from '0' to '9'\n"
				+"A special char of {'.', '-', '_', '+', '!', '?', '='}");
	}
}