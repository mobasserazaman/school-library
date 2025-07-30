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
				User admin = User.builder().username("admin").password(passwordEncoder.encode("admin123")).role(Role.ADMIN).build();
				System.out.println(passwordEncoder.encode("admin123"));
				System.out.println(passwordEncoder.getClass().getName());
				User adminUser = userRepository.save(admin);
				System.out.println(adminUser);

				System.out.println("Admin user created: username=admin, password=admin123");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
	}

}
