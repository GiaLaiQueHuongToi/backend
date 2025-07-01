package com.autoreels.AutoReels.service.video;

import com.autoreels.AutoReels.dto.request.CreateVideoRequest;
import com.autoreels.AutoReels.dto.response.PageResponse;
import com.autoreels.AutoReels.dto.response.VideoResponse;
import com.autoreels.AutoReels.entity.User;
import com.autoreels.AutoReels.entity.Video;
import com.autoreels.AutoReels.exception.AppException;
import com.autoreels.AutoReels.exception.ErrorCode;
import com.autoreels.AutoReels.mapper.VideoMapper;
import com.autoreels.AutoReels.repository.UserRepository;
import com.autoreels.AutoReels.repository.VideoRepository;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VideoServiceImpl implements VideoService{
    VideoRepository videoRepository;
    VideoMapper videoMapper;
    UserRepository userRepository;

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

    @Override
    public VideoResponse createVideo(CreateVideoRequest request) {
        // Get current user from security context
        SecurityContext context = SecurityContextHolder.getContext();
        User user = null;
        
        if (context.getAuthentication() != null && context.getAuthentication().getPrincipal() instanceof User) {
            // Principal is a User object
            user = (User) context.getAuthentication().getPrincipal();
        } else if (context.getAuthentication() != null && context.getAuthentication().getPrincipal() instanceof Long) {
            // Principal is a Long (user ID)
            Long userId = (Long) context.getAuthentication().getPrincipal();
            user = userRepository.findById(userId)
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        }
        
        if (user == null) {
            // For demo/testing: use the first available user when no authentication
            user = userRepository.findAll().stream().findFirst()
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        }

        // Map request to entity
        Video video = videoMapper.toVideo(request);
        video.setUser(user);

        // Ensure fields don't exceed database limits
        if (video.getTitle() != null && video.getTitle().length() > 255) {
            video.setTitle(video.getTitle().substring(0, 252) + "...");
        }
        
        if (video.getDescription() != null && video.getDescription().length() > 1000) {
            video.setDescription(video.getDescription().substring(0, 997) + "...");
        }

        // Save video
        Video savedVideo = videoRepository.save(video);

        // Convert entity to response
        VideoResponse response = videoMapper.toVideoResponse(savedVideo);
        response.setCreatedAt(savedVideo.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        
        return response;
    }
}
