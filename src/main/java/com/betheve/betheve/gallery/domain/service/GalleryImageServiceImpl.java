package com.betheve.betheve.gallery.domain.service;

import com.betheve.betheve.gallery.domain.entity.GalleryImage;
import com.betheve.betheve.gallery.domain.repository.GalleryImageRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;


@Service
@Transactional
public class GalleryImageServiceImpl {

    private final GalleryImageRepository galleryImageRepository;

    @Autowired
    public GalleryImageServiceImpl(GalleryImageRepository galleryImageRepository) {
        this.galleryImageRepository = galleryImageRepository;
    }

    public void saveImage(MultipartFile galleryImageDto){

        try {
            byte[] encodeBase64 = Base64.encodeBase64(galleryImageDto.getBytes());

            GalleryImage newImage = new GalleryImage(encodeBase64, galleryImageDto.getOriginalFilename(), galleryImageDto.getSize());

            galleryImageRepository.save(newImage);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public byte[] getImage() {
        return Base64.decodeBase64(galleryImageRepository.findAll().get(0).getImageCn());
    }

}
