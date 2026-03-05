package com.kbrsphere.property_management.repository;

import com.kbrsphere.property_management.model.Property;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends MongoRepository<Property,String> {

}
