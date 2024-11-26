package com.recipe.universe.domain.images.service.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileSystemAccessObject {
    public void save(String path, MultipartFile file);
    public Resource load(String path);

    public void delete(String path);

}
