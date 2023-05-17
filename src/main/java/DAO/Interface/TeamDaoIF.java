package DAO.Interface;

import java.util.List;

import BusinessObjects.Team;

public interface TeamDaoIF {

	String TABLENAME = "team";

	// Collumnnames of Databasetable
	String COL_TEAM_ID = "Team_ID";
	String COL_NAME = "Teamname";
	String COL_CLUB = "Club_ID";
	String COL_LEADER = "User_ID";

	
	//Queries
	String Q_SELECTALLTEAMS = "SELECT * FROM " +TABLENAME+";";
	String Q_SELECTBYTEAMID = "SELECT * FROM "+TABLENAME+ " WHERE "+COL_TEAM_ID+" = ? ;";
	String Q_UPDATETEAM = "UPDATE "+TABLENAME+" SET "+COL_NAME+"=?, "+COL_CLUB+"=?, "+COL_LEADER+"=? WHERE "+ COL_TEAM_ID+"=?;";
	String Q_DELETETEAM = "DELETE FROM "+TABLENAME+" WHERE "+COL_TEAM_ID+"=?;";
	String Q_INSERTTEAM = "INSERT INTO "+TABLENAME+"("+COL_NAME+","+COL_CLUB+","+COL_LEADER+") VALUES (?,?,?);";

	List<Team> getAllTeams();

	Team getTeamById(int team_id);

	boolean updateTeam(Team team);

	boolean deleteTeam(Team team);

	boolean insertTeam(Team team);
}
