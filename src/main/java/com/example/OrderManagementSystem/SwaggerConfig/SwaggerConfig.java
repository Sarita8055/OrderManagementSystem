package com.example.OrderManagementSystem.SwaggerConfig;

import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI; 
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI customOpenAPI() {
	    return new OpenAPI()
	        .info(new Info()
	            .title("Order Management System API")
	            .version("1.0")
	        )
	        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
	        .components(
	            new io.swagger.v3.oas.models.Components()
	                .addSecuritySchemes("bearerAuth",
	                    new SecurityScheme()
	                        .name("bearerAuth")
	                        .type(SecurityScheme.Type.HTTP)
	                        .scheme("bearer")
	                        .bearerFormat("JWT")
	                )
	        );
	}
}
