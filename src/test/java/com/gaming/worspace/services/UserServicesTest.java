package com.gaming.worspace.services;

import com.gaming.worspace.dao.RoleRepository;
import com.gaming.worspace.dao.UserRepository;
import com.gaming.worspace.models.User;
import com.gaming.worspace.models.dto.request.UserRequestDTO;
import com.gaming.worspace.models.enumerated.Gender;
import com.gaming.worspace.services.mappers.UserMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class UserServicesTest {


    @Mock
    private RoleService roleService;

    @Mock
    UserRepository userRepository;


    @InjectMocks
    private UserServices underTest;

    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new UserServices(userRepository,roleService,userMapper);
    }

    @Test
    void isShouldCreateUser() {
        //Given: User Instant
        UserRequestDTO user = new UserRequestDTO();
        user.setUsername("IBRA.AHD");user.setFirstname("IBRA.AHD");
        user.setLastname("IBRA.AHD");user.setEmail("IBRA@GMAIL.COM");
        user.setGender(Gender.MALE.toString());
        // When
        underTest.createUser(user);
        // Then
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());
        then(userRepository).should().save(userArgumentCaptor.capture());
        User userArgumentCaptorValue = userArgumentCaptor.getValue();
        assertThat(userArgumentCaptorValue.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    void shouldGetUserById() {

    }

    @Test
    void getAllUser() {

        //when
        underTest.getAllUser();

        //then
        verify(userRepository).findAll();


    }

    @Test
    void deleteUser() {
    }
}