package MainPack;
import  java.security.MessageDigest;

import org.apache.logging.log4j.Logger;

public class ReturnHash {
	private static final Logger fileLogger = Main.fileLogger;
	
	
	public int GetHash(String plain) {
		fileLogger.info("Hashed UserAttempt");
		return (plain.hashCode());
		
		
		
		
	}

}
