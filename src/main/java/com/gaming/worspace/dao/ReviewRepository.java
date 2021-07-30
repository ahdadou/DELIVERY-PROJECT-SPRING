package com.gaming.worspace.dao;

import com.gaming.worspace.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

//    Object existsByUser_receiverUsername(String username);
}
