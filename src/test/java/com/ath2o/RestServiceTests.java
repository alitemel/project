package com.ath2o;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestServiceTests
{
    @LocalServerPort
    int randomServerPort;

    @Test
    public void testGetSearchVehicleSuccess() throws URISyntaxException
    {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort + "/restService/searchVehicle/all/a";
        URI uri = new URI(baseUrl);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        //Verify request succeed
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assert.assertNotNull(result.getBody());
        Assert.assertEquals(true, result.getBody().contains("model"));
    }
}