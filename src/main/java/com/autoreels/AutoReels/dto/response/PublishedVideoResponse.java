package com.autoreels.AutoReels.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublishedVideoResponse {
    private Long id;
    private Long videoId;
    private String platform;
    private String externalId;
    private String url;
    private String publishedAt;
}