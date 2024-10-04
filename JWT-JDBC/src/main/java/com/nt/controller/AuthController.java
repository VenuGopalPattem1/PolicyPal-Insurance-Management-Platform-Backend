package com.nt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.jwt.JwtUtils;
import com.nt.jwt.LoginRequest;
import com.nt.jwt.LoginResponse;

@RestController
@CrossOrigin
public class AuthController {
	   @Autowired
	    private PasswordEncoder passwordEncoder;

	    @Autowired
	    private DataSource dataSource;
	    
	    @Autowired
	    private JwtUtils jwtUtils;

	    @Autowired
	    private AuthenticationManager authenticationManager;


	    @PostMapping("/register")
	    public String registerUser(@RequestParam String username, @RequestParam String password) {
	        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
	        
	        // Check if the user already exists
	        if (userDetailsManager.userExists(username)) {
	            return "User already exists!";
	        }

	        // Register the user in the database
	        userDetailsManager.createUser(
	                org.springframework.security.core.userdetails.User.withUsername(username)
	                .password(passwordEncoder.encode(password))
	                .roles("USER") // Assign role
	                .build());

	        return "User registered successfully!";
	    }
	    
	    
	    @PostMapping("/signin")
	    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
	        Authentication authentication;
	        try {
	            authentication = authenticationManager
	                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
	        } catch (AuthenticationException exception) {
	            Map<String, Object> map = new HashMap<>();
	            map.put("message", "Bad credentials");
	            map.put("status", false);
	            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
	        }

	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

	        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

	        List<String> roles = userDetails.getAuthorities().stream()
	                .map(item -> item.getAuthority())
	                .collect(Collectors.toList());

	        LoginResponse response = new LoginResponse(jwtToken,userDetails.getUsername(), roles);

	        return ResponseEntity.ok(response);
	    }
}
