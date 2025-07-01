package com.autoreels.AutoReels.mapper;

import com.autoreels.AutoReels.dto.request.CreateVideoRequest;
import com.autoreels.AutoReels.dto.response.VideoResponse;
import com.autoreels.AutoReels.entity.Video;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VideoMapper {
    VideoResponse toVideoResponse(Video video);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "views", constant = "0L")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "status", expression = "java(request.getStatus() != null ? request.getStatus() : \"draft\")")
    Video toVideo(CreateVideoRequest request);
}
