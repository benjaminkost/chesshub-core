package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector implements DatabaseConnectorIF{
    
    private static DatabaseConnector instance;
    private Connection connection;
    private PreparedStatement statement;

    private DatabaseConnector() {
    	try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public static synchronized DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }
    
    public PreparedStatement getStatement() {
		return statement;
	}

	public void setStatement(PreparedStatement statement) {
		this.statement = statement;
	}

	public String getHost() {
		return HOST;
	}



	public String getDatabaseName() {
		return DATABASENAME;
	}


	public String getUsername() {
		return DBUSER;
	}

	public String getPassword() {
		return DBPW;
	}



    public synchronized void connect() throws SQLException {
        String url = "jdbc:mariadb://" + HOST + "/" + DATABASENAME; //unsicher ob das richtig ist
        connection = DriverManager.getConnection(url, DBUSER, DBPW);
        System.out.println(connection);
        System.out.println(url +" "+DBUSER+" "+DBPW);
    }

    public synchronized ResultSet executeQuery(String query, Object... params) throws SQLException {
        statement = null;
        try {
            statement = connection.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeQuery();
        } finally {

    }}

    public synchronized Connection getConnection() {
        return connection;
    }

    public synchronized int executeUpdate(String query, Object... params) throws SQLException {
        statement = null;
        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeUpdate();
        } finally {
        }
    }
    
	public synchronized void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
	
	public synchronized void closeStatement() throws SQLException {
        if (statement != null) {
        	statement.close();
        }
    }
    }
