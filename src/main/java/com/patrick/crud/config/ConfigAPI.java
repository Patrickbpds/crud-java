package com.patrick.crud.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigAPI {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("CRUD API")
                        .version("1.0.0")
                        .description("API component for CRUD operations developed by Patrick")
                        .contact(new Contact()
                                .name("Patrick Batista")
                                .email("patrickbpds@outlook.com")
                                .url("https://github.com/Patrickbpds"))
                        .license(new License()
                                .name("GPL 3.0")
                                .url("https://www.gnu.org/licenses/gpl-3.0"))
                );
    }
}
