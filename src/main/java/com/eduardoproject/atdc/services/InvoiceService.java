package com.eduardoproject.atdc.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class InvoiceService {
    @Value("${invoice-path}")
    private String root;

    @Value("${invoice-directory}")
    private String fileDirectory;

    public String saveFile(MultipartFile file) {
        this.save(this.fileDirectory, file);
        return  file.getOriginalFilename();
    }

    public void save(String directory, MultipartFile file) {
        Path directoryPath = Paths.get(this.root, directory).toAbsolutePath().normalize();
        Path filePath = directoryPath.resolve(file.getOriginalFilename());

        try {
            Files.createDirectories(directoryPath);
            file.transferTo(filePath.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
        }
    }
}
