package com.camping.erp.domain.gallery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {

    @Query("select g from Gallery g left join fetch g.images order by g.id desc")
    List<Gallery> findAllWithImages();
}
