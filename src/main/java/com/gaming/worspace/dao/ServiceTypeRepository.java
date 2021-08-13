package com.gaming.worspace.dao;


import com.gaming.worspace.models.Stype;
import com.gaming.worspace.models.enumerated.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ServiceTypeRepository extends JpaRepository<Stype,Long> {
    Optional<Stype> findByType(Type type);


}
