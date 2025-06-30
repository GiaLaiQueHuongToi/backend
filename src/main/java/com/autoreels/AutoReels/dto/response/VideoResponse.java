package com.windowprogramming.ClothingStoreManager.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoResponse {
    Long id;
    String title;
    String videoUrl;
    String status; // draft, published
    Long views;
    String description;
    String createdAt;
}
