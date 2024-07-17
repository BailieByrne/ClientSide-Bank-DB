package MainPack;

import java.sql.*;

import org.apache.logging.log4j.Logger;


public class DB {
	private static final Logger fileLogger = Main.fileLogger;
	private static String url = "jdbc:mysql://localhost:3306/bank";
	private static String username = "[REDACTED]"; //Add these to ENV Variables
	private static String passw = "[REDACTED]";
	
	static ReturnHash hash = new ReturnHash();
	
	public static void FetchAllClients() {  //Static Void method to Fetch All ClientRecords
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");		//Grabs Driiver 
			Connection conn = DriverManager.getConnection(url,username,passw); //Gets the Connection
			
			Statement statement = conn.createStatement(); //Similar to python cursor
			
			ResultSet dbresult = statement.executeQuery("select * from clients"); //Store the ResultSet into a variable
			
			while(dbresult.next()) {  //While there is a next result print it
				System.out.println(dbresult.getInt(1)+" "+dbresult.getString(2)+" "+dbresult.getString(3)+" "+dbresult.getString(4));
				//Gets Values of ID/Forename/Surname/Address
			}
			
			conn.close(); //close conn
			
		} catch (ClassNotFoundException | SQLException e) {
			fileLogger.fatal("DATABSE ERROR" + e);
		}
	}
	
	public static String RegisterUser(String[] query) {
		fileLogger.info("Attempting To Register User");
		String Username = query[0];
		String Forename = query[1];
		String Surname = query[2];
		String Address = query[3];
		String Password = query[4];
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");		//Grabs Driiver 
			Connection conn = DriverManager.getConnection(url,username,passw); //Gets the Connection
			
			String checkExistance = "SELECT Username FROM clients WHERE Username = ?"; //Prepare SQL query seperatley so user doesnt have direct access to Statement
			PreparedStatement preparedStatement = conn.prepareStatement(checkExistance); //Python cursor essentially
			preparedStatement.setString(1, Username);//Insert the entered password into the string
			ResultSet existingUsers = preparedStatement.executeQuery();
			if(existingUsers.next()){
				fileLogger.warn("User Already Exists");
				return "UserExists";
			} else if(Username.length() >= 255 || Forename.length() >= 255 || Surname.length() >= 255 || Address.length() >= 255 || Password.length() >= 255){
				fileLogger.warn("Character Length over 255");
				return "CharLimit";
			} else if(Username.trim().isEmpty() || Forename.trim().isEmpty() || Surname.trim().isEmpty() || Address.trim().isEmpty() || Password.trim().isEmpty()){
				return "BlankChar";
			}else {
				String insertRecord = "INSERT INTO clients (Username,Forename,Surname,Address) VALUES (?,?,?,?)"; //Prepare SQL query seperatley so user doesnt have direct access to Statement
				PreparedStatement insertStatement = conn.prepareStatement(insertRecord); //Python cursor essentially
				insertStatement.setString(1, Username);
				insertStatement.setString(2, Forename);
				insertStatement.setString(3, Surname);
				insertStatement.setString(4, Address);
				insertStatement.executeUpdate();
				fileLogger.info("Adding Customer Details");
				
				String insertDetails= "INSERT INTO userPasswords VALUES (?,?)";
				PreparedStatement passwStatement = conn.prepareStatement(insertDetails); //Python cursor essentially
				passwStatement.setString(1, Username);
				passwStatement.setInt(2, hash.GetHash(Password));
				passwStatement.executeUpdate();
				fileLogger.info("Adding Customer Password");
				conn.close();
				fileLogger.warn("User Created!");
				return "UserCreated";
				
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			fileLogger.fatal("DATABSE ERROR " + e);
			return "DB Error";
			}
	}

	public boolean AuthorizeLogin(String[] attempt){
		fileLogger.info("Authorizing Login Attempt");
		
		String AttemptedUsername = attempt[0];
		String AttemptedPassword = attempt[1];

		try {
			fileLogger.info("Connecting To DB");
			Class.forName("com.mysql.cj.jdbc.Driver");		//Grabs Driiver 
			Connection conn = DriverManager.getConnection(url,username,passw); //Gets the Connection
			
			
			
			String query = "SELECT Username FROM userPasswords WHERE passw = ? AND Username = ?"; //Prepare SQL query seperatley so user doesnt have direct access to Statement
			PreparedStatement preparedStatement = conn.prepareStatement(query); //Python cursor essentially
			preparedStatement.setString(1, AttemptedPassword);
			preparedStatement.setString(2, AttemptedUsername);//Insert the entered password into the string
			ResultSet dbresult = preparedStatement.executeQuery(); //Execute the string
			

			
			while (dbresult.next()) { // Move the cursor to the first row
				System.out.println(dbresult.getString("Username"));
	        	conn.close();
	        	return true;
		    }
			conn.close();
			return false;
			
			
		} catch (ClassNotFoundException | SQLException e) {
			fileLogger.fatal("DATABSE ERROR " + e);
		}
		return false;
	}
}




