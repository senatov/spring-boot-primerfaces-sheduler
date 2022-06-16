package de.senatov.reservatio.test;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.api.VersionRestControllerApi;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.URL;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class OnlineTest {

    String msg = "Exception when calling VersionRestControllerApi#versionInformation\n Status code: %s \n Reason: %s \n Response headers:%s";

    @Test
    public void checkOpenApiTest() throws Exception {
        log.debug("Online test. For check OPEN-API implementation");
        int port = new URL(Configuration.getDefaultApiClient().getBaseUri()).getPort();
        if (!isPortavailable(port)) {
            log.debug("Ok. Application isn't online. Is ok. This test will be ignored. ");
            return;
        }
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        VersionRestControllerApi apiInstance = new VersionRestControllerApi(defaultClient);
        try {
            String result = apiInstance.versionInformation();
            log.debug(result);
            assertNotNull(result);
        } catch (ApiException e) {
            log.debug(format(msg, e.getCode(), e.getResponseBody(), e.getResponseHeaders()));
            throw new Exception(e);
        }
    }

    /**
     * Checks to see if a specific port is available.
     *
     * @param port the port to check for availability
     */
    private static boolean isPortavailable(int port) {
        log.debug("check if port open");
        ServerSocket srvSocket = null;
        DatagramSocket dataGrammSocket = null;
        try {
            srvSocket = new ServerSocket(port);
            srvSocket.setReuseAddress(true);
            dataGrammSocket = new DatagramSocket(port);
            dataGrammSocket.setReuseAddress(true);
            return true;
        } catch (IOException e) {
        } finally {
            if (dataGrammSocket != null) {
                dataGrammSocket.close();
            }
            if (srvSocket != null) {
                try {
                    srvSocket.close();
                } catch (IOException e) {
                    // should not be thrown
                }
            }
        }
        return false;
    }
}