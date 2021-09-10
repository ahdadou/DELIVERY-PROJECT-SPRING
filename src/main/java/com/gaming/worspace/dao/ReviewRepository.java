package com.gaming.worspace.dao;

import com.gaming.worspace.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query("SELECT u FROM Review u WHERE u.user_receiver.email = :email")
    Optional<List<Review>> findByUser_receiverEmail(@Param("email") String email);

    @Query("SELECT u FROM Review u WHERE u.user_receiver.email = :email_receiver AND u.user_sender.email = :email_sender")
    Review findByUser_receiverEmailAndUser_senderEmail(@Param("email_receiver") String email_receiver,@Param("email_sender") String email_sender);

//    Object existsByUser_receiverUsername(String username);
}
