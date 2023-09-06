package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The DatabaseConnector class is responsible for connecting to the database,
 * executing queries, and managing the database connection.
 */
public class DatabaseConnector implements IDatabaseConnector {

    private static DatabaseConnector instance;
    private Connection connection;
    private PreparedStatement statement;

    /**
     * Private constructor to restrict the instantiation of the class.
     */
    private DatabaseConnector() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the singleton instance of the DatabaseConnector.
     *
     * @return The DatabaseConnector instance.
     */
    public static synchronized DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }

    /**
     * Retrieves the prepared statement.
     *
     * @return The prepared statement.
     */
    public PreparedStatement getStatement() {
        return statement;
    }

    /**
     * Sets the prepared statement.
     *
     * @param statement The prepared statement.
     */
    public void setStatement(PreparedStatement statement) {
        this.statement = statement;
    }

    /**
     * Retrieves the host of the database.
     *
     * @return The host of the database.
     */
    public String getHost() {
        return HOST;
    }

    /**
     * Retrieves the name of the database.
     *
     * @return The name of the database.
     */
    public String getDatabaseName() {
        return DATABASENAME;
    }

    /**
     * Retrieves the username for the database connection.
     *
     * @return The username for the database connection.
     */
    public String getUsername() {
        return DBUSER;
    }

    /**
     * Retrieves the password for the database connection.
     *
     * @return The password for the database connection.
     */
    public String getPassword() {
        return DBPW;
    }

    /**
     * Establishes a connection to the database.
     *
     * @throws SQLException if a database access error occurs.
     */
    public synchronized void connect() throws SQLException {
        String url = "jdbc:mariadb://" + HOST + "/" + DATABASENAME;
        connection = DriverManager.getConnection(url, DBUSER, DBPW);
    }

    /**
     * Executes a query on the database and returns the result set.
     *
     * @param query  The SQL query to be executed.
     * @param params The parameters to be set in the prepared statement.
     * @return The result set of the query.
     * @throws SQLException if a database access error occurs.
     */
    public synchronized ResultSet executeQuery(String query, Object... params) throws SQLException {
        statement = null;
        try {
            statement = connection.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeQuery();
        } finally {
            // Perform any necessary cleanup or resource release here
        }
    }

    /**
     * Retrieves the connection to the database.
     *
     * @return The database connection.
     */
    public synchronized Connection getConnection() {
        return connection;
    }

    /**
     * Executes an update statement on the database.
     *
     * @param query  The SQL query to be executed.
     * @param params The parameters to be set in the prepared statement.
     * @return The number of rows affected by the update.
     * @throws SQLException if a database access error occurs.
     */
    public synchronized int executeUpdate(String query, Object... params) throws SQLException {
        statement = null;
        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeUpdate();
        } finally {
            // Perform any necessary cleanup or resource release here
        }
    }

    /**
     * Closes the database connection.
     *
     * @throws SQLException if a database access error occurs.
     */
    public synchronized void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * Closes the prepared statement.
     *
     * @throws SQLException if a database access error occurs.
     */
    public synchronized void closeStatement() throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }
}
