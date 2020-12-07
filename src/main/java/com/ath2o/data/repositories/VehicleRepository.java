package com.ath2o.data.repositories;

import com.ath2o.data.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
    @Query("SELECT cd FROM Vehicle as cd WHERE UPPER(cd.brand)  LIKE  CONCAT( '%', UPPER(:brand), '%') ")
    List<Vehicle> findByBrandContainingIgnoreCase(@Param("brand") String brand);

    @Query("SELECT cd FROM Vehicle as cd WHERE UPPER(cd.model)  LIKE  CONCAT( '%', UPPER(:model), '%') ")
    List<Vehicle> findByModelContainingIgnoreCase(@Param("model") String model);

    @Query("SELECT cd FROM Vehicle as cd WHERE UPPER(cd.classType)  LIKE  CONCAT( '%', UPPER(:classType), '%') ")
    List<Vehicle> findByClassTypeContainingIgnoreCase(@Param("classType") String classType);

    @Query("SELECT cd FROM Vehicle as cd WHERE UPPER(cd.brand)  LIKE CONCAT( '%', UPPER(?1), '%') OR UPPER(cd.model)  LIKE CONCAT( '%', UPPER(?1), '%') OR UPPER(cd.classType)  LIKE CONCAT( '%', UPPER(?1), '%') ")
    List<Vehicle> findByAnyContainingIgnoreCase( String keyword);
}
