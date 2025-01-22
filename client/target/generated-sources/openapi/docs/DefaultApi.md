# DefaultApi

All URIs are relative to *http://localhost/benchesster.ben_kostka.de*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getHome**](DefaultApi.md#getHome) | **GET** /home | Returns the main structure of the homepage with sections |
| [**getRequestedGames**](DefaultApi.md#getRequestedGames) | **GET** /requests/{user_ID} | Get all requested games |
| [**getSettingsoptions**](DefaultApi.md#getSettingsoptions) | **GET** /settings | See options panel |



## getHome

> List&lt;String&gt; getHome()

Returns the main structure of the homepage with sections

### Example

```java
// Import classes:
import de.ben_kostka.benchesster.client.ApiClient;
import de.ben_kostka.benchesster.client.ApiException;
import de.ben_kostka.benchesster.client.Configuration;
import de.ben_kostka.benchesster.client.model.*;
import de.ben_kostka.benchesster.client.api.DefaultApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost/benchesster.ben_kostka.de");

        DefaultApi apiInstance = new DefaultApi(defaultClient);
        try {
            List<String> result = apiInstance.getHome();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#getHome");
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

**List&lt;String&gt;**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful response with the homepage sections |  -  |
| **400** | Bad Request |  -  |
| **500** | Internal Server Error |  -  |


## getRequestedGames

> User getRequestedGames(userID)

Get all requested games

Get all games that are requested to be played with partner

### Example

```java
// Import classes:
import de.ben_kostka.benchesster.client.ApiClient;
import de.ben_kostka.benchesster.client.ApiException;
import de.ben_kostka.benchesster.client.Configuration;
import de.ben_kostka.benchesster.client.model.*;
import de.ben_kostka.benchesster.client.api.DefaultApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost/benchesster.ben_kostka.de");

        DefaultApi apiInstance = new DefaultApi(defaultClient);
        String userID = "userID_example"; // String | The name that needs to be fetched. Use user1 for testing. 
        try {
            User result = apiInstance.getRequestedGames(userID);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#getRequestedGames");
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
| **userID** | **String**| The name that needs to be fetched. Use user1 for testing.  | |

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
| **400** | Invalid username supplied |  -  |
| **404** | User not found |  -  |


## getSettingsoptions

> String getSettingsoptions()

See options panel

Returns all possible settings options

### Example

```java
// Import classes:
import de.ben_kostka.benchesster.client.ApiClient;
import de.ben_kostka.benchesster.client.ApiException;
import de.ben_kostka.benchesster.client.Configuration;
import de.ben_kostka.benchesster.client.model.*;
import de.ben_kostka.benchesster.client.api.DefaultApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost/benchesster.ben_kostka.de");

        DefaultApi apiInstance = new DefaultApi(defaultClient);
        try {
            String result = apiInstance.getSettingsoptions();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#getSettingsoptions");
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

**String**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | successful operation |  -  |
| **400** | Invalid ID supplied |  -  |
| **404** | Account not found |  -  |

