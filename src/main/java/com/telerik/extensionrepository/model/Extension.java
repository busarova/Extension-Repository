package com.telerik.extensionrepository.model;

import javax.persistence.*;

@Entity
@Table(name = "extension")
public class Extension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "version")
    private String version;

    @Column(name = "owner")
    private String owner;

    @Column(name = "numberOfDownloads")
    private long numberOfDownloads;

    @Column(name = "tags")
    private String tags;


    private GitExtensionInfo gitExtensionInfo;


    private String uploadDate;


    private int featured;


    private int downloadFileID;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDownloadFileID() {
        return downloadFileID;
    }

    public void setDownloadFileID(int downloadFileID) {
        this.downloadFileID = downloadFileID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public long getNumberOfDownloads() {
        return numberOfDownloads;
    }

    public void setNumberOfDownloads(long numberOfDownloads) {
        this.numberOfDownloads = numberOfDownloads;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public GitExtensionInfo getGitExtensionInfo() {
        return gitExtensionInfo;
    }

    public void setGitExtensionInfo(GitExtensionInfo gitExtensionInfo) {
        this.gitExtensionInfo = gitExtensionInfo;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public int getFeatured() {
        return featured;
    }

    public void setFeatured(int featured) {
        this.featured = featured;
    }


}
