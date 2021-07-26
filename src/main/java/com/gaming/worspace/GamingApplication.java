package com.gaming.worspace;

import com.gaming.worspace.models.User;
import com.gaming.worspace.models.dto.request.UserRequestDTO;
import com.gaming.worspace.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class GamingApplication implements  CommandLineRunner{

	@Autowired
	private UserMapper userMapper;

	public static void main(String[] args) {
		SpringApplication.run(GamingApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setEmail("sss");
		user.setUsername("Ssaa");
		user.setFirstname("Ssaa");
		user.setLastname("Ssaa");
		user.setActive(true);

		UserRequestDTO userRequestDto = userMapper.toUserDTO(user);
		System.out.println(user);
		Set<String> tt = new HashSet<>();
		tt.add("User");tt.add("ADMIN");
		userRequestDto.setRolesString(tt);
		System.out.println(userRequestDto);
		System.out.println("*****************");
		User userr = userMapper.toUser(userRequestDto);
		System.out.println(userr);


	}
}
