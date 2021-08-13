package com.gaming.worspace.dao;

import com.gaming.worspace.models.Inbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InboxRepository extends JpaRepository<Inbox,Long> {
    Optional<List<Inbox>> findAllByUserEmail(String email);

    Inbox findByUserEmailAndConnectedUserEmail(String sender_email, String receiver_email);
}
