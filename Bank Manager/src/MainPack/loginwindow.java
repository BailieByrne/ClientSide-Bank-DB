package MainPack;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.Logger;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import java.awt.Component;
import javax.swing.Box;

public class loginwindow extends JFrame implements ActionListener{
	private static final Logger fileLogger = Main.fileLogger;
	private JPanel contentPane;
	private static JFrame loginFrame = new loginwindow();
	ReturnHash hash = new ReturnHash();
	DB database = new DB();

	/**
	 * Launch the application.
	 */
	public static void main() {
		fileLogger.info("Created LoginWindow");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fileLogger.info("Running LoginWindow main()");
					loginFrame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
					fileLogger.error("ERROR during LoginWindow main()" + e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	JButton RegisterButton = new JButton("Register New Account");
	JButton LoginButton = new JButton("Login");
	JPanel panel = new JPanel();
	JFormattedTextField UsernameField = new JFormattedTextField();
	JPasswordField passwordField = new JPasswordField();
	JPasswordField confirmpasswordField = new JPasswordField();
	
	public loginwindow() {
		setTitle("Byrne Banking Solutions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(139, 63, 375, 296);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		LoginButton.setBounds(117, 139, 139, 63);
		panel.add(LoginButton);
		LoginButton.setFont(new Font("Georgia", Font.PLAIN, 11));
		LoginButton.setForeground(new Color(0, 0, 0));
		LoginButton.setBackground(new Color(0, 255, 128));
		LoginButton.addActionListener(this);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(117, 95, 139, 20);
		panel.add(passwordField);
		passwordField.setToolTipText("Password");
		
		
		RegisterButton.setBounds(111, 262, 165, 23);
		panel.add(RegisterButton);
		RegisterButton.addActionListener(this);
		
		UsernameField.setBounds(117, 64, 139, 20);
		panel.add(UsernameField);
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(160, 11, 68, 28);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Username:");
		lblNewLabel_2.setBounds(37, 67, 70, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password:");
		lblNewLabel_3.setBounds(37, 98, 70, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1 = new JLabel("BYRNE BANKING");
		lblNewLabel_1.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(250, 0, 151, 64);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("Our Agents Will Never Ask For Your Password");
		lblNewLabel_4.setBounds(10, 370, 260, 26);
		contentPane.add(lblNewLabel_4);
		lblNewLabel_4.setForeground(new Color(255, 0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Do Not Share These Details");
		lblNewLabel_5.setForeground(new Color(255, 0, 0));
		lblNewLabel_5.setBounds(10, 401, 175, 14);
		contentPane.add(lblNewLabel_5);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==RegisterButton) {
			loginFrame.dispose();
			fileLogger.info("Creating RegisterWindow");
			registerwindow registerframe = new registerwindow();
			registerframe.main();
			
		}
		
		else if(e.getSource()==LoginButton) {
			String[] loginAttempt = {UsernameField.getText(),String.valueOf(hash.GetHash(passwordField.getText()))};
			if (database.AuthorizeLogin(loginAttempt) == true) {
				fileLogger.info("Authorization Successful!");
			} else {
				fileLogger.info("Authorization Unsuccessful!");
				
			}	
		}
	}
}


	
