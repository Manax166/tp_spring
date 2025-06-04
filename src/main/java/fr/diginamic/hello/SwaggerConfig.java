package fr.diginamic.hello;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI().info(new Info()
                .title("titre")
                .version("1.0")
                .description("Cette API fournit des données sur bla bla bla.")
                .contact(new Contact().name("XXX").email("xxx@maboite.fr")));
    }
}
