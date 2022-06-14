## Spring Boot Integration example

- example code.
- `The Project builds under Oracle JDK17 General-Availability Release`

## Tags

---

> MS Zulu JDK 18.01 <br>
> JPA/Hibernate <br>
> Postgres:latest under Docker<br>
> Spring-Boot 2.6.2<br>
> JSF2 Mojarra <br>
> PrimeFaces 11.0.0<br>

---

- implementation of Basis Primefaces Scheduler.
- Components: MS Zulu JDK 18.01, JPA/Hibernate, Docker + Postgres, Spring-Boot 2.6.2, JSF 2.4.0 Mojarra, PrimeFaces
  11.0.0, OpenAPI,~Spring Security~
- URLs: [http://localhost:18087/ui/reservation.xhtml](http://localhost:18087/ui/reservation.xhtml)
- GITH Info from Rest WS: [http://localhost:18087/version](http://localhost:18087/version)

---

**[Start Link](http://localhost:18087/ui/reservation.xhtml)**.

common programm test (DB connect, Boot, JSF connection) is helpful on <br>
refactoring and pom version changing

**[EMail:javaentwickler@gmail.com](mailto://javaentwickler@gmail.com)**

---

![image1](doc/reservation.png "Image #1")
![image1](doc/list.png "Image #2")
![image1](doc/cewate.png "Image #3")

All URIs are relative to *http://localhost:18087*

Method | HTTP request | Description
------------- | ------------- | -------------
[**versionInformation**](VersionRestControllerApi.md#versionInformation) | **GET** /version/ |

<a name="versionInformation"></a>

# **versionInformation**

> String versionInformation()

### Example Call Open API Service:

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

