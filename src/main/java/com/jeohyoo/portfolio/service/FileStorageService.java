package com.jeohyoo.portfolio.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    @Value("${file.path}")
    private String filePath;

    public void saveFile(MultipartFile file) throws IOException {
        // 저장할 파일 경로
        Path uploadPath = Paths.get(filePath);

        // 만약 파일 경로가 없다면? 만듦
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 맞는 파일 경로로 만들어줌 (문법에 어긋나는 경우)
        Path storedFilePath = uploadPath.resolve(file.getOriginalFilename());

        // 파일 저장
        file.transferTo(storedFilePath.toFile());
    }
}
