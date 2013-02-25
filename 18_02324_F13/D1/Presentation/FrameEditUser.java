package Presentation;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

import Exceptions.DALException;
import Users.IUserFunction;

public class FrameEditUser 
{
	int userTypeLoggedIn;
	private IUserFunction func;
	private String[] userData;
	
	// Constructor
	public FrameEditUser(int userTypeLoggedIn,int userId, IUserFunction func)
	{
			initialize();
			this.func = func;
			this.userTypeLoggedIn = userTypeLoggedIn;
			
			if (this.userTypeLoggedIn > 0)
			{
				textCPR.setEditable(false);
				textName.setEditable(false);
			}
			
			try {
				userData = func.userData(userId);
				textName.setText(userData[1]);
				textCPR.setText(userData[2]);
				
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	JFrame frmEditUser;
	private JTextField textName;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textCPR;
	private JPasswordField passwordOld;
	private JPasswordField passwordNew;
	private JPasswordField passwordConfirm;
	
	public void setVisible(boolean visible)
	{
		frmEditUser.setVisible(visible);
	}
	
	private void initialize() 
	{
		//Frame
		frmEditUser = new JFrame();
		frmEditUser.setTitle("Edit user");
		frmEditUser.setBounds(100, 100, 215, 266);
		frmEditUser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEditUser.getContentPane().setLayout(null);
		
		//Text boxes
		textName = new JTextField();
		textName.setBounds(10, 28, 86, 20);
		frmEditUser.getContentPane().add(textName);
		textName.setColumns(10);
		
		textCPR = new JTextField();
		textCPR.setBounds(106, 28, 86, 20);
		frmEditUser.getContentPane().add(textCPR);
		textCPR.setColumns(10);
		
		// Password boxes
		passwordOld = new JPasswordField();
		passwordOld.setBounds(10, 73, 182, 20);
		frmEditUser.getContentPane().add(passwordOld);
		
		passwordNew = new JPasswordField();
		passwordNew.setBounds(10, 119, 182, 20);
		frmEditUser.getContentPane().add(passwordNew);
		
		passwordConfirm = new JPasswordField();
		passwordConfirm.setBounds(10, 165, 182, 20);
		frmEditUser.getContentPane().add(passwordConfirm);
		
		// Labels
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 12, 46, 14);
		frmEditUser.getContentPane().add(lblName);
		
		JLabel lblCpr = new JLabel("CPR");
		lblCpr.setBounds(106, 11, 46, 14);
		frmEditUser.getContentPane().add(lblCpr);
		
		JLabel lblPassword = new JLabel("Old password");
		lblPassword.setBounds(10, 58, 86, 14);
		frmEditUser.getContentPane().add(lblPassword);
		
		JLabel lblConfirmNewPassword = new JLabel("Confirm new password");
		lblConfirmNewPassword.setBounds(10, 150, 142, 14);
		frmEditUser.getContentPane().add(lblConfirmNewPassword);
		
		JLabel lblNewPassword = new JLabel("New password");
		lblNewPassword.setBounds(10, 104, 86, 14);
		frmEditUser.getContentPane().add(lblNewPassword);
		
		// Buttons
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{	
				String oldPass = new String(passwordOld.getPassword());
				String newPass = new String(passwordNew.getPassword());
				String confPass = new String(passwordConfirm.getPassword());
				try 
				{
							
					// IF Admin and old password is not entered
					if ((oldPass.length() == 0) && userTypeLoggedIn == 0)
					{
						int userId = Integer.parseInt(userData[0]);
						func.updateUser(userId, textName.getText(), textCPR.getText());
						setVisible(false);
					}
				
					else
					{
						if (newPass.equals(confPass) && func.validPass(Integer.parseInt(userData[0]), oldPass))
						{
							String pass = new String(passwordNew.getPassword());
							int userId = Integer.parseInt(userData[0]);
							func.updateUser(userId, textName.getText(), textCPR.getText(), pass);
							setVisible(false);
						}
						else
							JOptionPane	.showMessageDialog(frmEditUser,"Something is wrong with the password");
						
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
		btnUpdate.setBounds(10, 196, 182, 20);
		frmEditUser.getContentPane().add(btnUpdate);
	}
}
