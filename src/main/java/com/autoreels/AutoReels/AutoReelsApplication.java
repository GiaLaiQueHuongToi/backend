package com.autoreels.AutoReels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.autoreels.AutoReels", "com.windowprogramming.ClothingStoreManager"})
@EnableJpaRepositories(basePackages = {"com.autoreels.AutoReels.repository", "com.windowprogramming.ClothingStoreManager.repository"})
@EntityScan(basePackages = {"com.autoreels.AutoReels.entity", "com.windowprogramming.ClothingStoreManager.entity"})
public class AutoReelsApplication {
	public static void main(String[] args) {
		SpringApplication.run(AutoReelsApplication.class, args);
	}
}