import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewAccounts implements ActionListener {
	
	private JFrame frame;
	private JLabel title;
	private DefaultTableModel accountsTableModel;
	private JTable accountsTable;
	private JScrollPane accountsScrollPane;
	private JPanel mainPanel;
	private JButton backButton;
	
	public ViewAccounts(ArrayList<ArrayList<String>> allData) {
		frame = new JFrame("View Accounts");

		// UI components
		backButton = new JButton("Back");
		backButton.setFont(new Font("Helvetica", Font.PLAIN, 13));
		backButton.setBounds(50, 17, 115, 25);
		backButton.addActionListener(this);
		
		title = new JLabel("Existing Student Accounts");
		title.setFont(new Font("Helvetica", Font.PLAIN, 15));
		title.setBounds(400, 5, 200, 50);
		
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 50, 1000, 700);
		
		String[] columns = {"Student ID", "Name", "Course", "Level", "Address", "Email", "Contact Number"};
		int len = allData.size();
		int row = 0, col = 0;
		Object[][] data = new Object[len][7];
		RecordRepository record = new RecordRepository(allData);
		for (Iterator itr = record.getIterator(); itr.hasNext();) {
			if (col < 7) {
				data[row][col] = itr.next();
				col++;
			} else if (row < len) {
				row++;
				col = 0;
			}
		}
		accountsTable = new JTable(data, columns);
		accountsTable.setPreferredScrollableViewportSize(new Dimension(900, 600));
		accountsTable.setFillsViewportHeight(true);
		accountsTable.setEnabled(false);
		
		accountsScrollPane = new JScrollPane(accountsTable);
		mainPanel.add(accountsScrollPane); // JTable needed to be inside JPanel
		
		// add components
		frame.add(backButton);
		frame.add(title);
		frame.add(mainPanel);
		
		// main frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(1000, 740);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) {
			new AccountManagement();
			frame.dispose();
		}
	}
	
	public static void main(String[] args) {
		ArrayList<ArrayList<String>> allSample = new ArrayList<ArrayList<String>>();
		
		// sample data
		String[] array = {"000", "Student 1", "Sample Course 1", "2", "Sample Address 1", "sample1@gmail.com", "87000"};
		ArrayList<String> sample1 = new ArrayList<String>();
		Collections.addAll(sample1, array);
		allSample.add(sample1);
		
		ArrayList<String> sample2 = new ArrayList<String>();
		array = new String[] {"001", "Student 2", "Sample Course 2", "4", "Sample Address 2", "sample2@gmail.com", "86236"};
		Collections.addAll(sample2, array);
		allSample.add(sample2);
		
		new ViewAccounts(allSample);
	}
}
