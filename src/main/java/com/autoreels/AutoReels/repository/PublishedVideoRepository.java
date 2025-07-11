package com.autoreels.AutoReels.repository;

import com.autoreels.AutoReels.entity.PublishedVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublishedVideoRepository extends JpaRepository<PublishedVideo, Long> {
    Optional<PublishedVideo> findByVideoId(Long videoId);

}
