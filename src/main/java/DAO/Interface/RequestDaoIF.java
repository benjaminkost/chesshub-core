package DAO.Interface;

import java.util.List;
import BusinessObjects.Request;

public interface RequestDaoIF {
	
	 String TABLENAME = "requests";

	// Collumnnames of Databasetable
	String COL_REQUEST_ID = "Request_ID";
	String COL_SENDER_ID = "Sender_ID";
	String COL_RECIPIENT_ID = "Recipient_ID";
	String COL_GAME_ID = "Game_ID";
	String COL_STATUS = "Status";

	// Queries
	String Q_SELECTALLREQUESTS = "SELECT * FROM " + TABLENAME+";";
	String Q_SELECTBYREQUESTID = "SELECT * FROM " + TABLENAME + " WHERE " + COL_REQUEST_ID + "= ? ;";
	String Q_SELECTBYSENDERID = "SELECT * FROM " + TABLENAME + " WHERE " + COL_SENDER_ID + "= ? ;";
	String Q_SELECTBYRECIPIENTID = "SELECT * FROM " + TABLENAME + " WHERE " + COL_RECIPIENT_ID + "= ? AND " + COL_STATUS +"= 'I think I was your opponent, could you please confirm.';";
	String Q_UPDATEREQUEST = "UPDATE " + TABLENAME + " SET " + COL_STATUS + "=? WHERE " + COL_REQUEST_ID + "=?;";
	String Q_DELETEREQUEST = "DELETE FROM " + TABLENAME + " WHERE " + COL_REQUEST_ID + "=?;";
	String Q_INSERTREQUEST = "INSERT INTO "+TABLENAME+"("+COL_SENDER_ID+","+COL_RECIPIENT_ID+","+COL_GAME_ID+","+COL_STATUS+") VALUES (?,?,?,?);";

	List<Request> getAllRequests();
	
	List<Request> getRequestsBySenderId(int userId);
	
	List<Request> getRequestsByRecipientId(int userId);

	Request getRequestById(int request_id);

	boolean updateRequest(int request_id, String status);

	boolean deleteRequest(int request_id);

	boolean insertRequest(int sender_ID, int recipient_ID, int game_ID, String status);
}
