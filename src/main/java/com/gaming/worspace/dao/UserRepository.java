package com.gaming.worspace.dao;

import com.gaming.worspace.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);


//    @Query("SELECT u FROM User u WHERE u.stype.id=:id ")
    List<User> findByStypeId(long id);

    Optional<User> findByEmail(String email);

    List<User> findByCityNameAndStypeId(String city,long id);

    List<User> findByRatingAverageAndStypeId(double rating,long id);

    List<User> findByCityNameAndRatingAverageAndStypeId( String city,int rating,long id);

    @Query("SELECT u FROM User u where u.email LIKE %:param% OR u.cityName LIKE %:param%")
    List<User> findByCityOrEmail(@Param("param") String param);

//    @Query("SELECT u FROM User u "+
//            "WHERE LOWER(u.city.name)=LOWER(:city) and "+
//            " ratingAverage=:rating "
//    )
//    List<User> findByCityNameAndRatingAverageQuery(@Param("city") String city,@Param("rating") int rating);

}
