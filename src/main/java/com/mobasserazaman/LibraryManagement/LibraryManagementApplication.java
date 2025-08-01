package com.mobasserazaman.LibraryManagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mobasserazaman.LibraryManagement.entity.Role;
import com.mobasserazaman.LibraryManagement.entity.User;
import com.mobasserazaman.LibraryManagement.repository.UserRepository;

@SpringBootApplication
public class LibraryManagementApplication {

	@Bean
	public CommandLineRunner createAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder){
		return args -> {
			if(userRepository.findByUsername("admin").isEmpty()){
				User admin = User.builder().studentId(999999999L).username("admin").password(passwordEncoder.encode("admin123")).role(Role.ADMIN).build();
				userRepository.save(admin);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
	}

}
