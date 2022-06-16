package de.senatov.reservatio.test;


import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.api.VersionRestControllerApi;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.util.LinkedHashMap;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
@Slf4j
public class RestOnlineTest {

    final private static String msg = "Exception when calling VersionRestControllerApi#versionInformation\n Status code: %s \n Reason: %s \n Response headers:%s";

    @Test
    public void checkOpenApiTest() throws Exception {
        log.debug("Online test. For check OPEN-API implementation");
        URL url = new URL(Configuration.getDefaultApiClient().getBaseUri());
        if (!isPortavailable(url)) {
            log.debug("Ok. Server is offline. Is ok. This test will be ignored. ");
            return;
        }
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        VersionRestControllerApi apiInstance = new VersionRestControllerApi(defaultClient);
        try {
            LinkedHashMap result = (LinkedHashMap) apiInstance.versionInformation();
            String out = Joiner.on(",").withKeyValueSeparator("=").join(result);
            assertNotNull(out);
            log.debug(out);
        } catch (ApiException e) {
            log.debug(format(msg, e.getCode(), e.getResponseBody(), e.getResponseHeaders()));
            throw new Exception(e);
        }
    }

    /**
     * Checks to see if a specific port is available.
     *
     * @param url the host is available
     */
    private boolean isPortavailable(URL url) throws Exception {
        log.debug("check if port open");
        boolean isOnline = true;
        Socket socket;
        SocketAddress sockaddr = new InetSocketAddress(url.getHost(), url.getPort());
        socket = new Socket();
        try {
            socket.connect(sockaddr, 10000);
        } catch (IOException IOException) {
            log.warn("Server is down down down.. No Test possible");
            isOnline = false;
        } finally {
            if (!socket.isClosed()) {
                socket.close();
            }
        }
        if (isOnline) {
            log.debug("ONLINE");
        }
        return isOnline;
    }
}