package dao.Interface;

import java.util.List;

import BusinessObjects.Team;

public interface TeamDaoIF {

	final String TABLENAME = "team";

	// Collumnnames of Databasetable
	final String COL_TEAM_ID = "Team_ID";
	final String COL_NAME = "Teamname";
	final String COL_CLUB = "Club_ID";
	final String COL_LEADER = "User_ID";

	
	//Queries
	final String Q_SELECTALLTEAMS = "SELECT * FROM " +TABLENAME;
	final String Q_SELECTBYTEAMID = "SELECT * FROM "+TABLENAME+ " WHERE "+COL_TEAM_ID+" = ? ;";
	final String Q_UPDATETEAM = "UPDATE "+TABLENAME+" SET "+COL_NAME+"=?, "+COL_CLUB+"=?, "+COL_LEADER+"=? WHERE "+ COL_TEAM_ID+"=?;";
	final String Q_DELETETEAM = "DELETE FROM "+TABLENAME+" WHERE "+COL_TEAM_ID+"=?";
	final String Q_INSERTTEAM = "INSERT INTO "+TABLENAME+"("+COL_NAME+","+COL_CLUB+","+COL_LEADER+") VALUES (?,?,?)";

	public List<Team> getAllTeams();

	public Team getTeamById(int team_id);

	public boolean updateTeam(Team team);

	public boolean deleteTeam(Team team);

	public boolean insertTeam(Team team);

}
