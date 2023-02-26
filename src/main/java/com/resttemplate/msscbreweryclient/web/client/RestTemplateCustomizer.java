package com.resttemplate.msscbreweryclient.web.client;

import org.springframework.web.client.RestTemplate;

@FunctionalInterface
public interface RestTemplateCustomizer {
    void customize(RestTemplate restTemplate);
}
