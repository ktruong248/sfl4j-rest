package api;

import api.model.model.InsertResponse;
import api.model.model.LogLine;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public final class LoggingServiceClientImpl implements LoggingServiceClient {
    private static final Logger log = LoggerFactory.getLogger(LoggingServiceClientImpl.class);

    public static final int DEFAULT_CONNECT_TIMEOUT_MS = 60000;

    public static final int DEFAULT_READ_TIMEOUT_MS = 120000;

    private int connectTimeoutMs = DEFAULT_CONNECT_TIMEOUT_MS;

    private int readTimeoutMs = DEFAULT_READ_TIMEOUT_MS;

    private Client client;

    private String serviceUrl;

    public LoggingServiceClientImpl(Client client, String serviceUrl) {
        this.serviceUrl = serviceUrl + "/logs";
        this.client = client;
        this.client.setReadTimeout(readTimeoutMs);
        this.client.setConnectTimeout(connectTimeoutMs);
    }

    public LoggingServiceClientImpl(String serviceUrl) {
        this.serviceUrl = serviceUrl + "/logs";
        ClientConfig cc = new DefaultClientConfig();
        cc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        client = Client.create(cc);
        this.client.setReadTimeout(readTimeoutMs);
        this.client.setConnectTimeout(connectTimeoutMs);
    }

    public String insert(LogLine logLine) {
        log.info("inserting {} to {}", logLine, this.serviceUrl);

        URI insertURI = UriBuilder.fromPath(this.serviceUrl).build();
        WebResource resource = client.resource(insertURI);
        ClientResponse clientResponse = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, logLine);
        InsertResponse insertResponse = clientResponse.getEntity(InsertResponse.class);

        return insertResponse.getLogId();
    }

    public LogLine get(String id) {
        URI getURI = UriBuilder.fromPath(this.serviceUrl).path("{logId}").build(id);
        log.info("getting log id {}", id);

        WebResource resource = client.resource(getURI);
        try {
            ClientResponse response = resource.get(ClientResponse.class);
            return response.getEntity(LogLine.class);
        } catch (Exception e) {
            throw new LoggingServiceClientException(ErrorCode.FAILED_TO_GET.name(), e.getMessage(), e);
        }
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeoutMs = connectTimeout;
        this.client.setConnectTimeout(connectTimeoutMs);
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeoutMs = readTimeout;
        this.client.setReadTimeout(readTimeout);
    }

    protected Client getClient() {
        return this.client;
    }
}
