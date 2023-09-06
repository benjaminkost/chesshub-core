package database;

public interface DatabaseConnectorIF {

	String HOST = "localhost";
	//String HOST = "mysql1:3306";
	String DATABASENAME = "ChessManagement";
	String DBUSER = "root";
	String DBPW = "root";

	/**
	Vorbereitung für Ersetzung der Hardcredentials
	Ansatz: https://chat.openai.com/share/3cc7a8cd-1fbf-4731-8a62-93db74c51a0e

	 @author Lukas Zander
	 */

	//String DBUSER = System.getenv("DB_USERNAME");
	//String DBPW = System.getenv("DB_PASSWORD");

}
