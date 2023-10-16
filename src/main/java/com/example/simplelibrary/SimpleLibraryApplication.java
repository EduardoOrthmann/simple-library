package com.example.simplelibrary;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Simple Library API",
                version = "1.0.0",
                description = "This is a simple library API made for the module of Spring boot in the +devs2blu program.\""
        ),
        servers = {
                @Server(url = "https://simple-library-production.up.railway.app", description = "Production server"),
                @Server(url = "http://localhost:8080", description = "Local development server")
        }
)
public class SimpleLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleLibraryApplication.class, args);
    }

}
