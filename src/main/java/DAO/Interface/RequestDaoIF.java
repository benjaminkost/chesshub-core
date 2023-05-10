package DAO.Interface;

import java.util.List;
import BusinessObjects.Request;

public interface RequestDaoIF {
	
	final String TABLENAME = "Requests";

	// Collumnnames of Databasetable
	final String COL_REQUEST_ID = "Request_ID";
	final String COL_SENDER_ID = "Sender_ID";
	final String COL_RECIPIENT_ID = "Recipient_ID";
	final String COL_GAME_ID = "Game_ID";
	final String COL_STATUS = "Status";

	// Queries
	final String Q_SELECTALLREQUESTS = "SELECT * FROM " + TABLENAME+";";
	final String Q_SELECTBYREQUESTID = "SELECT * FROM " + TABLENAME + " WHERE " + COL_REQUEST_ID + "= ? ;";
	final String Q_SELECTBYSENDERID = "SELECT * FROM " + TABLENAME + " WHERE " + COL_SENDER_ID + "= ? ;";
	final String Q_SELECTBYRECIPIENTID = "SELECT * FROM " + TABLENAME + " WHERE " + COL_RECIPIENT_ID + "= ? AND " + COL_STATUS +"= 'I think I was your opponent, could you please confirm.';";
	final String Q_UPDATEREQUEST = "UPDATE " + TABLENAME + " SET " + COL_STATUS + "=? WHERE " + COL_REQUEST_ID + "=?;";
	final String Q_DELETEREQUEST = "DELETE FROM " + TABLENAME + " WHERE " + COL_REQUEST_ID + "=?;";
	final String Q_INSERTREQUEST = "INSERT INTO "+TABLENAME+"("+COL_SENDER_ID+","+COL_RECIPIENT_ID+","+COL_GAME_ID+","+COL_STATUS+") VALUES (?,?,?,?);";

	public List<Request> getAllRequests();
	
	public List<Request> getRequestsBySenderId(int userId);
	
	public List<Request> getRequestsByRecipientId(int userId);

	public Request getRequestById(int request_id);

	public boolean updateRequest(int request_id, String status);

	public boolean deleteRequest(int request_id);

	public boolean insertRequest(int sender_ID, int recipient_ID, int game_ID, String status);

}
