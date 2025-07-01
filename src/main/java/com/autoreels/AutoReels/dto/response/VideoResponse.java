package com.autoreels.AutoReels.dto.response;

import com.autoreels.AutoReels.entity.PublishedVideo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoResponse {
    Long id;
    String title;
    String videoUrl;
    String status;
    Long views = 0L;
    String description;
    String createdAt;
    List<PublishedVideoResponse> publishedVideos; // List of published videos associated with this video
}
