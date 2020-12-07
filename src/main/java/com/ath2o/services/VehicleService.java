package com.ath2o.services;

import com.ath2o.data.entities.Vehicle;
import com.ath2o.data.repositories.VehicleRepository;
import com.ath2o.definitions.SearchCriteria;
import com.ath2o.exceptions.VehicleSearchSpecificException;
import com.ath2o.loader.VehicleRepositoryLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    private VehicleRepositoryLoader vehicleRepositoryLoader;

    @Value( "${datasource.file.name}" )
    private String fileName;

    @Autowired
    VehicleService(VehicleRepository vehicleRepository, VehicleRepositoryLoader vehicleRepositoryLoader) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleRepositoryLoader = vehicleRepositoryLoader;
    }

    @PostConstruct
    public void initData() throws Exception {
        vehicleRepositoryLoader.loadRespositoryFromFileIntoInMemoryDB(fileName);
    }

    public List<Vehicle> filterVehicles(String searchCriteria, String searchKey) throws Exception {
        if(StringUtils.isEmpty(searchCriteria))
            throw new VehicleSearchSpecificException("INVALID CRITERIA", "search criteria can not be null or empty, field must be one of these -  brand, model, class or all");
        if(StringUtils.isEmpty(searchKey))
            throw new VehicleSearchSpecificException("INVALID KEY", "search key can not be null or empty");

        switch (searchCriteria.toUpperCase()) {
            case SearchCriteria.BRAND:
                return vehicleRepository.findByBrandContainingIgnoreCase(searchKey);
            case SearchCriteria.MODEL:
                return vehicleRepository.findByModelContainingIgnoreCase(searchKey);
            case SearchCriteria.CLASS:
                return vehicleRepository.findByClassTypeContainingIgnoreCase(searchKey);
            case SearchCriteria.ALL:
                return vehicleRepository.findByAnyContainingIgnoreCase(searchKey);
            default:
                throw new VehicleSearchSpecificException("INVALID CRITERIA", "Unknown search criteria, field must be one of these -  brand, model, class or all");
        }
    }
}