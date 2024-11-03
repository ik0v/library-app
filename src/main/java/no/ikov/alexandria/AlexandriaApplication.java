package no.ikov.alexandria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AlexandriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlexandriaApplication.class, args);
	}

}
