package com.autoreels.AutoReels.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "video")
    List<PublishedVideo> publishedVideo;

    @Column
    String title;

    @Column
    String videoUrl;

    @Column
    String status; // published, private

    @Column
    Long views = 0L;

    @Column
    String description;

    @Column(nullable = false)
    LocalDateTime createdAt = LocalDateTime.now();
}
