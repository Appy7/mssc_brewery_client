package com.resttemplate.msscbreweryclient.web.client;

import com.resttemplate.msscbreweryclient.web.model.BeerDto;
import com.resttemplate.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient breweryClient;

    @Test
    void getBeerById() {
        BeerDto beerDto = breweryClient.getBeer(UUID.randomUUID());
        assertNotNull(beerDto);
        System.out.println(beerDto.getBeerName());
    }

    @Test
    void testSaveNewBeerDto() {

        BeerDto beerDto = BeerDto.builder().beerName("butu").build();
        URI uri = breweryClient.saveNewBeerDto(beerDto);
        assertNotNull(uri);
        System.out.println(uri);
    }

    @Test
    void testUpdateNewBeer() {
        BeerDto beerDto = BeerDto.builder().beerName("Aki").build();
        breweryClient.updateNewBeer(UUID.randomUUID(), beerDto);
    }

    @Test
    void testDeleteBeer() {
        breweryClient.deleteBeer(UUID.randomUUID());
    }

    @Test
    void getCutomerById() {
        CustomerDto customerDto = breweryClient.getCustomer(UUID.randomUUID());
        assertNotNull(customerDto);
        System.out.println(customerDto.getName());
    }

    @Test
    void testSaveNewCustomerrDto() {
        CustomerDto customerDto = CustomerDto.builder().name("Aki").build();
        URI uri = breweryClient.saveNewCustomerDto(customerDto);
        assertNotNull(uri);
        System.out.println(uri);
    }

    @Test
    void testUpdateNewCustomer() {
        CustomerDto customerDto = CustomerDto.builder().name("Aki").build();
        breweryClient.updateNewCustomer(UUID.randomUUID(), customerDto);
    }

    @Test
    void testDeleteCustomer() {
        breweryClient.deleteCustomer(UUID.randomUUID());
    }
}