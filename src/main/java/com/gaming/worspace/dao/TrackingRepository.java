package com.gaming.worspace.dao;

import com.gaming.worspace.models.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking,Long> {
    Tracking findByUserEmail(String userEmail);
    Optional<Tracking> findByUuid(String uuid);
}
