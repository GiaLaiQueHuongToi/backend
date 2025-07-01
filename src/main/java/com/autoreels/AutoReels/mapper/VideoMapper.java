package com.windowprogramming.ClothingStoreManager.mapper;

import com.windowprogramming.ClothingStoreManager.dto.request.CreateVideoRequest;
import com.windowprogramming.ClothingStoreManager.dto.response.VideoResponse;
import com.windowprogramming.ClothingStoreManager.entity.Video;
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
