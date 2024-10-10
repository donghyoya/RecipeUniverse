package com.recipe.universe.global.config;

import org.apache.http.HttpHost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestClientBuilder;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.data.client.orhlc.ClientConfiguration;
import org.opensearch.data.client.orhlc.RestClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RestHighClientConfig {
    @Value("${opensearch.host}")
    private String host;
    @Bean
    @Profile({"dev", "test"})
    public RestHighLevelClient devClient() {
        RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "https"))
                .setRequestConfigCallback(
                        requestConfigBuilder -> requestConfigBuilder
                                .setConnectTimeout(30000)
                                .setSocketTimeout(300000))
                .setHttpClientConfigCallback(
                        httpClientBuilder -> httpClientBuilder
                                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                                .setConnectionReuseStrategy((response, context) -> true)
                                .setKeepAliveStrategy(((response, context) -> 300000)));
        return new RestHighLevelClient(builder);
    }

    @Bean
    @Profile({"prod"})
    public RestHighLevelClient prodClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(host)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }

}
