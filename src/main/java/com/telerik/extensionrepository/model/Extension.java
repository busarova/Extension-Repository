package com.telerik.extensionrepository.model;

import javax.persistence.*;

@Entity
@Table(name = "extension")
public class Extension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "extensionID")
    private int id;

    @Column(name = "name")
    public String name;

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

    /*@OneToOne
    @JoinColumn(name = "downloadFileID")
    private DownloadFile downloadFile;*/

    /*@OneToOne
    @JoinColumn(name = "gitExtensionInfoID")
    private GitExtensionInfo gitExtensionInfo;*/

    @Column(name = "uploadDate")
    private String uploadDate;

    @Column(name = "featured")
    private int featured;

    public Extension(){}

    public Extension(String name, String description, String version, String owner, long numberOfDownloads, String tags, String uploadDate, int featured) {
        this.name = name;
        this.description = description;
        this.version = version;
        this.owner = owner;
        this.numberOfDownloads = numberOfDownloads;
        this.tags = tags;
        this.uploadDate = uploadDate;
        this.featured = featured;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public DownloadFile getDownloadFile() {
        return downloadFile;
    }

    public void setDownloadFile(DownloadFile downloadFile) {
        this.downloadFile = downloadFile;
    }*/

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


    /*public GitExtensionInfo getGitExtensionInfo() {
        return gitExtensionInfo;
    }

    public void setGitExtensionInfo(GitExtensionInfo gitExtensionInfo) {
        this.gitExtensionInfo = gitExtensionInfo;
    }*/


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
