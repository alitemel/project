package com.ath2o.controller;

import com.ath2o.data.entities.Vehicle;
import com.ath2o.exceptions.RestExceptionHelper;
import com.ath2o.exceptions.VehicleSearchSpecificException;
import com.ath2o.models.VehicleRestRequestModel;
import com.ath2o.services.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restService")
public class VehicleRestController {

    private VehicleService vehicleService;

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleRestController.class);

    @Autowired
    VehicleRestController(VehicleService vehicleService)
    {
        this.vehicleService = vehicleService;
    }

    /**
     *
     * @param searchCriteria
     * @param searchKey
     * @return List<Vehicle>
     * @throws Exception
     */
    @GetMapping("/searchVehicle/{searchCriteria}/{searchKey}")
    List<Vehicle> searchVehicle(@PathVariable String searchCriteria, @PathVariable String searchKey) throws Exception {
            List<Vehicle> vehicles = vehicleService.filterVehicles(searchCriteria, searchKey);
            if(vehicles.isEmpty())
                throw new VehicleSearchSpecificException("NOT FOUND", "We could not found any vehicle for criteria = "
                        + searchCriteria + " and key = " + searchKey );

            LOGGER.info("search result :"+ System.lineSeparator() + vehicles.stream().map(e -> e.toString()).reduce("", String::concat));
            return vehicles;
    }

    /**
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping (path= "/searchVehicle", consumes = "application/json", produces = "application/json")
    List<Vehicle> searchVehicle(@RequestBody VehicleRestRequestModel request) throws Exception {
        List<Vehicle> vehicles = vehicleService.filterVehicles(request.getSearchCriteria(), request.getSearchKey());
        if(vehicles.isEmpty())
            throw new VehicleSearchSpecificException("NOT FOUND", "We could not found any vehicle for criteria = "
                    + request.getSearchCriteria() + " and key = " +  request.getSearchKey() );

        LOGGER.info("search result : "+ System.lineSeparator() + vehicles.stream().map(e -> e.toString()).reduce("", String::concat));
        return vehicles;
    }
}
