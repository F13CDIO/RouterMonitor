package Presentation;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Exceptions.DALException;
import Scale.ScaleProgram;
import Users.IUserFunction;

public class FrameUserOverview 
{
	private JFrame frmUserOverview;
	private JTable table = null;
	private JButton btnAddUser;
	private JButton btnDelete;
	private JButton btnEdit;
	private JScrollPane scrollPane = new JScrollPane();
	private int userTypeLoggedIn;
	private IUserFunction func;
	private int userId;
	
	public FrameUserOverview(int userType, int userId, IUserFunction func) 
	{
		this.userTypeLoggedIn = userType;
		this.func = func;
		this.userId = userId;
		initialize();
		
		// If not Administrator
		if (this.userTypeLoggedIn > 0)
		{
			btnAddUser.setEnabled(false);
			btnDelete.setEnabled(false);
		}
		
		updateTable();
	}

	public void setVisible(boolean visible)
	{
		frmUserOverview.setVisible(visible);
	}
	
	private void initialize() 
	{
		// Window listener - updates table when frame gets activated
		WindowListener listener = new WindowListener() 
		{
			@Override
			public void windowOpened(WindowEvent e) {}
			
			@Override
			public void windowIconified(WindowEvent e) {}
			
			@Override
			public void windowDeiconified(WindowEvent e) {}
			
			@Override
			public void windowDeactivated(WindowEvent e) {}
			
			@Override
			public void windowClosing(WindowEvent e) {}
			
			@Override
			public void windowClosed(WindowEvent e) {}
			
			@Override
			public void windowActivated(WindowEvent e) 
			{
				updateTable();
			}
		};
		
		// Frame
		frmUserOverview = new JFrame();
		frmUserOverview.setTitle("User overview");
		frmUserOverview.setBounds(100, 100, 614, 356);
		frmUserOverview.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmUserOverview.getContentPane().setLayout(null);
		frmUserOverview.addWindowListener(listener);

		// Scrollpane
		scrollPane.setBounds(10, 45, 578, 231);
		frmUserOverview.getContentPane().add(scrollPane);
		
		// Buttons
		btnAddUser = new JButton("Add user");
		btnAddUser.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				FrameAddUser winAddUSer = new FrameAddUser(func);
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
					func.deleteUser(Integer.parseInt(userID));
					JOptionPane	.showMessageDialog(frmUserOverview, name +  " deleted");
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
					FrameEditUser frameEditUser = new FrameEditUser(userTypeLoggedIn, Integer.parseInt(userID), func);
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
		
		JButton btnTestWeight = new JButton("Test weight");
		btnTestWeight.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				ScaleProgram scale = new ScaleProgram();
				
				scale.setBrutto(100);
				scale.setTara(10);
				JOptionPane	.showMessageDialog(frmUserOverview, "Small test: (Brutto: 100, Tara: 10) = " + scale.getNetto());
			}
		});
		btnTestWeight.setBounds(305, 11, 283, 23);
		frmUserOverview.getContentPane().add(btnTestWeight);
	}
	
	public void updateTable()
	{	
		String[] columnNames = {"ID", "Name", "CPR", "Type"};
		Object[][] data = null;
		
		try 
		{
			if (userTypeLoggedIn > 0)
			{
				data = new Object[1][4];
				data[0] = func.userData(userId);
			}
			
			else
				data = func.getUserList();
		} 
		
		catch (DALException e) 
		{
			e.printStackTrace();
		}

		table = new JTable(data,columnNames);
		scrollPane.setViewportView(table);	
	}
}