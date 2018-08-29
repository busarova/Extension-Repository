package com.telerik.extensionrepository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "git_extension_info")
public class GitExtensionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "git_id")
    private int id;

    @Column(name = "open_issues")
    private int openIssues;

    @Column(name = "pull_requests")
    private int pullRequests;

    @Column(name = "last_commit_date")
    private String lastCommitDate;

    @Column(name = "git_repo_link")
    private String gitRepoLink;

    @OneToOne(mappedBy = "gitExtensionInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Extension extension;


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

    public Extension getExtension() {
        return extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
    }
}
