package com.autoreels.AutoReels.controller;

import com.autoreels.AutoReels.dto.request.CreateVideoRequest;
import com.autoreels.AutoReels.dto.response.ApiResponse;
import com.autoreels.AutoReels.dto.response.PageResponse;
import com.autoreels.AutoReels.dto.response.PublishedVideoResponse;
import com.autoreels.AutoReels.dto.response.VideoResponse;
import com.autoreels.AutoReels.service.video.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VideoController {
    VideoService videoService;

    @GetMapping
    @Operation(summary = "Get all videos",
               description = "Fetches a paginated list of all videos available in the system.")
    public ApiResponse<PageResponse<VideoResponse>> getAllVideos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageResponse<VideoResponse> videoPage = videoService.getAllVideos(page, size);
        return ApiResponse.<PageResponse<VideoResponse>>builder()
                .data(videoPage)
                .build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get video by ID",
               description = "Fetches a specific video by its ID.")
    public ApiResponse<VideoResponse> getVideoById(@PathVariable Long id) {
        VideoResponse videoResponse = videoService.getVideoById(id);
        return ApiResponse.<VideoResponse>builder()
                .data(videoResponse)
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new video",
               description = "Creates a new video with the provided information.")
    public ApiResponse<VideoResponse> createVideo(@Valid @RequestBody CreateVideoRequest request) {
        VideoResponse videoResponse = videoService.createVideo(request);
        return ApiResponse.<VideoResponse>builder()
                .data(videoResponse)
                .message("Video created successfully")
                .build();
    }

    @PostMapping("/publish")
    @Operation(summary = "Publish a new video",
               description = "Publishes a new video with the provided information.")
    public ApiResponse<VideoResponse> publishVideo(@RequestParam Long videoId,
                                                   @RequestParam String publicUrl,
                                                   @RequestParam String publicId) {
        VideoResponse videoResponse = videoService.publishVideo(videoId, publicUrl, publicId);
        return ApiResponse.<VideoResponse>builder()
                .data(videoResponse)
                .message("Video published successfully")
                .build();
    }

    @GetMapping("/{id}/published")
    @Operation(summary = "Get published video information",
            description = "Retrieves the publication details for a specific video by its ID.")
    public ApiResponse<PublishedVideoResponse> getPublishedVideo(@PathVariable Long id) {
        PublishedVideoResponse response = videoService.getPublishedVideoByVideoId(id);
        return ApiResponse.<PublishedVideoResponse>builder()
                .data(response)
                .build();
    }



}

