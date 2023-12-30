package com.cinema.definelocal.db.common.models;

import jakarta.persistence.*;

@Entity
@Table(name = "LANGUAGE_VERSION")
public class LanguageVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "version_id")
    private Long id;
    private String dubbing;
    private String lector;
    private String subtitles;

    @Column(name = "VERSION_NAME")
    private String versionName;
}
