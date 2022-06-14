# VersionRestControllerApi

All URIs are relative to *http://localhost:18087*

Method | HTTP request | Description
------------- | ------------- | -------------
[**versionInformation**](VersionRestControllerApi.md#versionInformation) | **GET** /version/ |

<a name="versionInformation"></a>

# **versionInformation**

> String versionInformation()

### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.VersionRestControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:18087");

    VersionRestControllerApi apiInstance = new VersionRestControllerApi(defaultClient);
    try {
      String result = apiInstance.versionInformation();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling VersionRestControllerApi#versionInformation");
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
- **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

