package com.recipe.universe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = {ElasticsearchDataAutoConfiguration.class})
public class UniverseApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniverseApplication.class, args);
	}


}
