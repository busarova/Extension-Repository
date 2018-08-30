package com.telerik.extensionrepository.data.base;

import com.telerik.extensionrepository.model.GitExtensionInfo;

import java.util.List;

public interface GitExtensionInfoRepository {

    List<GitExtensionInfo> getAllGitInfo();

    GitExtensionInfo getGitInfoById(int id);

    void updateGitInfo(GitExtensionInfo gitExtensionInfo);
}
