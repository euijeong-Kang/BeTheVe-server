package com.betheve.betheve.gallery.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Embeddable
@RequiredArgsConstructor
public class GalleryImage {

    @Id
    @Column(insertable = false, updatable = false, nullable = false)
    private UUID imageId;

    private byte[] imageCn;

    private String originalName;

    private String pathName;

    private long imageSize;

    private LocalDateTime uploadDate;

    public GalleryImage(byte[] imageCn, String originalName, long imageSize) {
        this.imageId = UUID.randomUUID();
        this.imageCn = imageCn;
        this.originalName = originalName;
        this.imageSize = imageSize;
        this.uploadDate = LocalDateTime.now();
        this.setPathName();
    }

    public void setPathName() {
        this.pathName = this.imageId.toString() + "_" +this.originalName;
    }


}
