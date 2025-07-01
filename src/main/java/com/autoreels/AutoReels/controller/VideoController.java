package com.windowprogramming.ClothingStoreManager.controller;

import com.windowprogramming.ClothingStoreManager.dto.request.CreateVideoRequest;
import com.windowprogramming.ClothingStoreManager.dto.response.ApiResponse;
import com.windowprogramming.ClothingStoreManager.dto.response.PageResponse;
import com.windowprogramming.ClothingStoreManager.dto.response.VideoResponse;
import com.windowprogramming.ClothingStoreManager.service.video.VideoService;
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
}

