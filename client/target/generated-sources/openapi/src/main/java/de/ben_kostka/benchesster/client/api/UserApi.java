package de.ben_kostka.benchesster.client.api;

import de.ben_kostka.benchesster.client.ApiException;
import de.ben_kostka.benchesster.client.ApiClient;
import de.ben_kostka.benchesster.client.ApiResponse;
import de.ben_kostka.benchesster.client.Configuration;
import de.ben_kostka.benchesster.client.Pair;

import javax.ws.rs.core.GenericType;

import org.joda.time.DateTime;
import de.ben_kostka.benchesster.client.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-01-27T11:52:59.664272+01:00[Europe/Berlin]", comments = "Generator version: 7.10.0")
public class UserApi {
  private ApiClient apiClient;

  public UserApi() {
    this(Configuration.getDefaultApiClient());
  }

  public UserApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Get the API client
   *
   * @return API client
   */
  public ApiClient getApiClient() {
    return apiClient;
  }

  /**
   * Set the API client
   *
   * @param apiClient an instance of API client
   */
  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Create user
   * This can only be done by the logged in user.
   * @param user Created user object (optional)
   * @return User
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 0 </td><td> successful operation </td><td>  -  </td></tr>
     </table>
   */
  public User createUser(User user) throws ApiException {
    return createUserWithHttpInfo(user).getData();
  }

  /**
   * Create user
   * This can only be done by the logged in user.
   * @param user Created user object (optional)
   * @return ApiResponse&lt;User&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 0 </td><td> successful operation </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<User> createUserWithHttpInfo(User user) throws ApiException {
    String localVarAccept = apiClient.selectHeaderAccept("application/json", "application/xml");
    String localVarContentType = apiClient.selectHeaderContentType("application/json", "application/xml", "application/x-www-form-urlencoded");
    GenericType<User> localVarReturnType = new GenericType<User>() {};
    return apiClient.invokeAPI("UserApi.createUser", "/user", "POST", new ArrayList<>(), user,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               null, localVarReturnType, false);
  }
  /**
   * Creates list of users with given input array
   * Creates list of users with given input array
   * @param user  (optional)
   * @return User
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Successful operation </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> successful operation </td><td>  -  </td></tr>
     </table>
   */
  public User createUsersWithListInput(List<User> user) throws ApiException {
    return createUsersWithListInputWithHttpInfo(user).getData();
  }

  /**
   * Creates list of users with given input array
   * Creates list of users with given input array
   * @param user  (optional)
   * @return ApiResponse&lt;User&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Successful operation </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> successful operation </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<User> createUsersWithListInputWithHttpInfo(List<User> user) throws ApiException {
    String localVarAccept = apiClient.selectHeaderAccept("application/xml", "application/json");
    String localVarContentType = apiClient.selectHeaderContentType("application/json");
    GenericType<User> localVarReturnType = new GenericType<User>() {};
    return apiClient.invokeAPI("UserApi.createUsersWithListInput", "/user/createWithList", "POST", new ArrayList<>(), user,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               null, localVarReturnType, false);
  }
  /**
   * Delete user
   * This can only be done by the logged in user.
   * @param userID The name that needs to be fetched. Use user1 for testing.  (required)
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 400 </td><td> Invalid user id supplied </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> User not found </td><td>  -  </td></tr>
     </table>
   */
  public void deleteUser(Integer userID) throws ApiException {
    deleteUserWithHttpInfo(userID);
  }

  /**
   * Delete user
   * This can only be done by the logged in user.
   * @param userID The name that needs to be fetched. Use user1 for testing.  (required)
   * @return ApiResponse&lt;Void&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 400 </td><td> Invalid user id supplied </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> User not found </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Void> deleteUserWithHttpInfo(Integer userID) throws ApiException {
    // Check required parameters
    if (userID == null) {
      throw new ApiException(400, "Missing the required parameter 'userID' when calling deleteUser");
    }

    // Path parameters
    String localVarPath = "/user/{user_ID}"
            .replaceAll("\\{user_ID}", apiClient.escapeString(userID.toString()));

    String localVarAccept = apiClient.selectHeaderAccept();
    String localVarContentType = apiClient.selectHeaderContentType();
    return apiClient.invokeAPI("UserApi.deleteUser", localVarPath, "DELETE", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               null, null, false);
  }
  /**
   * Get user by user name
   * 
   * @param userID The name that needs to be fetched. Use user1 for testing.  (required)
   * @return User
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 400 </td><td> Invalid user id supplied </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> User not found </td><td>  -  </td></tr>
     </table>
   */
  public User getUserByName(Long userID) throws ApiException {
    return getUserByNameWithHttpInfo(userID).getData();
  }

