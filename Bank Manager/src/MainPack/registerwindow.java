package MainPack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.Logger;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;


public class registerwindow extends JFrame implements ActionListener {
	private static final Logger fileLogger = Main.fileLogger;
	private JPanel contentPane;
	static registerwindow registerFrame = new registerwindow();
	static DB database = new DB();

	/**
	 * Launch the application.
	 */
	public static void main() {
		fileLogger.info("Created RegisterWindow");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fileLogger.info("Running RegisterWindow main()");
					registerFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					fileLogger.error("ERROR during RegisterWindow main()" + e.getMessage());
					
				}
			}
		});
	}

	JButton ReturnButton = new JButton("Return To Login");
	JButton SubmitRegister = new JButton("REGISTER");
	
	JFormattedTextField usernameTxtField = new JFormattedTextField();
	JFormattedTextField forenameTxtField = new JFormattedTextField();
	JFormattedTextField surnameTxtField = new JFormattedTextField();
	JFormattedTextField addressTxtField = new JFormattedTextField();
	JPasswordField passwordField = new JPasswordField();
	JPasswordField confirmPasswordField = new JPasswordField();

	
	public registerwindow() {
		setTitle("Byrne Banking Solutions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(136, 43, 365, 281);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel UsernameLabel = new JLabel("Username:");
		UsernameLabel.setBounds(30, 27, 66, 14);
		panel.add(UsernameLabel);
		
		JLabel PasswordLabel = new JLabel("Password:");
		PasswordLabel.setBounds(30, 211, 66, 14);
		panel.add(PasswordLabel);
		
		JLabel ConPasswordLabel = new JLabel("Confirm Password:");
		ConPasswordLabel.setBounds(30, 237, 95, 14);
		panel.add(ConPasswordLabel);
		
		JLabel ForenameLabel = new JLabel("Forename:");
		ForenameLabel.setBounds(30, 52, 66, 14);
		panel.add(ForenameLabel);
		
		JLabel SurnameLabel = new JLabel("Surname:");
		SurnameLabel.setBounds(30, 77, 66, 14);
		panel.add(SurnameLabel);
		
		JLabel AddressLabel = new JLabel("Address Line:");
		AddressLabel.setBounds(30, 105, 66, 14);
		panel.add(AddressLabel);
		

		passwordField.setBounds(133, 208, 120, 20);
		panel.add(passwordField);
		

		confirmPasswordField.setBounds(133, 234, 120, 20);
		panel.add(confirmPasswordField);
		

		usernameTxtField.setBounds(106, 24, 170, 20);
		panel.add(usernameTxtField);
		


		forenameTxtField.setBounds(106, 49, 170, 20);
		panel.add(forenameTxtField);
		


		surnameTxtField.setBounds(106, 74, 170, 20);
		panel.add(surnameTxtField);
		


		addressTxtField.setBounds(106, 102, 170, 20);
		panel.add(addressTxtField);
		
		JLabel lblNewLabel = new JLabel("REGISTRATION FORM");
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(233, 0, 181, 32);
		contentPane.add(lblNewLabel);
		
		SubmitRegister.setBounds(233, 335, 181, 45);
		contentPane.add(SubmitRegister);
		SubmitRegister.addActionListener(this);
		
		ReturnButton.setBounds(0, 398, 138, 23);
		contentPane.add(ReturnButton);
		ReturnButton.addActionListener(this);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==SubmitRegister) {
			if(passwordField.getText().equals(confirmPasswordField.getText())) {
				String[] registerAttempt = {usernameTxtField.getText(),forenameTxtField.getText(),surnameTxtField.getText(),addressTxtField.getText(),passwordField.getText()};
				String response = database.RegisterUser(registerAttempt);
				if(response == "UserCreated") {
					System.out.println("USER CREATED");
					registerFrame.dispose();
					fileLogger.info("Creating LoginWindow");
					loginwindow loginframe = new loginwindow();
					loginframe.main();
				} else if(response == "UserExists") {
					System.out.println("USER ALREADY EXISTS");
				} else if(response == "CharLimit"){
					System.out.println("CHARCTER LIMIT EXCEEDED");
				}else  if(response == "BlankChar"){
					System.out.println("BLANK INPUT");
				}else {
					System.out.println("CANNOT CONTACT SERVER");
				}
			}else {
				System.out.println("PASSWORDS DONT MATCH");
				fileLogger.info("Passwords Not Matching");
			}
		}
		
		else if(e.getSource()==ReturnButton) {
			registerFrame.dispose();
			fileLogger.info("Creating LoginWindow");
			loginwindow loginframe = new loginwindow();
			loginframe.main();
			}
			
		}
}
