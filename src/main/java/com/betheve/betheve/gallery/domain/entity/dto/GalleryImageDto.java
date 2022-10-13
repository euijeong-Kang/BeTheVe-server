package com.betheve.betheve.gallery.domain.entity.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class GalleryImageDto {
    private MultipartFile imageObject;
}
