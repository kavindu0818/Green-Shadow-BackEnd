package com.example.Green_Shadow_BackEnd;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableMethodSecurity
public class GreenShadowBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreenShadowBackEndApplication.class, args);}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
