package com.windowprogramming.ClothingStoreManager.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity(name = "videos")
@Table(name = "videos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Video {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    User user;

    @Column
    String title;

    @Column
    String videoUrl;

    @Column
    String status; // draft, published

//    @Column
//    String thumbnail;

    @Column
    Long views = 0L;

    @Column
    String description;

    @Column(nullable = false)
    LocalDateTime createdAt = LocalDateTime.now();
}
