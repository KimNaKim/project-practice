package com.camping.erp.domain.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImageService {

    private final ImageRepository imageRepository;
    private final String uploadDir = "./uploads";

    @Transactional
    public List<Image> uploadImages(List<MultipartFile> files) {
        List<Image> images = new ArrayList<>();
        if (files == null || files.isEmpty()) return images;

        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;
            
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);

            try {
                if (!Files.exists(Paths.get(uploadDir))) {
                    Files.createDirectories(Paths.get(uploadDir));
                }
                Files.write(filePath, file.getBytes());

                Image image = Image.builder()
                        .fileName(file.getOriginalFilename())
                        .filePath("/upload/" + fileName)
                        .build();
                
                images.add(image);
            } catch (IOException e) {
                throw new RuntimeException("파일 저장 중 오류가 발생했습니다: " + e.getMessage());
            }
        }
        return images;
    }

    @Transactional
    public void deleteImages(List<Image> images) {
        if (images == null) return;
        for (Image image : images) {
            // 실제 파일 삭제 로직 (선택 사항)
            // Path filePath = Paths.get("." + image.getFilePath());
            // Files.deleteIfExists(filePath);
            imageRepository.delete(image);
        }
    }
}
