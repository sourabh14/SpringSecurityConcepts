package com.example.SpringSecurityConcepts;

import com.example.SpringSecurityConcepts.entity.User;
import com.example.SpringSecurityConcepts.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
public class SpringSecurityConceptsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityConceptsApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			try {
				userRepository.save(new User(null, "admin", passwordEncoder.encode("admin"), "ADMIN"));
				userRepository.save(new User(null, "normal", passwordEncoder.encode("normal"), "NORMAL"));
			} catch (Exception e) {
				System.out.println(e);;
			}
		};
	}


}