  /**
   * Get user by user name
   * 
   * @param userID The name that needs to be fetched. Use user1 for testing.  (required)
   * @return ApiResponse&lt;User&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
       <tr><td> 400 </td><td> Invalid user id supplied </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> User not found </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<User> getUserByNameWithHttpInfo(Long userID) throws ApiException {
    // Check required parameters
    if (userID == null) {
      throw new ApiException(400, "Missing the required parameter 'userID' when calling getUserByName");
    }

    // Path parameters
    String localVarPath = "/user/{user_ID}"
            .replaceAll("\\{user_ID}", apiClient.escapeString(userID.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/xml", "application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    GenericType<User> localVarReturnType = new GenericType<User>() {};
    return apiClient.invokeAPI("UserApi.getUserByName", localVarPath, "GET", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               null, localVarReturnType, false);
  }
  /**
   * Logs user into the system
   * 
   * @param username The user name for login (optional)
   * @param password The password for login in clear text (optional)
   * @return String
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  * X-Rate-Limit - calls per hour allowed by the user <br>  * X-Expires-After - date in UTC when token expires <br>  </td></tr>
       <tr><td> 400 </td><td> Invalid username/password supplied </td><td>  -  </td></tr>
     </table>
   */
  public String loginUser(String username, String password) throws ApiException {
    return loginUserWithHttpInfo(username, password).getData();
  }

  /**
   * Logs user into the system
   * 
   * @param username The user name for login (optional)
   * @param password The password for login in clear text (optional)
   * @return ApiResponse&lt;String&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> successful operation </td><td>  * X-Rate-Limit - calls per hour allowed by the user <br>  * X-Expires-After - date in UTC when token expires <br>  </td></tr>
       <tr><td> 400 </td><td> Invalid username/password supplied </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<String> loginUserWithHttpInfo(String username, String password) throws ApiException {
    // Query parameters
    List<Pair> localVarQueryParams = new ArrayList<>(
            apiClient.parameterToPairs("", "username", username)
    );
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "password", password));

    String localVarAccept = apiClient.selectHeaderAccept("application/xml", "application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI("UserApi.loginUser", "/user/login", "GET", localVarQueryParams, null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               null, localVarReturnType, false);
  }
  /**
   * Logs out current logged in user session
   * 
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 0 </td><td> successful operation </td><td>  -  </td></tr>
     </table>
   */
  public void logoutUser() throws ApiException {
    logoutUserWithHttpInfo();
  }

  /**
   * Logs out current logged in user session
   * 
   * @return ApiResponse&lt;Void&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 0 </td><td> successful operation </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Void> logoutUserWithHttpInfo() throws ApiException {
    String localVarAccept = apiClient.selectHeaderAccept();
    String localVarContentType = apiClient.selectHeaderContentType();
    return apiClient.invokeAPI("UserApi.logoutUser", "/user/logout", "GET", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               null, null, false);
  }
  /**
   * Update user
   * This can only be done by the logged in user.
   * @param userID The name that needs to be fetched. Use user1 for testing.  (required)
   * @param user Update an existent user in the store (optional)
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 0 </td><td> successful operation </td><td>  -  </td></tr>
     </table>
   */
  public void updateUser(Integer userID, User user) throws ApiException {
    updateUserWithHttpInfo(userID, user);
  }

  /**
   * Update user
   * This can only be done by the logged in user.
   * @param userID The name that needs to be fetched. Use user1 for testing.  (required)
   * @param user Update an existent user in the store (optional)
   * @return ApiResponse&lt;Void&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 0 </td><td> successful operation </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Void> updateUserWithHttpInfo(Integer userID, User user) throws ApiException {
    // Check required parameters
    if (userID == null) {
      throw new ApiException(400, "Missing the required parameter 'userID' when calling updateUser");
    }

    // Path parameters
    String localVarPath = "/user/{user_ID}"
            .replaceAll("\\{user_ID}", apiClient.escapeString(userID.toString()));

    String localVarAccept = apiClient.selectHeaderAccept();
    String localVarContentType = apiClient.selectHeaderContentType("application/json", "application/xml", "application/x-www-form-urlencoded");
    return apiClient.invokeAPI("UserApi.updateUser", localVarPath, "PUT", new ArrayList<>(), user,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               null, null, false);
  }
}
