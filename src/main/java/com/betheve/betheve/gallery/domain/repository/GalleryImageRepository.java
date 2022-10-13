package com.betheve.betheve.gallery.domain.repository;

import com.betheve.betheve.gallery.domain.entity.GalleryImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GalleryImageRepository extends JpaRepository<GalleryImage, UUID> {
}
