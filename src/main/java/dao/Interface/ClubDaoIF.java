package dao.Interface;

import java.util.List;

import BusinessObjects.Club;

public interface ClubDaoIF {

	final String TABLENAME = "club";

	// Collumnnames of Databasetable
	final String COL_CLUB_ID = "CLUB_ID";
	final String COL_NAME = "Name";

	
	//Queries
	final String Q_SELECTALLCLUBS = "SELECT * FROM " +TABLENAME;
	final String Q_SELECTBYCLUBID = "SELECT * FROM "+TABLENAME+ " WHERE "+COL_CLUB_ID+" = ? ;";
	final String Q_UPDATECLUB = "UPDATE "+TABLENAME+" SET "+COL_NAME+"=? WHERE "+ COL_CLUB_ID+"=?;";
	final String Q_DELETECLUB = "DELETE FROM "+TABLENAME+" WHERE "+COL_CLUB_ID+"=?";
	final String Q_INSERTCLUB = "INSERT INTO "+TABLENAME+"("+COL_NAME+") VALUES (?)";

	public List<Club> getAllClubs();

	public Club getClubById(int club_id);

	public boolean updateClub(Club club);

	public boolean deleteClub(int club_id);

	public boolean insertClub(Club Club);

}
