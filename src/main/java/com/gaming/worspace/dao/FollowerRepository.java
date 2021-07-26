package com.gaming.worspace.dao;

import com.gaming.worspace.models.Follower;
import com.gaming.worspace.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FollowerRepository extends JpaRepository<Follower,Long> {

}
