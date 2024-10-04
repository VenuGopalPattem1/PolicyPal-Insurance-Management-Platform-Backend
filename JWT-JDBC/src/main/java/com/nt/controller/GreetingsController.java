
package com.nt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {


    @GetMapping("/hello")
    public String sayHello(){
        return "Hello";
    }
    
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String sayAll(){
        return "For Both The Roles";
    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String userEndpoint(){
        return "Hello, User!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminEndpoint(){
        return "Hello, Admin!";
    }


    
}
