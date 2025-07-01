package com.autoreels.AutoReels.repository;

import com.autoreels.AutoReels.entity.PublishedVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishedVideoRepository extends JpaRepository<PublishedVideo, Long> {
}
