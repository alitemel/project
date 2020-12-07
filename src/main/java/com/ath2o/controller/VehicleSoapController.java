package com.ath2o.controller;


import com.ath2o.exceptions.ServiceFault;
import com.ath2o.exceptions.ServiceFaultException;
import com.ath2o.exceptions.VehicleSearchSpecificException;
import com.ath2o.services.VehicleService;
import com.ath2o.xml.vehicle.SearchVehicleRequest;
import com.ath2o.xml.vehicle.SearchVehicleResponse;
import com.ath2o.xml.vehicle.Vehicle;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapFaultException;

import java.util.List;

@Endpoint
public class VehicleSoapController {
    private static final String NAMESPACE_URI = "http://www.ath2o.com/xml/vehicle";

    private VehicleService vehicleService;

    @Autowired
    public VehicleSoapController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     *
     * @param  request
     * @return SearchVehicleResponse
     * @throws SoapFaultException
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SearchVehicleRequest")
    @ResponsePayload
    public SearchVehicleResponse searchVehicle(@RequestPayload SearchVehicleRequest request) throws ServiceFaultException {
        try {
            SearchVehicleResponse response = new SearchVehicleResponse();
            vehicleService.filterVehicles(request.getSearchCriteria().toString(), request.getSearchKey());
            ObjectMapper objectMapper = new ObjectMapper();
            // there is a diff between entity and class, entitiy has an id
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            response.getVehicle().addAll(objectMapper.convertValue(vehicleService.filterVehicles(request.getSearchCriteria().toString(), request.getSearchKey()),   new TypeReference<List<Vehicle>>(){} ) );

            return response;
        }
        catch (VehicleSearchSpecificException vex)
        {
            throw new ServiceFaultException(vex.getErrorCode() ,new ServiceFault( vex.getErrorCode(), vex.getDescription()));
        }
        catch (Exception e)
        {
            throw new ServiceFaultException("UNKNOWN ERROR" ,new ServiceFault( "UNKNOWN ERROR", "Internal error" ));

        }
    }
}