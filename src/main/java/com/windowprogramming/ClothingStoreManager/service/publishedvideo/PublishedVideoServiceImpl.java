package com.windowprogramming.ClothingStoreManager.service.publishedvideo;

import com.windowprogramming.ClothingStoreManager.repository.PublishedVideoRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PublishedVideoServiceImpl implements PublishedVideoService{
    PublishedVideoRepository publishedVideoRepository;
}
