package com.telerik.extensionrepository.model;

import javax.persistence.*;

@Entity
@Table(name = "extension")
public class Extension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "extension_id")
    private int id;

    @Column(name = "name")
    public String name;

    @Column(name = "description")
    private String description;

    @Column(name = "version")
    private String version;

    @Column(name = "owner")
    private String owner;

    @Column(name = "number_of_downloads")
    private long numberOfDownloads;

    @Column(name = "tags")
    private String tags;

    @Column(name = "approved")
    private int approved;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "git_id")
    private GitExtensionInfo gitExtensionInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private UploadFile uploadFile;

    @Column(name = "upload_date")
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

    public GitExtensionInfo getGitExtensionInfo() {
        return gitExtensionInfo;
    }

    public void setGitExtensionInfo(GitExtensionInfo gitExtensionInfo) {
        this.gitExtensionInfo = gitExtensionInfo;
    }

    public UploadFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(UploadFile uploadFile) {
        this.uploadFile = uploadFile;
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

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public String isItApproved(){
        if(getApproved() == 0){
            return "Pending Approval";
        }else{
            return "Approved";
        }
    }

    public String isItFeatured(){
        if(getFeatured() == 0){
            return "Not Featured";
        }else{
            return "Featured";
        }
    }
}
