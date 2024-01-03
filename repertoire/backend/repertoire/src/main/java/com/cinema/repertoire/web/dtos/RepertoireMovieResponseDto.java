package com.cinema.repertoire.web.dtos;

public class RepertoireMovieResponseDto {
    private String title;
    private String description;
    private String image;
    private RepertoireDTO[] showings;

    public RepertoireDTO[] getShowings() {
        return showings;
    }
    public void setShowings(RepertoireDTO[] repertoires) {
        this.showings = repertoires;
    }

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
}
