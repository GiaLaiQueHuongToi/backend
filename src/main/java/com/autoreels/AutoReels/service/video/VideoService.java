package com.autoreels.AutoReels.service.video;

import com.autoreels.AutoReels.dto.request.CreateVideoRequest;
import com.autoreels.AutoReels.dto.response.PageResponse;
import com.autoreels.AutoReels.dto.response.VideoResponse;

public interface VideoService {
    PageResponse<VideoResponse> getAllVideos(int page, int size);

    VideoResponse getVideoById(Long id);

    VideoResponse createVideo(CreateVideoRequest request);
}
