package com.autoreels.AutoReels.service.video;

import com.autoreels.AutoReels.dto.request.CreateVideoRequest;
import com.autoreels.AutoReels.dto.response.PageResponse;
import com.autoreels.AutoReels.dto.response.PublishedVideoResponse;
import com.autoreels.AutoReels.dto.response.VideoResponse;
import com.autoreels.AutoReels.entity.PublishedVideo;
import com.autoreels.AutoReels.entity.User;
import com.autoreels.AutoReels.entity.Video;
import com.autoreels.AutoReels.exception.AppException;
import com.autoreels.AutoReels.exception.ErrorCode;
import com.autoreels.AutoReels.mapper.VideoMapper;
import com.autoreels.AutoReels.repository.PublishedVideoRepository;
import com.autoreels.AutoReels.repository.UserRepository;
import com.autoreels.AutoReels.repository.VideoRepository;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VideoServiceImpl implements VideoService{
    VideoRepository videoRepository;
    VideoMapper videoMapper;
    UserRepository userRepository;
    PublishedVideoRepository publishedVideoRepository;

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

//    @Override
//    public PageResponse<VideoResponse> getAllVideos(int page, int size) {
//        List<VideoResponse> mockVideos = Arrays.asList(
//                VideoResponse.builder()
//                        .id(1L)
//                        .title("Top 10 AI Trends in 2025")
//                        .videoUrl("https://res.cloudinary.com/ddw1pv5un/video/upload/v1751248056/SampleVideo_1280x720_1mb_tvmarb.mp4")
//                        .status("published")
//                        .views(1245L)
//                        .description("Explore the top AI trends shaping the future.")
//                        .createdAt("2025-05-28")
//                        .build(),
//                VideoResponse.builder()
//                        .id(2L)
//                        .title("How to Learn Programming Fast")
//                        .videoUrl("https://res.cloudinary.com/ddw1pv5un/video/upload/v1751248056/SampleVideo_1280x720_1mb_tvmarb.mp4")
//                        .status("draft")
//                        .views(876L)
//                        .description("Tips and tricks to accelerate your programming journey.")
//                        .createdAt("2025-05-25")
//                        .build(),
//                VideoResponse.builder()
//                        .id(3L)
//                        .title("The Future of Web Development")
//                        .videoUrl("https://res.cloudinary.com/ddw1pv5un/video/upload/v1751248056/SampleVideo_1280x720_1mb_tvmarb.mp4")
//                        .status("published")
//                        .views(543L)
//                        .description("Discover the latest trends in web development.")
//                        .createdAt("2025-05-20")
//                        .build()
//        );
//
//        return PageResponse.<VideoResponse>builder()
//                .data(mockVideos)
//                .page(page)
//                .size(size)
//                .totalElements(3L)
//                .totalPages(1)
//                .build();
//    }

    @Override
    public PageResponse<VideoResponse> getAllVideos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        // Get user id from security context
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();

        Page<Video> videoPage = videoRepository.findAllByUserId(user.getId(), pageable);
        List<Video> videos = videoPage.getContent();

        return PageResponse.<VideoResponse>builder()
                .data(videos.stream()
                        .map(video -> {
                            VideoResponse response = videoMapper.toVideoResponse(video);
                            response.setCreatedAt(formatDate(video.getCreatedAt()));
                            return response;
                        })
                        .collect(Collectors.toList()))
                .page(page)
                .size(size)
                .totalElements(videoPage.getTotalElements())
                .totalPages(videoPage.getTotalPages())
                .build();
    }

    @Override
    public VideoResponse getVideoById(Long id) {
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();

        Video video = videoRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new AppException(ErrorCode.VIDEO_NOT_FOUND));

        VideoResponse response = videoMapper.toVideoResponse(video);
        response.setCreatedAt(formatDate(video.getCreatedAt()));
        return response;
    }

    // Utility method for formatting dates consistently
    private String formatDate(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }



//    @Override
//    public VideoResponse getVideoById(Long id) {
//        SecurityContext context = SecurityContextHolder.getContext();
//        Long userId = context.getAuthentication() != null ? (Long) context.getAuthentication().getPrincipal() : null;
//        Video video = videoRepository.findByIdAndUserId(id, userId)
//                .orElseThrow(() -> new AppException(ErrorCode.VIDEO_NOT_FOUND));
//        return videoMapper.toVideoResponse(video);
//    }

//    @Override
//    public VideoResponse getVideoById(Long id){
//        return VideoResponse.builder()
//                .id(3L)
//                .title("The Future of Web Development")
//                .videoUrl("https://res.cloudinary.com/ddw1pv5un/video/upload/v1751248056/SampleVideo_1280x720_1mb_tvmarb.mp4")
//                .status("published")
//                .views(543L)
//                .description("Discover the latest trends in web development.")
//                .createdAt("2025-05-20")
//                .build();
//    }

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

    @Override
    public VideoResponse publishVideo(Long videoId, String publicUrl, String publicId) {
        // Get current user from security context
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();

        // Find video by id and check if user owns it
        Video video = videoRepository.findByIdAndUserId(videoId, user.getId())
                .orElseThrow(() -> new AppException(ErrorCode.VIDEO_NOT_FOUND));

        // Create a new PublishedVideo record
        PublishedVideo publishedVideo = PublishedVideo.builder()
                .video(video)
                .externalId(publicId)
                .url(publicUrl)
                .publishedAt(LocalDateTime.now())
                .build();

        // Save the published video record
        publishedVideoRepository.save(publishedVideo);

        // Update video status to published
        video.setStatus("published");
        Video savedVideo = videoRepository.save(video);

        // Convert entity to response
        VideoResponse response = videoMapper.toVideoResponse(savedVideo);
        response.setCreatedAt(formatDate(savedVideo.getCreatedAt()));

        return response;
    }

    @Override
    public PublishedVideoResponse getPublishedVideoByVideoId(Long videoId) {
        // Get current user from security context
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();

        // First verify the video exists and belongs to the user
        Video video = videoRepository.findByIdAndUserId(videoId, user.getId())
                .orElseThrow(() -> new AppException(ErrorCode.VIDEO_NOT_FOUND));

        // Find the published video record
        PublishedVideo publishedVideo = publishedVideoRepository.findByVideoId(videoId)
                .orElseThrow(() -> new AppException(ErrorCode.PUBLISHED_VIDEO_NOT_FOUND));

        // Convert to response DTO
        return PublishedVideoResponse.builder()
                .id(publishedVideo.getId())
                .videoId(videoId)
                .externalId(publishedVideo.getExternalId())
                .url(publishedVideo.getUrl())
                .publishedAt(formatDate(publishedVideo.getPublishedAt()))
                .build();
    }


}
