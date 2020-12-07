package com.ath2o;

import com.ath2o.configuration.SoapConfiguration;
import com.ath2o.xml.vehicle.SearchVehicleRequest;
import com.ath2o.xml.vehicle.SearchVehicleResponse;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SoapConfiguration.class)
public class SoapServiceTests {
    @Test
    @Ignore("Need to be run when service is up")
    public void getBelde() throws IOException, JAXBException {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.ath2o.xml.vehicle");
        marshaller.setSchema(new ClassPathResource("vehicles.xsd"));
        webServiceTemplate.setMarshaller(marshaller);
        webServiceTemplate.setUnmarshaller(marshaller);

        SearchVehicleRequest searchVehicleRequest = new SearchVehicleRequest();
        searchVehicleRequest.setSearchCriteria("all");
        searchVehicleRequest.setSearchKey("belde");
        SearchVehicleResponse searchVehicleResponse = (SearchVehicleResponse) webServiceTemplate.marshalSendAndReceive("http://localhost:9191/soapService/searchVehicle", searchVehicleRequest);

        Assert.assertEquals(searchVehicleResponse.getVehicle().isEmpty(), false);

    }
}