package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.model.GitExtensionInfo;

public interface GitService {
    GitExtensionInfo getGitDetails(String link);
}
