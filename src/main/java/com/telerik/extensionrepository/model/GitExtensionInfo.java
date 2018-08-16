package com.telerik.extensionrepository.model;

import javax.persistence.*;

@Entity
@Table(name = "git_extension_info")
public class GitExtensionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gitExtensionInfoID")
    private int id;

    @Column(name = "openIssues")
    private int openIssues;

    @Column(name = "pullRequests")
    private int pullRequests;

    @Column(name = "lastCommitDate")
    private String lastCommitDate;

    @Column(name = "gitRepoLink")
    private String gitRepoLink;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLastCommitDate() {
        return lastCommitDate;
    }

    public void setLastCommitDate(String lastCommitDate) {
        this.lastCommitDate = lastCommitDate;
    }

    public String getGitRepoLink() {
        return gitRepoLink;
    }

    public void setGitRepoLink(String gitRepoLink) {
        this.gitRepoLink = gitRepoLink;
    }
}
