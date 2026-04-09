package com.api.biogreen.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;


@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setUserAgent("biogreen-api/1.0 (caiodesouza.cds@gmail.com)")
                .build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

        factory.setConnectTimeout(5_000);
        factory.setReadTimeout(5_000);

        return new RestTemplate(factory);
    }

}
