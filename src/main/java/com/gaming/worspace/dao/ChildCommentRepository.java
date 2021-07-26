package com.gaming.worspace.dao;

import com.gaming.worspace.models.ChildCommets;
import com.gaming.worspace.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChildCommentRepository extends JpaRepository<ChildCommets,Long> {

}
