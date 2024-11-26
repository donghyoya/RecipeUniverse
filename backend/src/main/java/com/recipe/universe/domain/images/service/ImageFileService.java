package com.recipe.universe.domain.images.service;

import com.recipe.universe.domain.images.dto.ImageFileDto;
import com.recipe.universe.domain.images.dto.ResourceDto;
import com.recipe.universe.domain.images.entity.ImageFiles;
import com.recipe.universe.domain.images.repository.ImageFilesRepository;
import com.recipe.universe.global.file.access.FileSystemAccessObject;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ImageFileService {
    private final ImageFilesRepository filesRepository;
    private final FileSystemAccessObject fileSAO;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucketName;

    @Transactional
    public ImageFileDto saveFile(MultipartFile file) {
        ImageFiles files = new ImageFiles(file.getOriginalFilename());
        fileSAO.save(files.getStorePath(), file);
        return new ImageFileDto(files);
    }

    public ResourceDto loadFileByFilename(String filename){
        Resource resource = fileSAO.load(filename);
        return new ResourceDto(resource, filename);
    }

    public ResourceDto getFileByFileId(Long fileId) {
        ImageFiles files = filesRepository.findById(fileId).orElseThrow(EntityNotFoundException::new);
        Resource resource = fileSAO.load(files.getStorePath());
        return new ResourceDto(resource, files.getStorePath());
    }

    @Transactional
    public void deleteFile(Long fileId) {
        ImageFiles files = filesRepository.findById(fileId).orElseThrow(EntityNotFoundException::new);
        filesRepository.delete(files);
        fileSAO.delete(files.getStorePath());
    }
}
