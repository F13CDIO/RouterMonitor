package Presentation;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

import Exceptions.DALException;
import Users.IUserFunction;

public class FrameMain 
{
	private JFrame frmLogin;
	private JTextField textId;
	private JPasswordField passwordField;
	private IUserFunction func;
	
	public FrameMain(IUserFunction func) 
	{
		this.func = func;
		initialize();
	}
	
	public void setVisible(boolean visible)
	{
		frmLogin.setVisible(visible);
	}

	
	private void initialize() 
	{
		// Frame
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 242, 133);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		//Labels
		JLabel lblUserId = new JLabel("User id");
		lblUserId.setBounds(22, 11, 89, 14);
		frmLogin.getContentPane().add(lblUserId);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(118, 11, 89, 14);
		frmLogin.getContentPane().add(lblPassword);
		
		//Password box
		passwordField = new JPasswordField();
		passwordField.setBounds(118, 25, 89, 20);
		frmLogin.getContentPane().add(passwordField);

		// Text boxes
		textId = new JTextField();
		textId.setBounds(22, 25, 89, 20);
		frmLogin.getContentPane().add(textId);
		textId.setColumns(10);
		
		//Buttons
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					// Checks the entered password and logs in as the usertype recieved
					String pass = new String(passwordField.getPassword());
					int uID = Integer.parseInt(textId.getText());
					int userType = func.checkLogin(uID,pass);
					
					// If no user
					if (userType == -1)					
					{
						JOptionPane.showMessageDialog(frmLogin,"Invalid input. Try again");
					}
					
					else
					{
						FrameUserOverview frameUserAdmin = new FrameUserOverview(userType,uID, func);
						frameUserAdmin.setVisible(true);
					}
				} 
				
				catch (NumberFormatException e) 
				{
					e.printStackTrace();
				} 
				
				catch (DALException e) 
				{
					e.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(22, 56, 185, 23);
		frmLogin.getContentPane().add(btnLogin);		
	}
}
