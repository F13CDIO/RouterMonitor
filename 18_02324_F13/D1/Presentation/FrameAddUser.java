package Presentation;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameAddUser 
{
	JFrame frmAddNewUser;
	private JTextField textName;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textCPR;
	
	// Constructor
	public FrameAddUser() 
	{
		initialize();
	}
	
	public void setVisible(boolean visible)
	{
		frmAddNewUser.setVisible(visible);
	}
	
	private void initialize() 
	{
		// Frame
		frmAddNewUser = new JFrame();
		frmAddNewUser.setTitle("Add new user");
		frmAddNewUser.setBounds(100, 100, 349, 138);
		frmAddNewUser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAddNewUser.getContentPane().setLayout(null);
		
		//Text boxes
		textName = new JTextField();
		textName.setBounds(115, 28, 86, 20);
		frmAddNewUser.getContentPane().add(textName);
		textName.setColumns(10);
		
		textCPR = new JTextField();
		textCPR.setBounds(211, 28, 86, 20);
		frmAddNewUser.getContentPane().add(textCPR);
		textCPR.setColumns(10);
		
		//Radio buttons
		JRadioButton rbStudent = new JRadioButton("Student");
		rbStudent.setActionCommand("1");
		buttonGroup.add(rbStudent);
		rbStudent.setBounds(17, 7, 93, 23);
		frmAddNewUser.getContentPane().add(rbStudent);
		
		JRadioButton rbTeacher = new JRadioButton("Teacher");
		buttonGroup.add(rbTeacher);
		rbTeacher.setActionCommand("2");
		rbTeacher.setBounds(17, 25, 92, 23);
		frmAddNewUser.getContentPane().add(rbTeacher);
		
		JRadioButton rbAdmin = new JRadioButton("0");
		buttonGroup.add(rbAdmin);
		rbAdmin.setBounds(17, 45, 92, 23);
		rbAdmin.setActionCommand("Admin");
		frmAddNewUser.getContentPane().add(rbAdmin);
		
		//Labels
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(115, 12, 46, 14);
		frmAddNewUser.getContentPane().add(lblName);
		
		JLabel lblCpr = new JLabel("CPR");
		lblCpr.setBounds(211, 11, 46, 14);
		frmAddNewUser.getContentPane().add(lblCpr);
		
		// Buttons
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String name = textName.getText();
				String CPR = textCPR.getText();
				
				if (buttonGroup.getSelection() != null && !name.equals("") && !CPR.equals(""))
				{
					int selectedRadioButton = Integer.parseInt(buttonGroup.getSelection().getActionCommand());
					
					switch (selectedRadioButton)
					{
						case 0:
							break;
						
						case 1:
							JOptionPane	.showMessageDialog(frmAddNewUser,"User created. Password = Wh5N90K");
							break;
							
						case 2:
							break;
							
						
							
							default:
								break;
					}			
					System.out.println("Selected: " + selectedRadioButton + ", Name: "+ name + ", CPR: " + CPR);
				}
				
				else
					JOptionPane	.showMessageDialog(frmAddNewUser,"Invalid input. Try again");
			}
		});
		
		btnAdd.setBounds(115, 59, 182, 20);
		frmAddNewUser.getContentPane().add(btnAdd);
	}
}
