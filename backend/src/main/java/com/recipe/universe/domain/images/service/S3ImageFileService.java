package com.recipe.universe.domain.images.service;

import com.recipe.universe.domain.images.dto.ImageFileDto;
import com.recipe.universe.domain.images.dto.ResourceDto;
import com.recipe.universe.domain.images.entity.ImageFiles;
import com.recipe.universe.domain.images.repository.ImageFilesRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.net.MalformedURLException;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class S3ImageFileService {
    private final ImageFilesRepository filesRepository;
    private final S3Client s3Client;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucketName;

    @Transactional
    public void saveFile(MultipartFile file) {
        try {
            ImageFiles files = new ImageFiles(file.getOriginalFilename());
            Long id = filesRepository.save(files).getId();

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .contentType(file.getContentType())
                    .contentLength(file.getSize())
                    .key(id + "." + files.getExtension())
                    .build();
            RequestBody requestBody = RequestBody.fromBytes(file.getBytes());
            s3Client.putObject(putObjectRequest, requestBody);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<ImageFileDto> getAllFiles() {
        return filesRepository.findAll().stream().map(ImageFileDto::new).toList();
    }

    public ResourceDto getFileByFilename(Long fileId) {
        try {
            ImageFiles files = filesRepository.findById(fileId).orElseThrow(EntityNotFoundException::new);
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(files.getStorePath())
                    .build();
            ResponseInputStream<GetObjectResponse> object = s3Client.getObject(getObjectRequest);
            Resource r = new InputStreamResource(object);
            return new ResourceDto(r, files.getStorePath());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void deleteFile(Long fileId) {
        try {
            ImageFiles files = filesRepository.findById(fileId).orElseThrow(EntityNotFoundException::new);
            filesRepository.delete(files);
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(files.getStorePath())
                    .build();
            s3Client.deleteObject(deleteObjectRequest);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
