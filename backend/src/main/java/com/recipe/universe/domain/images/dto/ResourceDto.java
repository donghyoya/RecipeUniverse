package com.recipe.universe.domain.images.dto;

import lombok.Data;
import org.springframework.core.io.Resource;

import java.net.MalformedURLException;

@Data
public class ResourceDto {
    private ImageFileDto filesDto;
    private Resource resource;
    private String header;

    public ResourceDto(Resource resource, String fileName) throws MalformedURLException {
        this.resource = resource;
        this.header = new StringBuilder()
                .append("attachment;")
                .append("filename=\"")
                .append(fileName)
                .append("\"").toString();

    }

}
