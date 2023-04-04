package dao.Interface;

import java.util.List;

import BusinessObjects.Team;

public interface TeamDaoIF {

	final String TABLENAME = "team";

	// Collumnnames of Databasetable
	final String COL_TEAM_ID = "TEAM_ID";
	final String COL_NAME = "Name";

	
	//Queries
	final String Q_SELECTALLTEAMS = "SELECT * FROM " +TABLENAME;
	final String Q_SELECTBYTEAMID = "SELECT * FROM "+TABLENAME+ " WHERE "+COL_TEAM_ID+" = ? ;";
	final String Q_UPDATETEAM = "UPDATE "+TABLENAME+" SET "+COL_NAME+"=? WHERE "+ COL_TEAM_ID+"=?;";
	final String Q_DELETETEAM = "DELETE FROM "+TABLENAME+" WHERE "+COL_TEAM_ID+"=?";
	final String Q_INSERTTEAM = "INSERT INTO "+TABLENAME+"("+COL_NAME+") VALUES (?)";

	public List<Team> getAllTeams();

	public Team getTeamById(int team_id);

	public boolean updateTeam(Team team);

	public boolean deleteTeam(int team_id);

	public boolean insertTeam(Team team);

}
