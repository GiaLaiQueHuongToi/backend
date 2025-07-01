package com.autoreels.AutoReels.service;

import com.cloudinary.Cloudinary;
import com.autoreels.AutoReels.dto.response.CloudinaryResponse;
import com.autoreels.AutoReels.exception.AppException;
import com.autoreels.AutoReels.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;

    @Transactional
    public CloudinaryResponse uploadFile(final MultipartFile file, final String fileName) {
        try {
            Map<String, Object> uploadOptions = Map.of(
                    "public_id", "ClothingStoreManager/" + fileName,
                    "folder", "ClothingStoreManager"  // Optional, Cloudinary will recognize the folder based on public_id
            );

            // Upload the file to Cloudinary
            Map result = cloudinary.uploader().upload(file.getBytes(), uploadOptions);
            final String url      = (String) result.get("secure_url");
            final String publicId = (String) result.get("public_id");
            return CloudinaryResponse.builder().publicId(publicId).url(url)
                    .build();

        } catch (final Exception e) {
            throw new AppException(ErrorCode.FILE_UPLOAD_FAILED);
        }
    }
}
