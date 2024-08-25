package com.recipe.universe.global.config;


import org.opensearch.client.RestHighLevelClient;
import org.opensearch.data.client.orhlc.AbstractOpenSearchConfiguration;
import org.opensearch.data.client.orhlc.ClientConfiguration;
import org.opensearch.data.client.orhlc.OpenSearchRestTemplate;
import org.opensearch.data.client.orhlc.RestClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenSearchConfig extends AbstractOpenSearchConfiguration {
    @Value("${opensearch.username}")
    private String username;

    @Value("${opensearch.password}")
    private String password;

    @Value("${opensearch.host}")
    private String host;

    @Value("${opensearch.port}")
    private int port;

    @Override
    public RestHighLevelClient opensearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(host + ":" + port)
                .usingSsl()
                .withBasicAuth(username, password)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
