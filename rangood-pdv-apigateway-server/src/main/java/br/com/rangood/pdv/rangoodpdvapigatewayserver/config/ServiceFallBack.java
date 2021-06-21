package br.com.rangood.pdv.rangoodpdvapigatewayserver.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class ServiceFallBack implements FallbackProvider {

    private static final String DEFAULT_FALLBACK_MESSAGE = "This service is unavailable.";
    private static final String GATEWAY_TIMEOUT_FALLBACK_MESSAGE = "The resource is currently unavailable.";

    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
            if (cause instanceof HystrixTimeoutException) {
                return response(HttpStatus.GATEWAY_TIMEOUT, GATEWAY_TIMEOUT_FALLBACK_MESSAGE);
            } else {
                return response(HttpStatus.SERVICE_UNAVAILABLE, DEFAULT_FALLBACK_MESSAGE);
            }
    }

    private ClientHttpResponse response(final HttpStatus status, final String message) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                Map<String,Object> map = new HashMap<>();
                map.put("code", String.valueOf(status.value()));
                map.put("message", message);
                final String json = new ObjectMapper().writeValueAsString(map);
                return new ByteArrayInputStream(json.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }

}
