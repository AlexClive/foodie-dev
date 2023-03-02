package com.imooc.resource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
@PropertySource("classpath:file-upload-dev-win.properties")
public class FileUpload {
    private String imageUserFaceLocation;
    private String imageServiceUrl;

    public String getImageUserFaceLocation() {
        return imageUserFaceLocation;
    }

    public void setImageUserFaceLocation(String imageUserFaceLocation) {
        this.imageUserFaceLocation = imageUserFaceLocation;
    }

    public String getImageServiceUrl() {
        return imageServiceUrl;
    }

    public void setImageServiceUrl(String imageServiceUrl) {
        this.imageServiceUrl = imageServiceUrl;
    }
}
