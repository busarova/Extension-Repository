package com.telerik.extensionrepository.dto;

import com.telerik.extensionrepository.model.Extension;

import java.util.Date;

//User to transfer data with the rest service

public class ExtensionDTO {

    private int id;
    private String name;
    private String description;
    private String version;
    private String owner;
    private long numberOfDownloads;
    private String tags;
    private int approved;
    private String uploadDate;
    private int featured;
    private String gitRepoLink;
    private int openIssues;
    private int pullRequests;
    private Date lastCommitDate;
    private Date lastSuccessfulSync;

    public ExtensionDTO(Extension extension){

        this.id = extension.getId();
        this.name = extension.getName();
        this.description = extension.getDescription();
        this.version = extension.getVersion();
        this.owner = extension.getOwner();
        this.numberOfDownloads = extension.getNumberOfDownloads();
        this.tags = extension.getTags();
        this.approved = extension.getApproved();
        this.uploadDate = extension.getUploadDate().toString();
        this.featured = extension.getFeatured();
        this.gitRepoLink = extension.getGitExtensionInfo().getGitRepoLink();
        this.openIssues = extension.getGitExtensionInfo().getOpenIssues();
        this.pullRequests = extension.getGitExtensionInfo().getPullRequests();
        this.lastCommitDate = extension.getGitExtensionInfo().getLastCommitDate();
        this.lastSuccessfulSync = extension.getGitExtensionInfo().getLastSuccessfulSync();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
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

    public String getGitRepoLink() {
        return gitRepoLink;
    }

    public void setGitRepoLink(String gitRepoLink) {
        this.gitRepoLink = gitRepoLink;
    }

    public int getOpenIssues() {
        return openIssues;
    }

    public void setOpenIssues(int openIssues) {
        this.openIssues = openIssues;
    }

    public int getPullRequests() {
        return pullRequests;
    }

    public void setPullRequests(int pullRequests) {
        this.pullRequests = pullRequests;
    }

    public Date getLastCommitDate() {
        return lastCommitDate;
    }

    public void setLastCommitDate(Date lastCommitDate) {
        this.lastCommitDate = lastCommitDate;
    }

    public Date getLastSuccessfulSync() {
        return lastSuccessfulSync;
    }

    public void setLastSuccessfulSync(Date lastSuccessfulSync) {
        this.lastSuccessfulSync = lastSuccessfulSync;
    }
}
