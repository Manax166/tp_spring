package fr.diginamic.hello;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:application.properties")
@EnableAutoConfiguration
public class RepositoryContextConfiguration {
}
