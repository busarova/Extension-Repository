package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.model.Admin;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.User;

import java.util.List;

public interface AdminService {

    int refreshAllGitHubInfo();

    List<Extension> getUnnaprovedExt();

    void approveExt(int id);

    void removeApproval(int id);

    List<User> getAllUsers();

    User getUser(String name);

    void disableUser(String name);

    void enableUser(String name);

    void featureExtension(int id);

    void unFeatureExtension(int id);

    void deleteExtension(int id);

    Admin getAdminInfo();

}
