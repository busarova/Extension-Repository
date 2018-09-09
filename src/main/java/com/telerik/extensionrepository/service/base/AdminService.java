package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.exceptions.GithubSyncException;
import com.telerik.extensionrepository.model.Admin;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.User;
import com.telerik.extensionrepository.exceptions.RepositoryException;

import java.util.List;

public interface AdminService {

    int refreshAllGitHubInfo() throws RepositoryException, GithubSyncException;

    List<Extension> getNotApprovedExt();

    Extension approveExt(int id);

    Extension removeApproval(int id);

    List<User> getAllUsers();

    User getUser(String name);

    User disableUser(String name);

    User enableUser(String name);

    Extension featureExtension(int id);

    Extension unFeatureExtension(int id);

    Extension deleteExtension(int id);

    Admin getAdminInfo();

    void refreshExtensionGitInfo(int id);

    int emptyTagCheck();

    String getScheduledTaskTime();

}
