package de.senatov.reservatio.utl;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.context.PrimeFacesContext;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import static java.lang.String.format;


@Named
@Getter
@Setter
@RequestScoped
@Slf4j
public class InputTextView {

    private String text;

    public String getPath() {
        return "/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config";
    }

    public String getUri() throws UnknownHostException {
        String ret;
        String serverName = Inet4Address.getLocalHost().getHostAddress();
        int serverPort = PrimeFacesContext.getCurrentInstance().getExternalContext().getRequestServerPort();
        String protocol = PrimeFacesContext.getCurrentInstance().getExternalContext().getRequestScheme();
        ret = format("%s:%s%s", serverName, serverPort, getPath());
        log.debug("link: " + ret);
        return ret;
    }

}
