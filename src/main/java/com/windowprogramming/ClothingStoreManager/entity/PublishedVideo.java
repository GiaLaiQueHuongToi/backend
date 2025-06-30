package com.windowprogramming.ClothingStoreManager.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity(name = "published_videos")
@Table(name = "published_videos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PublishedVideo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name="video_id", nullable = false)
    Video video;

    @Column(name = "platform", nullable = false)
    String platform; // e.g., YouTube, TikTok, Facebook

    @Column(name = "external_id", nullable = false)
    String externalId; // ID on external platform (YouTube, TikTok, Facebook)

    @Column(name = "url", nullable = false)
    String url; // URL of published video

    @Column
    LocalDateTime publishedAt;

}
