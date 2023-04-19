package dao.Interface;

import java.util.List;

import BusinessObjects.Team;
import BusinessObjects.User;

public interface UserTeamDaoIF {

	final String TABLENAME = "User_has_Teams";
	// Collumnames
	final String COL_USER_ID = "User_ID";
	final String COL_TEAM_ID = "Team_ID";

//	 Queries
	final String Q_SELECTALLUSERTEAMS = "SELECT * FROM " + TABLENAME+";";
	final String Q_SELECTTEAMSBYUSERID = "SELECT * FROM " + TABLENAME + " WHERE " + COL_USER_ID + " = ? ;";
	final String Q_SELECTUSERSBYTEAMID = "SELECT * FROM " + TABLENAME + " WHERE " + COL_TEAM_ID + " = ? ;";
	final String Q_UPDATEUSERTEAM = "UPDATE " + TABLENAME + " SET " + COL_TEAM_ID + "=? WHERE " + COL_USER_ID
			+ "=? AND " + COL_TEAM_ID + "=?;";
	final String Q_DELETEUSERFROMTEAM = "DELETE FROM " + TABLENAME + " WHERE " + COL_USER_ID + "=? AND " + COL_TEAM_ID
			+ "=?;";
	final String Q_INSERTUSERINTEAM = "INSERT INTO " + TABLENAME + "(" + COL_USER_ID + "," + COL_TEAM_ID
			+ ") VALUES (?,?);";

	public List<Team> getTeamsByUserId(User user);
	
	public List<User> getUsersByTeamId(Team team);

	public boolean updateUserInTeam(User user, Team fromTeam, Team toTeam);

	public boolean deleteUserFromTeam(User user, Team team);

	public boolean insertUserInTeam(User user, Team team);

}
