package com.autoreels.AutoReels.service.publishedvideo;

import com.autoreels.AutoReels.repository.PublishedVideoRepository;
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
