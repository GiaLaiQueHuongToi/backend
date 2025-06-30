package com.windowprogramming.ClothingStoreManager.mapper;

import com.windowprogramming.ClothingStoreManager.dto.response.PublishedVideoResponse;
import com.windowprogramming.ClothingStoreManager.entity.PublishedVideo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PublishedVideoMapper {
    PublishedVideoResponse toPublishedVideoResponse(PublishedVideo publishedVideo);
}
