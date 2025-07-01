package com.autoreels.AutoReels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.autoreels.AutoReels", "com.autoreels.AutoReels"})
@EnableJpaRepositories(basePackages = {"com.autoreels.AutoReels.repository", "com.autoreels.AutoReels.repository"})
@EntityScan(basePackages = {"com.autoreels.AutoReels.entity", "com.autoreels.AutoReels.entity"})
public class AutoReelsApplication {
	public static void main(String[] args) {
		SpringApplication.run(AutoReelsApplication.class, args);
	}
}