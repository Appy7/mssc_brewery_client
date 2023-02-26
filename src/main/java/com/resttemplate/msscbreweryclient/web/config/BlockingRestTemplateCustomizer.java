package com.resttemplate.msscbreweryclient.web.config;

import com.resttemplate.msscbreweryclient.web.client.RestTemplateCustomizer;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    private final Integer maxTotalConnections;
    private final Integer maxConnectionsPerRoute;
    private final Integer requestTimeout;
    private final Integer socketTimeout;


    public BlockingRestTemplateCustomizer(@Value("${sfg.maxtotalconnections}") Integer maxTotalConnections,
                                          @Value("${sfg.maxconnectionsperroute}")Integer maxConnectionsPerRoute,
                                          @Value("${sfg.requesttimeout}")Integer requestTimeout,
                                          @Value("${sfg.sockettimeout}")Integer socketTimeout) {
        this.maxTotalConnections = maxTotalConnections;
        this.maxConnectionsPerRoute = maxConnectionsPerRoute;
        this.requestTimeout = requestTimeout;
        this.socketTimeout = socketTimeout;
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestfactory());
    }

    public ClientHttpRequestFactory clientHttpRequestfactory() {
        PoolingHttpClientConnectionManager clientConnectionManager=new PoolingHttpClientConnectionManager();
        clientConnectionManager.setMaxTotal(maxTotalConnections);
        clientConnectionManager.setDefaultMaxPerRoute(maxConnectionsPerRoute);

        RequestConfig requestConfig =RequestConfig
                .custom()
                .setConnectionRequestTimeout(requestTimeout)
                .setSocketTimeout(socketTimeout)
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(clientConnectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }
}

    git init
    git add README.md
        git commit -m "first commit"
        git branch -M main
        git remote add origin https://github.com/Appy7/mssc_brewery_client.git
        git push -u origin main
