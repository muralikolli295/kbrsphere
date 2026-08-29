package com.kbrsphere.property_management.repository;

import com.kbrsphere.property_management.model.Property;
import com.kbrsphere.property_management.dto.PropertyStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PropertyRepository extends MongoRepository<Property, String> {

     List<Property> findByOwnerId(String ownerId);

     List<Property> findByCityIgnoreCase(String city);

     List<Property> findByCityIgnoreCaseAndPropertyTypeIgnoreCase(
             String city,
             String propertyType
     );

     List<Property> findByCityIgnoreCaseAndPriceBetween(
             String city,
             Double minPrice,
             Double maxPrice
     );

     List<Property> findByStatus(PropertyStatus status);

}