package com.ath2o.loader;

import com.ath2o.data.entities.Vehicle;
import com.ath2o.data.repositories.VehicleRepository;
import com.ath2o.exceptions.VehicleSearchSpecificException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

@Component
public class VehicleRepositoryLoader {

    private VehicleRepository vehicleRepository;

    VehicleRepositoryLoader(VehicleRepository vehicleRepository)
    {
        this.vehicleRepository =vehicleRepository;
    }

    public void loadRespositoryFromFileIntoInMemoryDB(String fileName) throws Exception {

        try {
            URL resource = getClass().getClassLoader().getResource(fileName);
            if (resource == null) {
                throw new IllegalArgumentException(fileName + "file not found!");
            } else {
                Scanner scanner = new Scanner(new File(resource.toURI()));
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Vehicle vehicle = new Vehicle(line);
                    vehicleRepository.save(vehicle);
                }
            }
        } catch (Exception e) {
            throw new VehicleSearchSpecificException("RESOURCE LOAD ERROR", "Resoruce could not load !!" );
        }
    }
}
