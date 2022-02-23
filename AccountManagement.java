import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class AccountManagement implements ActionListener {

	private JFrame frame;
	private JLabel title, studentID, name, course, level, address, email, contact;
	private JTextField studentIDTextField, nameTextField, addressTextField, emailTextField, contactTextField;
	private JComboBox courseComboBox, levelComboBox;
	private JButton updateButton, exitButton;
	private static ArrayList<ArrayList<String>> allData;
	
	public AccountManagement() {
		if (allData == null) {
			allData = new ArrayList<ArrayList<String>>();
		}
		frame = new JFrame("Account Management");
		
		// UI components
		title = new JLabel("Account Information");
		title.setFont(new Font("Helvetica", Font.BOLD, 15));
		title.setBounds(15, 3, 200, 50);
		
		studentID = new JLabel("Student ID:");
		studentID.setFont(new Font("Helvetica", Font.PLAIN, 15));
		studentID.setBounds(15, 33, 200, 50);
		
		studentIDTextField = new JTextField();
		studentIDTextField.setFont(new Font("Helvetica", Font.PLAIN, 12));
		studentIDTextField.setBounds(100, 47, 185, 25);
		
		name = new JLabel("Name:");
		name.setFont(new Font("Helvetica", Font.PLAIN, 15));
		name.setBounds(15, 73, 200, 50);
		
		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Helvetica", Font.PLAIN, 12));
		nameTextField.setBounds(100, 87, 185, 25);
		
		course = new JLabel("Course:");
		course.setFont(new Font("Helvetica", Font.PLAIN, 15));
		course.setBounds(15, 115, 200, 50);
		
		String[] courses = new String[] {"BSCS-SE", "BSIT-AGD", "BSIT-AD", "BSIT-SMBA"};
		courseComboBox = new JComboBox(courses);
		courseComboBox.setFont(new Font("Helvetica", Font.PLAIN, 12));
		courseComboBox.setBounds(100, 128, 155, 25);
		
		level = new JLabel("Level:");
		level.setFont(new Font("Helvetica", Font.PLAIN, 15));
		level.setBounds(15, 159, 200, 50);
		
		String[] levels = new String[] {"1", "2", "3", "4"};
		levelComboBox = new JComboBox(levels);
		levelComboBox.setFont(new Font("Helvetica", Font.PLAIN, 12));
		levelComboBox.setBounds(100, 170, 50, 25);
		
		address = new JLabel("Address:");
		address.setFont(new Font("Helvetica", Font.PLAIN, 15));
		address.setBounds(15, 199, 200, 50);
		
		addressTextField = new JTextField();
		addressTextField.setFont(new Font("Helvetica", Font.PLAIN, 12));
		addressTextField.setBounds(100, 210, 185, 25);
		
		email = new JLabel("Email:");
		email.setFont(new Font("Helvetica", Font.PLAIN, 15));
		email.setBounds(15, 239, 200, 50);
		
		emailTextField = new JTextField();
		emailTextField.setFont(new Font("Helvetica", Font.PLAIN, 12));
		emailTextField.setBounds(100, 250, 185, 25);
		
		contact = new JLabel("Contact Number:");
		contact.setFont(new Font("Helvetica", Font.PLAIN, 15));
		contact.setBounds(15, 279, 200, 50);
		
		contactTextField = new JTextField();
		contactTextField.setFont(new Font("Helvetica", Font.PLAIN, 12));
		contactTextField.setBounds(150, 290, 135, 25);
		
		updateButton = new JButton("UPDATE");
		updateButton.setFont(new Font("Helvetica", Font.PLAIN, 15));
		updateButton.setBounds(15, 349, 125, 30);
		updateButton.addActionListener(this);
		
		exitButton = new JButton("EXIT");
		exitButton.setFont(new Font("Helvetica", Font.PLAIN, 15));
		exitButton.setBounds(160, 349, 125, 30);
		exitButton.addActionListener(this);
		
		// add components
		frame.add(title);
		frame.add(studentID);
		frame.add(studentIDTextField);
		frame.add(name);
		frame.add(nameTextField);
		frame.add(course);
		frame.add(courseComboBox);
		frame.add(level);
		frame.add(levelComboBox);
		frame.add(address);
		frame.add(addressTextField);
		frame.add(email);
		frame.add(emailTextField);
		frame.add(contact);
		frame.add(contactTextField);	
		frame.add(updateButton);
		frame.add(exitButton);
		
		// main frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(320, 450);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == updateButton) {
			ArrayList<String> info = new ArrayList<String>();
			info.add(studentIDTextField.getText());
			info.add(nameTextField.getText());
			info.add(courseComboBox.getSelectedItem().toString());
			info.add(levelComboBox.getSelectedItem().toString());
			info.add(addressTextField.getText());
			info.add(emailTextField.getText());
			info.add(contactTextField.getText());
			
			// check blank text fields and non-numeric values in student id and contact no.
			boolean blank = false;
			boolean notNumeric = false;
			boolean invalidEmail = true;
			for (int i = 0; i < (int) info.size(); i++) {
				if (info.get(i).equals("")) {
					blank = true;
				} else if (i == 0 || i == (int) info.size() - 1) {
					for (int j = 0; j < (int) info.get(i).length(); j++) {
						if (info.get(i).charAt(j) < '0' || info.get(i).charAt(j) > '9') {
							notNumeric = true;
							break;
						}
					}
				} else if (i == 5 && info.get(i).contains("@")) {
					invalidEmail = false;
				}
				if (blank || notNumeric || (invalidEmail && i >= 5)) {
					break;
				}
			}
			
			if (blank) {
				JOptionPane.showMessageDialog(null, "Incomplete fields", "Notification", JOptionPane.WARNING_MESSAGE);
			} else if (notNumeric) {
				JOptionPane.showMessageDialog(null, "Numbers [0-9] only in Student ID and Contact Number", "Notification", JOptionPane.ERROR_MESSAGE);
			} else if (invalidEmail) {
				JOptionPane.showMessageDialog(null, "Email must contain \'@\'", "Notification", JOptionPane.ERROR_MESSAGE);
			} else {
				// update the record (if existing student)
				boolean existing = false;
				for (int i = 0; i < (int) allData.size(); i++) {
					if (info.get(0).toString().equals(allData.get(i).get(0).toString())) {
						for (int j = 1; j < (int) allData.get(i).size(); j++) {
							allData.get(i).set(j, info.get(j).toString());
						}
						existing = true;
						break;
					}
				}
				// add a new record (if new student)
				if (!existing) {
					allData.add(info);
				}
				JOptionPane.showMessageDialog(null, "Record updated sucessfully!", "Notification", JOptionPane.INFORMATION_MESSAGE);
				new ViewAccounts(allData);
				frame.dispose();
			}
		} else if (e.getSource() == exitButton) {
			frame.dispose();
		}
	}
	
	public static void main(String[] args) {
		allData = new ArrayList<ArrayList<String>>();
		new AccountManagement();
	}
}
