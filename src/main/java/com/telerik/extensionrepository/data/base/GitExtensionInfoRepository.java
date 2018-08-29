package com.telerik.extensionrepository.data.base;

import com.telerik.extensionrepository.model.GitExtensionInfo;

public interface GitExtensionInfoRepository {

    GitExtensionInfo getGitInfoById(int id);

    void updateGitInfo(GitExtensionInfo gitExtensionInfo);
}
