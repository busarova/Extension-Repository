package com.telerik.extensionrepository.model.dto;

public class ExtensionForm {

    private String name;
    private String description;
    private String version;
    private String tags;
    private String githubLink;

    public ExtensionForm(){}

    public ExtensionForm(String name, String description, String version, String tags, String githubLink) {
        this.name = name;
        this.description = description;
        this.version = version;
        this.tags = tags;
        this.githubLink = githubLink;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }
}
