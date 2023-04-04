package dao.Interface;

import java.util.List;

import BusinessObjects.Authorisation;

public interface AuthDaoIF {

	final String TABLENAME = "authorisation";

	// Collumnnames of Databasetable
	final String COL_AUTH_ID = "Auth_ID";
	final String COL_ROLE = "Role";

	// Queries
	final String Q_SELECTALLAUTHS = "SELECT * FROM " + TABLENAME;
	final String Q_SELECTBYAUTHID = "SELECT * FROM " + TABLENAME + " WHERE " + COL_AUTH_ID + "= ? ;";
	final String Q_UPDATEAUTH = "UPDATE " + TABLENAME + " SET " + COL_ROLE + "=? WHERE " + COL_AUTH_ID + "=?;";
	final String Q_DELETEAUTH = "DELETE FROM " + TABLENAME + " WHERE " + COL_AUTH_ID + "=?";
	final String Q_INSERTAUTH = "INSERT INTO " + TABLENAME + "(" + COL_ROLE + ") VALUES (?)";

	public List<Authorisation> getAllAuths();

	public Authorisation getAuthById(int auth_id);

	public boolean updateAuth(Authorisation auth);

	public boolean deleteAuth(int auth_id);

	public boolean insertAuth(Authorisation Auth);

}
