package com.gaming.worspace.dao;

import com.gaming.worspace.models.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking,Long> {
}
