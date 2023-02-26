package com.resttemplate.msscbreweryclient.web.client;


import com.resttemplate.msscbreweryclient.web.model.BeerDto;
import com.resttemplate.msscbreweryclient.web.model.CustomerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.brewery",ignoreUnknownFields = false)
public class BreweryClient {

    public final String BEER_PATH_V1="/api/v1/beer/";

    public final String CUSTOMER_PATH_V1="/api/v1/customer/";
    private String apiHost;

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeer(UUID id){
        return restTemplate.getForObject(apiHost+BEER_PATH_V1+id.toString(), BeerDto.class);
    }

    public URI saveNewBeerDto(BeerDto beerDto){
        return restTemplate.postForLocation(apiHost +BEER_PATH_V1,beerDto);
    }

    public void updateNewBeer(UUID uuid, BeerDto beerDto) {
        restTemplate.put(apiHost + BEER_PATH_V1 +"/"+uuid.toString(),beerDto);
    }

    public void deleteBeer(UUID randomUUID) {
        restTemplate.delete(apiHost+BEER_PATH_V1+"/"+randomUUID.toString());
    }

    public CustomerDto getCustomer(UUID randomUUID) {
         return restTemplate.getForObject(apiHost+CUSTOMER_PATH_V1+randomUUID, CustomerDto.class);
    }

    public URI saveNewCustomerDto(CustomerDto customerDto) {
       return restTemplate.postForLocation(apiHost+CUSTOMER_PATH_V1,customerDto);
    }

    public void updateNewCustomer(UUID randomUUID, CustomerDto customerDto) {
        restTemplate.put(apiHost+CUSTOMER_PATH_V1+"/"+randomUUID,customerDto);
    }

    public void deleteCustomer(UUID randomUUID) {
        restTemplate.delete(apiHost+CUSTOMER_PATH_V1+"/"+randomUUID);
    }
}
