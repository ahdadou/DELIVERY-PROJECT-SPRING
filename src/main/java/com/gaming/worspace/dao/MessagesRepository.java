package com.gaming.worspace.dao;

import com.gaming.worspace.models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessagesRepository extends JpaRepository<Messages,Long> {

     @Query("SELECT u FROM Messages u WHERE u.inbox.Id = :id")
     Optional<List<Messages>> findAllByInboxId(@Param("id") long id);
}
