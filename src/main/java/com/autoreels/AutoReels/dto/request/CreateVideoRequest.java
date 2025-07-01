package com.windowprogramming.ClothingStoreManager.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateVideoRequest {
    
    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    String title;
    
    String videoUrl;
    
    String status; // draft, published - defaults to draft
    
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    String description;
}
