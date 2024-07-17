package MainPack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
	static Logger fileLogger = LogManager.getLogger("FileLogLogger");
	public static void main(String[] args) {
		
//		String[] data = {"2","John","Doe","103 Medium Street"};
//		DB RootConn = new DB();
//		RootConn.FetchAllClients();
//		RootConn.InsertNewRec(data);
//		RootConn.FetchAllClients();
		
		fileLogger.info("Creating LoginWindow");
		loginwindow loginwind = new loginwindow();
		loginwind.main();
	}

}
