package dao.daoInterfaces;

import java.util.List;

import businessObjects.Team;
import businessObjects.User;

public interface IUserTeamDao {

	String TABLENAME = "user_has_Teams";
	// Collumnames
	String COL_USER_ID = "User_ID";
	String COL_TEAM_ID = "Team_ID";

	//	 Queries
	String Q_SELECTALLUSERTEAMS = "SELECT * FROM " + TABLENAME+";";
	String Q_SELECTTEAMSBYUSERID = "SELECT * FROM " + TABLENAME + " WHERE " + COL_USER_ID + " = ? ;";
	String Q_SELECTUSERSBYTEAMID = "SELECT * FROM " + TABLENAME + " WHERE " + COL_TEAM_ID + " = ? ;";
	String Q_UPDATEUSERTEAM = "UPDATE " + TABLENAME + " SET " + COL_TEAM_ID + "=? WHERE " + COL_USER_ID
			+ "=? AND " + COL_TEAM_ID + "=?;";
	String Q_DELETEUSERFROMTEAM = "DELETE FROM " + TABLENAME + " WHERE " + COL_USER_ID + "=? AND " + COL_TEAM_ID
			+ "=?;";
	String Q_INSERTUSERINTEAM = "INSERT INTO " + TABLENAME + "(" + COL_USER_ID + "," + COL_TEAM_ID
			+ ") VALUES (?,?);";

	List<Team> getTeamsByUserId(int userID);
	
	List<User> getUsersByTeamId(Team team);

	boolean updateUserInTeam(User user, Team fromTeam, Team toTeam);

	boolean deleteUserFromTeam(User user, Team team);

	boolean insertUserInTeam(User user, Team team);
}
