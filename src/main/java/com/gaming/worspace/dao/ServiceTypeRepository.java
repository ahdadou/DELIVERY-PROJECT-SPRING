package com.gaming.worspace.dao;


import com.gaming.worspace.models.SERVICE_TYPE;
import com.gaming.worspace.models.enumerated.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ServiceTypeRepository extends JpaRepository<SERVICE_TYPE,Long> {
    Optional<SERVICE_TYPE> findByType(Type type);


}
