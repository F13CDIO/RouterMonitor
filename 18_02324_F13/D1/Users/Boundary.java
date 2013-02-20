package Users;

import java.util.Scanner;
import Exceptions.DALException;

public class Boundary {
	IUserFunction uFunc;
	Scanner scan;
	
	//Constructor
	public Boundary(){
		uFunc = new UserFunction();
		scan = new Scanner(System.in);
	}
	
	/**
	 *Displays main menu and redirects to the right methods 
	 */
	private void startMenu(){
		while (true) {
			System.out.println("---------- Main Menu ----------\n" +
					  		   "- Type '1' for administrator menu\n" +
					 		   "- Type '2' to change your password\n" +
					 		   "- Type '3' to run the simple scale program\n" +
					 		   "- Type '0' to quit\n" +
					  		   "-------------------------");
			switch (scan.nextInt()) {
			case 1:
				adminMenu();
				break;
			case 2:
				changePass();
				break;
			case 3:
				runSimpProg();
				break;
			case 4:
				return;
			}
		}
	}
	
	private void adminMenu(){
		//TODO Log ind som admin skal implementeres
		
		System.out.println("---------- Administrator Menu ----------\n"+
						   "- Type '1' to create a user\n" +
						   "- Type '2' to delete a user\n" +
						   "- Type '3' to show user info\n" +
						   "- Type '4' to update a user\n" +
						   "- Type '0' to go back\n" +
						   "----------------------------------------");
		
		//TODO Implent the choices
	}
	/**
	 * Calls the updateUser method to change the password
	 */
	private void changePass(){
		System.out.println("Enter your ID:");
		int id = scan.nextInt();
		try {
			uFunc.updateUser(uFunc.getUser(id));
		} catch (DALException e) {
			// TODO catch the right exceptions here
		}
	}
	
	private void runSimpProg(){
		//TODO implement the simple program
	}

}
