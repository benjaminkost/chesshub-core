package DAO.Interface;

import java.util.List;

import BusinessObjects.Club;

public interface ClubDaoIF {

	String TABLENAME = "club";

	// Collumnnames of Databasetable
	String COL_CLUB_ID = "Club_ID";
	String COL_NAME = "Clubname";
	String COL_PRESIDENT = "User_ID";

	
	//Queries
	String Q_SELECTALLCLUBS = "SELECT * FROM " +TABLENAME+";";
	String Q_SELECTBYCLUBID = "SELECT * FROM "+TABLENAME+" WHERE "+ COL_CLUB_ID+"=?;";
	String Q_UPDATECLUB = "UPDATE "+TABLENAME+" SET "+COL_NAME+"=?, "+COL_PRESIDENT+"=? WHERE "+ COL_CLUB_ID+"=?;";
	String Q_DELETECLUB = "DELETE FROM "+TABLENAME+" WHERE "+COL_CLUB_ID+"=?;";
	String Q_INSERTCLUB = "INSERT INTO "+TABLENAME+"("+COL_NAME+","+COL_PRESIDENT+") VALUES (?,?);";

	List<Club> getAllClubs();

	Club getClubById(int club_id);

	boolean updateClub(Club club);

	boolean deleteClub(Club club);

	boolean insertClub(Club club);

}
