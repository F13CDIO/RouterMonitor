package Presentation;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import Exceptions.DALException;
import Users.IUserFunction;

public class FrameUserOverview 
{
	private JFrame frmUserOverview;
	private JTable table = null;
	private JButton btnAddUser;
	private JButton btnDelete;
	private JButton btnEdit;
	private String[] columnNames = {"ID", "Name", "CPR", "Data"};
	private JScrollPane scrollPane = new JScrollPane();
	private JButton btnTestProgram;
	private int userType;
	private IUserFunction func;
	
	public FrameUserOverview(int userType, IUserFunction func) 
	{
		this.userType = userType;
		this.func = func;
		initialize();
		
		// If not Administrator
		if (this.userType > 0)
		{
			btnAddUser.setEnabled(false);
			btnDelete.setEnabled(false);
		}
		
		updateTable(userType);
	}

	public void setVisible(boolean visible)
	{
		frmUserOverview.setVisible(visible);
	}
	
	private void initialize() 
	{
		// Frame
		frmUserOverview = new JFrame();
		frmUserOverview.setTitle("User overview");
		frmUserOverview.setBounds(100, 100, 614, 356);
		frmUserOverview.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmUserOverview.getContentPane().setLayout(null);

		// Scrollpane
		scrollPane.setBounds(10, 45, 578, 231);
		frmUserOverview.getContentPane().add(scrollPane);
		
		// Buttons
		btnAddUser = new JButton("Add user");
		btnAddUser.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				FrameAddUser winAddUSer = new FrameAddUser();
				winAddUSer.setVisible(true);
			}
		});
		
		btnAddUser.setBounds(10, 11, 285, 23);
		frmUserOverview.getContentPane().add(btnAddUser);
		
		btnDelete = new JButton("Delete selected");
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					String userID = (String)table.getValueAt(table.getSelectedRow(), 0);
					String name = (String)table.getValueAt(table.getSelectedRow(), 1);
					JOptionPane	.showMessageDialog(frmUserOverview, name +  " deleted");
					// call controller
					
				}
				
				catch(Exception e2)
				{
					JOptionPane	.showMessageDialog(frmUserOverview, "Error. No input.");
				}
			
			}
		});
		
		btnDelete.setBounds(304, 286, 284, 23);
		frmUserOverview.getContentPane().add(btnDelete);
		
		
		btnEdit = new JButton("Edit selected");
		btnEdit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				try
				{
					String userID = (String)table.getValueAt(table.getSelectedRow(), 0);
					FrameEditUser frameEditUser = new FrameEditUser(Integer.parseInt(userID), func);
					frameEditUser.setVisible(true);
				}
				
				catch(Exception e2)
				{
					JOptionPane	.showMessageDialog(frmUserOverview, "Error. No input.");
				}

			}
		});
		btnEdit.setBounds(10, 286, 284, 23);
		frmUserOverview.getContentPane().add(btnEdit);
		
		btnTestProgram = new JButton("Test weight");
		btnTestProgram.addActionListener(new ActionListener() 
		{	
			public void actionPerformed(ActionEvent arg0) 
			{
				JOptionPane	.showMessageDialog(frmUserOverview,"WEIGHT CALL");
			}
		});
		btnTestProgram.setBounds(305, 11, 284, 23);
		frmUserOverview.getContentPane().add(btnTestProgram);
	}
	
	public void updateTable(int userType)
	{	
		Object[][] data = null;
		try {
			data = func.getUserList();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		table = new JTable(data,columnNames);
		scrollPane.setViewportView(table);	
	}
}