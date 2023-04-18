package juTest;

import org.junit.*;

import Database.DatabaseConnector;

import static org.junit.Assert.*;
import java.sql.*;

public class DatabaseConnectorTest {
	private static final String CREATE_TABLE_QUERY = "CREATE TABLE test_table(id INTEGER PRIMARY KEY, name VARCHAR(255))";
	private static final String INSERT_QUERY = "INSERT INTO test_table(id, name) VALUES (?, ?)";
	private static final String SELECT_QUERY = "SELECT COUNT(*) FROM test_table";
	private static final String DROP_TABLE_QUERY = "DROP TABLE test_table";

	@Test
	public void testExecuteUpdate() throws SQLException {
		DatabaseConnector.getInstance().connect();
		try {
			DatabaseConnector.getInstance().executeUpdate(CREATE_TABLE_QUERY);
			assertEquals(1, DatabaseConnector.getInstance().executeUpdate(INSERT_QUERY, 1, "John"));
			assertEquals(1, DatabaseConnector.getInstance().executeUpdate(INSERT_QUERY, 2, "Jane"));

			ResultSet resultSet = DatabaseConnector.getInstance().executeQuery(SELECT_QUERY);
			assertTrue(resultSet.next());
			resultSet.close();
		} finally {
			DatabaseConnector.getInstance().executeUpdate(DROP_TABLE_QUERY);
			DatabaseConnector.getInstance().closeConnection();
		}
	}

	@Test
	public void testExecuteQuery() throws SQLException {
		DatabaseConnector.getInstance().connect();
		try {
			DatabaseConnector.getInstance().executeUpdate(CREATE_TABLE_QUERY);
			DatabaseConnector.getInstance().executeUpdate(INSERT_QUERY, 1, "John");
			DatabaseConnector.getInstance().executeUpdate(INSERT_QUERY, 2, "Jane");

			ResultSet resultSet = DatabaseConnector.getInstance().executeQuery("SELECT * FROM test_table");
			assertTrue(resultSet.next());
			assertEquals(1, resultSet.getInt("id"));
			assertEquals("John", resultSet.getString("name"));
			assertTrue(resultSet.next());
			assertEquals(2, resultSet.getInt("id"));
			assertEquals("Jane", resultSet.getString("name"));
			assertFalse(resultSet.next());
			resultSet.close();
		} finally {
			DatabaseConnector.getInstance().executeUpdate(DROP_TABLE_QUERY);
			DatabaseConnector.getInstance().closeConnection();
		}
	}
}
