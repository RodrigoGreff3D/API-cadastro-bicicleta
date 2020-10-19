package br.com.bicicletas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EntityScan(basePackages = { "br.com.bicicletas.model" })
@ComponentScan(basePackages = { "br.**" })
@EnableJpaRepositories(basePackages = { "br.com.bicicletas.repository" })
@RestController
@EnableAutoConfiguration
@EnableSpringDataWebSupport
@EnableCaching
public class BicicletasApplication {

	public static void main(String[] args) {
		SpringApplication.run(BicicletasApplication.class, args);
	
	}

}
