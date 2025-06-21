package com.windowprogramming.ClothingStoreManager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "oauth_tokens")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String accessToken;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String refreshToken;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}