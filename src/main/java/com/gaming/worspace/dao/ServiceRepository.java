package com.gaming.worspace.dao;


import com.gaming.worspace.models.SERVICE_TYPE;
import com.gaming.worspace.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<SERVICE_TYPE,Long> {
    Optional<SERVICE_TYPE> findByType(String type);


}
