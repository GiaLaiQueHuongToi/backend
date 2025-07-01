package com.autoreels.AutoReels.mapper;

import com.autoreels.AutoReels.dto.response.PublishedVideoResponse;
import com.autoreels.AutoReels.entity.PublishedVideo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PublishedVideoMapper {
    PublishedVideoResponse toPublishedVideoResponse(PublishedVideo publishedVideo);
}
