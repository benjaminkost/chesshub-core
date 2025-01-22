# UserApi

All URIs are relative to *http://localhost/benchesster.ben_kostka.de*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createUser**](UserApi.md#createUser) | **POST** /user | Create user |
| [**createUsersWithListInput**](UserApi.md#createUsersWithListInput) | **POST** /user/createWithList | Creates list of users with given input array |
| [**deleteUser**](UserApi.md#deleteUser) | **DELETE** /user/{user_ID} | Delete user |
| [**getUserByName**](UserApi.md#getUserByName) | **GET** /user/{user_ID} | Get user by user name |
| [**loginUser**](UserApi.md#loginUser) | **GET** /user/login | Logs user into the system |
| [**logoutUser**](UserApi.md#logoutUser) | **GET** /user/logout | Logs out current logged in user session |
| [**updateUser**](UserApi.md#updateUser) | **PUT** /user/{user_ID} | Update user |



## createUser

> User createUser(user)

Create user

This can only be done by the logged in user.

### Example

```java
// Import classes:
import de.ben_kostka.benchesster.client.ApiClient;
import de.ben_kostka.benchesster.client.ApiException;
import de.ben_kostka.benchesster.client.Configuration;
import de.ben_kostka.benchesster.client.model.*;
import de.ben_kostka.benchesster.client.api.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost/benchesster.ben_kostka.de");

        UserApi apiInstance = new UserApi(defaultClient);
        User user = new User(); // User | Created user object
        try {
            User result = apiInstance.createUser(user);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#createUser");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **user** | [**User**](User.md)| Created user object | [optional] |

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json, application/xml, application/x-www-form-urlencoded
- **Accept**: application/json, application/xml

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **0** | successful operation |  -  |


## createUsersWithListInput

> User createUsersWithListInput(user)

Creates list of users with given input array

Creates list of users with given input array

### Example

```java
// Import classes:
import de.ben_kostka.benchesster.client.ApiClient;
import de.ben_kostka.benchesster.client.ApiException;
import de.ben_kostka.benchesster.client.Configuration;
import de.ben_kostka.benchesster.client.model.*;
import de.ben_kostka.benchesster.client.api.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost/benchesster.ben_kostka.de");

        UserApi apiInstance = new UserApi(defaultClient);
        List<User> user = Arrays.asList(); // List<User> | 
        try {
            User result = apiInstance.createUsersWithListInput(user);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#createUsersWithListInput");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **user** | [**List&lt;User&gt;**](User.md)|  | [optional] |

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/xml, application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful operation |  -  |
| **0** | successful operation |  -  |


## deleteUser

> deleteUser(userID)

Delete user

This can only be done by the logged in user.

### Example

```java
// Import classes:
import de.ben_kostka.benchesster.client.ApiClient;
import de.ben_kostka.benchesster.client.ApiException;
import de.ben_kostka.benchesster.client.Configuration;
import de.ben_kostka.benchesster.client.model.*;
import de.ben_kostka.benchesster.client.api.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost/benchesster.ben_kostka.de");

        UserApi apiInstance = new UserApi(defaultClient);
        Integer userID = 56; // Integer | The name that needs to be fetched. Use user1 for testing. 
        try {
            apiInstance.deleteUser(userID);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#deleteUser");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **userID** | **Integer**| The name that needs to be fetched. Use user1 for testing.  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Invalid user id supplied |  -  |
| **404** | User not found |  -  |


## getUserByName

> User getUserByName(userID)

Get user by user name



### Example

```java
// Import classes:
import de.ben_kostka.benchesster.client.ApiClient;
import de.ben_kostka.benchesster.client.ApiException;
import de.ben_kostka.benchesster.client.Configuration;
import de.ben_kostka.benchesster.client.model.*;
import de.ben_kostka.benchesster.client.api.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost/benchesster.ben_kostka.de");

        UserApi apiInstance = new UserApi(defaultClient);
        Long userID = 56L; // Long | The name that needs to be fetched. Use user1 for testing. 
        try {
            User result = apiInstance.getUserByName(userID);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#getUserByName");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **userID** | **Long**| The name that needs to be fetched. Use user1 for testing.  | |

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/xml, application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | successful operation |  -  |
| **400** | Invalid user id supplied |  -  |
| **404** | User not found |  -  |


## loginUser

> String loginUser(username, password)

Logs user into the system



### Example

```java
// Import classes:
import de.ben_kostka.benchesster.client.ApiClient;
import de.ben_kostka.benchesster.client.ApiException;
import de.ben_kostka.benchesster.client.Configuration;
import de.ben_kostka.benchesster.client.model.*;
import de.ben_kostka.benchesster.client.api.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost/benchesster.ben_kostka.de");

        UserApi apiInstance = new UserApi(defaultClient);
        String username = "username_example"; // String | The user name for login
        String password = "password_example"; // String | The password for login in clear text
        try {
            String result = apiInstance.loginUser(username, password);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#loginUser");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **username** | **String**| The user name for login | [optional] |
| **password** | **String**| The password for login in clear text | [optional] |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/xml, application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | successful operation |  * X-Rate-Limit - calls per hour allowed by the user <br>  * X-Expires-After - date in UTC when token expires <br>  |
| **400** | Invalid username/password supplied |  -  |


## logoutUser

> logoutUser()

Logs out current logged in user session



### Example

```java
// Import classes:
import de.ben_kostka.benchesster.client.ApiClient;
import de.ben_kostka.benchesster.client.ApiException;
import de.ben_kostka.benchesster.client.Configuration;
import de.ben_kostka.benchesster.client.model.*;
import de.ben_kostka.benchesster.client.api.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost/benchesster.ben_kostka.de");

        UserApi apiInstance = new UserApi(defaultClient);
        try {
            apiInstance.logoutUser();
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#logoutUser");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **0** | successful operation |  -  |


## updateUser

> updateUser(userID, user)

Update user

This can only be done by the logged in user.

### Example

```java
// Import classes:
import de.ben_kostka.benchesster.client.ApiClient;
import de.ben_kostka.benchesster.client.ApiException;
import de.ben_kostka.benchesster.client.Configuration;
import de.ben_kostka.benchesster.client.model.*;
import de.ben_kostka.benchesster.client.api.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost/benchesster.ben_kostka.de");

        UserApi apiInstance = new UserApi(defaultClient);
        Integer userID = 56; // Integer | The name that needs to be fetched. Use user1 for testing. 
        User user = new User(); // User | Update an existent user in the store
        try {
            apiInstance.updateUser(userID, user);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#updateUser");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **userID** | **Integer**| The name that needs to be fetched. Use user1 for testing.  | |
| **user** | [**User**](User.md)| Update an existent user in the store | [optional] |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json, application/xml, application/x-www-form-urlencoded
- **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **0** | successful operation |  -  |

