package com.windowprogramming.ClothingStoreManager.service.video;

import com.windowprogramming.ClothingStoreManager.dto.request.CreateVideoRequest;
import com.windowprogramming.ClothingStoreManager.dto.response.PageResponse;
import com.windowprogramming.ClothingStoreManager.dto.response.VideoResponse;

public interface VideoService {
    PageResponse<VideoResponse> getAllVideos(int page, int size);

    VideoResponse getVideoById(Long id);

    VideoResponse createVideo(CreateVideoRequest request);
}
