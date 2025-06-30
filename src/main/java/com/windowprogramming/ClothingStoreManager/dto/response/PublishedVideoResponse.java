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
public class PublishedVideoResponse {
    Long id;
    String title;
    String videoUrl; // cloudinary url
    String status; // draft, published
    String thumbnail;
    Long views;
    String description;
    String createdAt;
    String platform; // e.g., YouTube, TikTok, Facebook
    String externalId; // ID on external platform (YouTube, TikTok, Facebook)
    String url; // URL of published video
    String publishedAt; // DateTime when the video was published
}
