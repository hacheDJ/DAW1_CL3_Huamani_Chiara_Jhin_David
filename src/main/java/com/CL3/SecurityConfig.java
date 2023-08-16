package com.CL3;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public UserDetailsService userDetailService() {
		User user = new User(
				"dejota", 
				getPasswordEncoder().encode("1234"), 
				Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
				);
		
		return new InMemoryUserDetailsManager(user);
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		return httpSecurity.authorizeHttpRequests(authorizeHttpRequests -> 
			authorizeHttpRequests
				.requestMatchers("/", "/about", "/fagments/head", "/css/style.css").permitAll()
				//.requestMatchers("/**").permitAll()
				.requestMatchers("/**"/*"/about/frmRegisterEmployee", "/about/frmUpdateEmploye", "/about/deleteEmployee"*/).authenticated()
			)
			.formLogin(formLogin -> formLogin.loginPage("/user/login").permitAll())
			.build();
		
	}

}
