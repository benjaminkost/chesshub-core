package dao;

import java.util.ArrayList;

import java.util.List;

import BusinessObjects.Club;
import Database.DatabaseConnector;
import Database.DatabaseConnectorIF;
import dao.Interface.ClubDaoIF;

import java.sql.*;

public class ClubDao implements ClubDaoIF, DatabaseConnectorIF {

	private static ClubDao instance;

	private ClubDao() {
		try {
			DatabaseConnector.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ClubDao getInstance() {
		if (instance == null) {
			instance = new ClubDao();
		}
		return instance;
	}

	@Override
	public List<Club> getAllClubs() {
		List<Club> clubList = new ArrayList<Club>();
		try {
			ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTALLCLUBS);
			while (rs.next()) {
				Club club = new Club();
				club.setClub_ID(rs.getInt(COL_CLUB_ID));
				club.setName(rs.getString(COL_NAME));
				club.setPresident(UserDao.getInstance().getUserById(rs.getInt(COL_PRESIDENT)));
				clubList.add(club);

			}
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			try {
				DatabaseConnector.getInstance().closeStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return clubList;
	}

	@Override
	public Club getClubById(int club_id){
		Club club = new Club();
		try {
			ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTBYCLUBID, club_id);
			if (rs.next()) {
				club.setClub_ID(rs.getInt(COL_CLUB_ID));
				club.setName(rs.getString(COL_NAME));
				club.setPresident(UserDao.getInstance().getUserById(rs.getInt(COL_PRESIDENT)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DatabaseConnector.getInstance().closeStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return club;
	}

	@Override
	public boolean updateClub(Club club) {
		boolean result = false;
		try {
			if (DatabaseConnector.getInstance().executeUpdate(Q_UPDATECLUB, club.getName(),club.getPresident(), club.getClub_ID()) > 0) {
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DatabaseConnector.getInstance().closeStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean deleteClub(Club club) {
		boolean result = false;
		try {
			if (DatabaseConnector.getInstance().executeUpdate(Q_DELETECLUB, club.getClub_ID()) > 0) {
				result = true;
				club = null;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				DatabaseConnector.getInstance().closeStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean insertClub(Club Club) {
		boolean result = false;
		try {
			if (DatabaseConnector.getInstance()
					.executeUpdate(Q_INSERTCLUB, Club.getName(), Club.getPresident()) > 0) {
				ResultSet rs = DatabaseConnector.getInstance().getStatement().getGeneratedKeys();
				if (rs.next()) {
					Club.setClub_ID(rs.getInt(1));
				}
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DatabaseConnector.getInstance().closeStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}
