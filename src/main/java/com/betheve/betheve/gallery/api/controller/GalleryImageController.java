package com.betheve.betheve.gallery.api.controller;

import com.betheve.betheve.gallery.domain.entity.dto.GalleryImageDto;
import com.betheve.betheve.gallery.domain.service.GalleryImageServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class GalleryImageController {

    private final GalleryImageServiceImpl galleryImageService;

    @Autowired
    public GalleryImageController(GalleryImageServiceImpl galleryImageService) {
        this.galleryImageService = galleryImageService;
    }

    @Operation(summary = "이미지 업로드", description = "이미지를 업로드 합니다.")
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Object> saveImage(@RequestPart MultipartFile galleryImageDto) {

        galleryImageService.saveImage(galleryImageDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage() {
        return galleryImageService.getImage();

    }
}
