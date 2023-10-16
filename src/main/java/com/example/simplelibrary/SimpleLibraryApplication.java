package com.example.simplelibrary;

import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Server(url = "https://simple-library-production.up.railway.app", description = "Production server")
@Server(url = "http://localhost:8080", description = "Local development server")
public class SimpleLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleLibraryApplication.class, args);
    }

}
