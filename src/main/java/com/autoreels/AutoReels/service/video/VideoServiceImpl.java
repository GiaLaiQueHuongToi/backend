package com.windowprogramming.ClothingStoreManager.service.video;

import com.windowprogramming.ClothingStoreManager.dto.response.PageResponse;
import com.windowprogramming.ClothingStoreManager.dto.response.VideoResponse;
import com.windowprogramming.ClothingStoreManager.entity.Video;
import com.windowprogramming.ClothingStoreManager.exception.AppException;
import com.windowprogramming.ClothingStoreManager.exception.ErrorCode;
import com.windowprogramming.ClothingStoreManager.mapper.VideoMapper;
import com.windowprogramming.ClothingStoreManager.repository.VideoRepository;
import io.jsonwebtoken.lang.Collections;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VideoServiceImpl implements VideoService{
    VideoRepository videoRepository;
    VideoMapper videoMapper;

//    @Override
//    public PageResponse<VideoResponse> getAllVideos(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
//
//        // get user id from security contex
//        SecurityContext context = SecurityContextHolder.getContext();
//        Long userId = context.getAuthentication() != null ? (Long) context.getAuthentication().getPrincipal() : null;
//
//        Page<Video> videoPage = videoRepository.findAllByUserId(userId, pageable);
//        List<Video> videos = videoPage.getContent();
//
//        return PageResponse.<VideoResponse>builder()
//                .data(videos.stream().map(videoMapper::toVideoResponse).collect(Collectors.toList()))
//                .page(page)
//                .size(size)
//                .totalElements(videoPage.getTotalElements())
//                .totalPages(videoPage.getTotalPages())
//                .build();
//    }

    @Override
    public PageResponse<VideoResponse> getAllVideos(int page, int size) {
        List<VideoResponse> mockVideos = Arrays.asList(
                VideoResponse.builder()
                        .id(1L)
                        .title("Top 10 AI Trends in 2025")
                        .videoUrl("https://res.cloudinary.com/ddw1pv5un/video/upload/v1751248056/SampleVideo_1280x720_1mb_tvmarb.mp4")
                        .status("published")
                        .views(1245L)
                        .description("Explore the top AI trends shaping the future.")
                        .createdAt("2025-05-28")
                        .build(),
                VideoResponse.builder()
                        .id(2L)
                        .title("How to Learn Programming Fast")
                        .videoUrl("https://res.cloudinary.com/ddw1pv5un/video/upload/v1751248056/SampleVideo_1280x720_1mb_tvmarb.mp4")
                        .status("draft")
                        .views(876L)
                        .description("Tips and tricks to accelerate your programming journey.")
                        .createdAt("2025-05-25")
                        .build(),
                VideoResponse.builder()
                        .id(3L)
                        .title("The Future of Web Development")
                        .videoUrl("https://res.cloudinary.com/ddw1pv5un/video/upload/v1751248056/SampleVideo_1280x720_1mb_tvmarb.mp4")
                        .status("published")
                        .views(543L)
                        .description("Discover the latest trends in web development.")
                        .createdAt("2025-05-20")
                        .build()
        );

        return PageResponse.<VideoResponse>builder()
                .data(mockVideos)
                .page(page)
                .size(size)
                .totalElements(3L)
                .totalPages(1)
                .build();
    }

//    @Override
//    public VideoResponse getVideoById(Long id) {
//        SecurityContext context = SecurityContextHolder.getContext();
//        Long userId = context.getAuthentication() != null ? (Long) context.getAuthentication().getPrincipal() : null;
//        Video video = videoRepository.findByIdAndUserId(id, userId)
//                .orElseThrow(() -> new AppException(ErrorCode.VIDEO_NOT_FOUND));
//        return videoMapper.toVideoResponse(video);
//    }

    @Override
    public VideoResponse getVideoById(Long id){
        return VideoResponse.builder()
                .id(3L)
                .title("The Future of Web Development")
                .videoUrl("https://res.cloudinary.com/ddw1pv5un/video/upload/v1751248056/SampleVideo_1280x720_1mb_tvmarb.mp4")
                .status("published")
                .views(543L)
                .description("Discover the latest trends in web development.")
                .createdAt("2025-05-20")
                .build();
    }
}
