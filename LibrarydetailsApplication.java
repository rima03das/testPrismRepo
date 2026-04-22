package com.cee.librarydetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.web.client.RestTemplate;

import com.cee.librarydetails.config.AuditorAwareConfiguration;

import java.time.Duration;

@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@EnableMongoAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
public class LibrarydetailsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LibrarydetailsApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LibrarydetailsApplication.class);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.setConnectTimeout(Duration.ofMinutes(5000000)).setReadTimeout(Duration.ofMinutes(5000000)).build();
	}

	@Bean(name = "auditorAware")
	public AuditorAwareConfiguration auditAware() {
		return new AuditorAwareConfiguration();
	}

}
