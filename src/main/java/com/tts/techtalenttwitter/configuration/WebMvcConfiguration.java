package com.tts.techtalenttwitter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	//Write the instructions to how to create
	//a BCryptPasswordEncoder
	//The way we do that is we put a method
	//inside a class with @Configuration annotation...
	
	//And the method is annotated with:
	@Bean
	//The method must return the type you are defining
	//how to create.
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder =
			new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
}