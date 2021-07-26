package com.gaming.worspace;

import com.gaming.worspace.dao.UserRepository;
import com.gaming.worspace.models.Role;
import com.gaming.worspace.models.User;
import com.gaming.worspace.models.dto.request.UserRequestDTO;
import com.gaming.worspace.models.enumerated.Gender;
import com.gaming.worspace.models.enumerated.RoleName;
//import com.gaming.worspace.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class GamingApplication{

	@Autowired
	UserRepository userRepository;

	@Autowired


	public static void main(String[] args) {
		SpringApplication.run(GamingApplication.class, args);
	}


}
