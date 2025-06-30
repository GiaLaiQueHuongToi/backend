package com.windowprogramming.ClothingStoreManager.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.windowprogramming.ClothingStoreManager.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video,Long> {
    Page<Video> findAllByUserId(Long userId, Pageable pageable);

    Optional<Video> findByIdAndUserId(Long id, Long userId);
}
