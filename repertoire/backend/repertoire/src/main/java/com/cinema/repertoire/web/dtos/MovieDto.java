package com.cinema.repertoire.web.dtos;

public class MovieDto {
    private String title;
    private String description;
    private String image;

    private Long versionId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public MovieDto(String title, String description, String image, Long versionId) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.versionId = versionId;
    }
}
