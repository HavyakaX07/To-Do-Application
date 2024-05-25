package com.hosalli.hegde.TODOApplication.Configure;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class TodoSecurityConfig {
	
	@Autowired
	private AuthenticationProvider myCustomAuthenticationProvider;
	
	
	@Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new ProviderManager(Arrays.asList(myCustomAuthenticationProvider));
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myCustomAuthenticationProvider);
    }
    


	@SuppressWarnings({ "removal", "deprecation" })
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 http
         .csrf().disable() // Disable CSRF protection
         .authorizeRequests()
             .requestMatchers("/login","/register", "/registerUser","/WEB-INF/jsp/loginPage.jsp","/WEB-INF/jsp/registrationPage.jsp","/manageTodos","/WEB-INF/jsp/manageTodosPage.jsp").permitAll() // Allow access to /login for all users
             .anyRequest().authenticated() // Any other request must be authenticated
             .and()
         .formLogin()
             .loginPage("/login") // The login page
             .loginProcessingUrl("/loginUser").usernameParameter("userName")
             .passwordParameter("password") // The URL to submit the username and password
             .defaultSuccessUrl("/manageTodos") // The page to go to after successful login
             .failureUrl("/login?error=true") // The page to go to after failed login
             .and()
         .logout()
             .logoutUrl("/logout") // The URL to logout
             .logoutSuccessUrl("/login"); // The page to go to after logout
        return http.build();
    }


}
