package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BusinessObjects.Request;
import DAO.Interface.RequestDaoIF;
import Database.DatabaseConnector;
import Database.DatabaseConnectorIF;

/**
 * The RequestDao class implements the RequestDaoIF and the DatabaseConnectorIF interfaces
 * and provides methods for performing CRUD operations on the Request table in the database.
 */
public class RequestDao implements RequestDaoIF, DatabaseConnectorIF {

    private static RequestDao instance; //Singleton instance of the ReequestDao class.

    /**
     * Private constructor for the RequestDao class.
     * Initializes the database connection when creating a new instance.
     */
    private RequestDao() {
        try {
            DatabaseConnector.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the Singleton instance of the RequestDao class.
     *
     * @return RequestDao instance.
     */
    public static RequestDao getInstance() {
        if (instance == null) {
            instance = new RequestDao();
        }
        return instance;
    }

    /**
     * Gets all requests from the Request table in the database.
     *
     * @return a list of Request objects
     */
    @Override
    public List<Request> getAllRequests() {
        List<Request> requestList = new ArrayList<Request>();
        try {
            ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTALLREQUESTS);
            while (rs.next()) {
                Request request = new Request();
                request.setRequest_ID(rs.getInt(COL_REQUEST_ID));
                requestList.add(request);
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
        return requestList;
    }

    /**
     * Gets all requests from the Request table in the database that were sent by a specific user.
     *
     * @param userId the ID of the user who sent the requests
     * @return a list of Request objects
     */
    @Override
    public List<Request> getRequestsBySenderId(int userId) {
        List<Request> requestList = new ArrayList<Request>();
        try {
            ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTBYSENDERID, userId);
            while (rs.next()) {
                Request request = new Request();
                request.setRequest_ID(rs.getInt(COL_REQUEST_ID));
                request.setSender_ID(rs.getInt(COL_SENDER_ID));
                request.setRecipient_ID(rs.getInt(COL_RECIPIENT_ID));
                request.setGame_ID(rs.getInt(COL_GAME_ID));
                request.setStatus(rs.getString(COL_STATUS));
                requestList.add(request);
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
        return requestList;
    }

    /**
     * Gets all requests from the Request table in the database that were received by a specific user.
     *
     * @param userId the ID of the user who received the requests
     * @return a list of Request objects
     */
    @Override
    public List<Request> getRequestsByRecipientId(int userId) {
        List<Request> requestList = new ArrayList<Request>();
        try {
            ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTBYRECIPIENTID, userId);
            while (rs.next()) {
                Request request = new Request();
                request.setRequest_ID(rs.getInt(COL_REQUEST_ID));
                request.setSender_ID(rs.getInt(COL_SENDER_ID));
                request.setRecipient_ID(rs.getInt(COL_RECIPIENT_ID));
                request.setGame_ID(rs.getInt(COL_GAME_ID));
                request.setStatus(rs.getString(COL_STATUS));
                requestList.add(request);
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
        return requestList;
    }

    /**
     * Retrieves a Request object by its ID.
     *
     * @param request_id the ID of the Request to retrieve
     * @return the Request object with the given ID
     */
    @Override
    public Request getRequestById(int request_id) {
        Request request = new Request();
        try {
            ResultSet rs = DatabaseConnector.getInstance().executeQuery(Q_SELECTBYREQUESTID, request_id);
            if (rs.next()) {
                request.setRequest_ID(rs.getInt(COL_REQUEST_ID));
                request.setSender_ID(rs.getInt(COL_SENDER_ID));
                request.setRecipient_ID(rs.getInt(COL_RECIPIENT_ID));
                request.setGame_ID(rs.getInt(COL_GAME_ID));
                request.setStatus(rs.getString(COL_STATUS));
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
        return request;
    }

    /**
     * Updates the status of a Request in the database.
     *
     * @param request_id the ID of the Request to update
     * @param status     the new status of the Request
     * @return true if the update was successful, false otherwise
     */
    @Override
    public boolean updateRequest(int request_id, String status) {
        boolean result = false;
        try {
            if (DatabaseConnector.getInstance().executeUpdate(Q_UPDATEREQUEST, status, request_id) > 0) {
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

    /**
     * Deletes a Request from the database.
     *
     * @param request_id
     * @return true if the delete was successful, false otherwise
     */
    @Override
    public boolean deleteRequest(int request_id) {
        boolean result = false;
        try {
            if (DatabaseConnector.getInstance().executeUpdate(Q_DELETEREQUEST, request_id) > 0) {
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

    /**
     * Insert a Request to the database.
     *
     * @param sender_ID
     * @param recipient_ID
     * @param game_ID
     * @param status
     * @return true if the insert was successful, false otherwise
     */
    @Override
    public boolean insertRequest(int sender_ID, int recipient_ID, int game_ID, String status) {
        boolean result = false;
        try {
            if (DatabaseConnector.getInstance().executeUpdate(Q_INSERTREQUEST, sender_ID, recipient_ID, game_ID,
                    status) > 0) {
                ResultSet rs = DatabaseConnector.getInstance().getStatement().getGeneratedKeys();
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException e) {
            //e.printStackTrace();
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
