package Presentation;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.JPasswordField;

public class FrameEditUser 
{
	int userType;
	
	// Constructor
	public FrameEditUser(int userId) 
	{
			initialize();
			this.userType = userType;
			
			if (this.userType > 0)
			{
				textCPR.setEditable(false);
				textName.setEnabled(false);
			}
			
			// userObject = getUSerObject
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
				if (passwordOld.getPassword().length == 0)
				{
					System.out.println("Old empty");
				}
				
				else
				{
					if (Arrays.equals(passwordNew.getPassword(), passwordConfirm.getPassword()) && passwordNew.getPassword().length > 0)
					{
						System.out.println("Same");
						System.out.println(passwordNew.getPassword().length);
					}
					
					else
					{
						System.out.println("Not the same");
					}
				}
					
			}
		
		
		});
		btnUpdate.setBounds(10, 196, 182, 20);
		frmEditUser.getContentPane().add(btnUpdate);
		
		
	}
}
