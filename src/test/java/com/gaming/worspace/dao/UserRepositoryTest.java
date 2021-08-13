package com.gaming.worspace.dao;

import com.gaming.worspace.models.User;
import com.gaming.worspace.models.enumerated.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    UserRepository underTest;

    User user = new User();

    @BeforeEach
    void setUp() {
        //todo: create user instance
        user.setFirstname("IBRAA");
        user.setLastname("IBRAA");
        user.setActive(true);
        user.setGender(Gender.MALE);
        user.setEmailVerified(false);
        user.setEmailVerified(false);
    }

    @Test
    void isShouldSelectUserByEmail(){

        // Given
        String email = "IBRAA@GMAIL.COM";
        user.setEmail(email);
        underTest.save(user);

        // WHEN
        boolean exists = underTest.existsByEmail(email);

        // Then
//        assertThat(exists).isTrue();

        Optional<User> optionalUser = underTest.findByEmail(email);
        assertThat(optionalUser)
                .isPresent()
                .hasValueSatisfying(c -> {
                    assertThat(c).isEqualToComparingFieldByField(user);
                });
    }


    @Test
    void itNotShouldSelectUserByEmailWhenEmailDoesNotExists() {
        // Given
        String email = "IBRAA@GMAIL.COM";

        // When
        Optional<User> optionalUser = underTest.findByEmail(email);

        // Then
        assertThat(optionalUser).isNotPresent();
    }


//    @Test
//    void isShouldSelectUserByUsername() {
//        // Given
//
//        String email = "IBRAA@GMAIL.COM";
//        user.setEmail(email);
//
//        // When
//        underTest.save(user);
//
//        // Then
////        Optional<User> optionalUser = underTest.findByUsername(user.getUsername());
//        assertThat(optionalUser)
//                .isPresent()
//                .hasValueSatisfying(c -> {
////                    assertThat(c.getId()).isEqualTo(id);
////                    assertThat(c.getUsername()).isEqualTo("Ahdadou");
////                    assertThat(c.getPhoneNumber()).isEqualTo("1111");
//                    assertThat(c).isEqualToComparingFieldByField(user);
//                });
//    }


    @Test
    void itShouldNotSaveUserWhenEmailIsNull() {
        // Given

        // When
        // Then
        assertThatThrownBy(() -> underTest.save(user))
                .hasMessageContaining("not-null property references a null or transient value : com.gaming.worspace.dao.UserRepositoryTest.User.email")
                .isInstanceOf(DataIntegrityViolationException.class);

    }
}