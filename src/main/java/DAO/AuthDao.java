package DAO;

import java.util.ArrayList;

import java.util.List;

import BusinessObjects.Authorisation;

import DAO.Interface.AuthDaoIF;

import Database.DatabaseConnector;
import Database.DatabaseConnectorIF;


import java.sql.*;

public class AuthDao implements AuthDaoIF, DatabaseConnectorIF {

	private static AuthDao instance;

	private AuthDao() {
		try {
			DatabaseConnector.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static AuthDao getInstance() {
		if (instance == null) {
			instance = new AuthDao();
		}
		return instance;
	}

	@Override
	public List<Authorisation> getAllAuths() {
		List<Authorisation> authList = new ArrayList<Authorisation>();
		try {
			ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTALLAUTHS);
			while (rs.next()) {
				Authorisation auth = new Authorisation();
				auth.setAuth_ID(rs.getInt(COL_AUTH_ID));
				authList.add(auth);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			try {
				DatabaseConnector.getInstance().closeStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return authList;
	}

	@Override
	public Authorisation getAuthById(int auth_id) {
		Authorisation auth = new Authorisation();
		try {
			ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTBYAUTHID, auth_id);
			if (rs.next()) {
				auth.setAuth_ID(rs.getInt(COL_AUTH_ID));
				auth.setRole(rs.getString(COL_ROLE));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DatabaseConnector.getInstance().closeStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return auth;
	}

	@Override
	public boolean updateAuth(Authorisation Auth) {
		boolean result = false;
		try {
			if (DatabaseConnector.getInstance().executeUpdate(Q_UPDATEAUTH, Auth.getRole(), Auth.getAuth_ID()) > 0) {
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DatabaseConnector.getInstance().closeStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean deleteAuth(Authorisation auth) {
		boolean result = false;
		try {
			if (DatabaseConnector.getInstance().executeUpdate(Q_DELETEAUTH, auth.getAuth_ID()) > 0) {
				result = true;
				auth = null;
				
			} else {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DatabaseConnector.getInstance().closeStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean insertAuth(Authorisation Auth) {
		boolean result = false;
		try {
			if (DatabaseConnector.getInstance().executeUpdate(Q_INSERTAUTH, Auth.getRole()) > 0) {
				ResultSet rs = DatabaseConnector.getInstance().getStatement().getGeneratedKeys();
				if (rs.next()) {
					Auth.setAuth_ID(rs.getInt(1));
				}
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DatabaseConnector.getInstance().closeStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
