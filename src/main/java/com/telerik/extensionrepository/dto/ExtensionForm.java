package com.telerik.extensionrepository.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ExtensionForm {

    private static final String FIELD_REQUIRED = "Required field";
    private static final String NAME_SIZE = "Extension name should be minimum 3 characters";

    @NotNull
    @NotEmpty(message = FIELD_REQUIRED)
    @Size(min = 3, message = NAME_SIZE)
    private String name;


    private String description;
    private String version;
    private String tags;

    @NotNull
    @NotEmpty(message = FIELD_REQUIRED)
    private String githubLink;

    public ExtensionForm(){}

    public ExtensionForm(@NotNull @NotEmpty @Size(min = 3, message = NAME_SIZE) String name, String description, String version, String tags, @NotNull @NotEmpty String githubLink) {
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
