package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.model.Admin;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.User;

import java.util.List;

public interface AdminService {

    int refreshAllGitHubInfo();

    List<Extension> getUnnaprovedExt();

    Extension approveExt(int id);

    Extension removeApproval(int id);

    List<User> getAllUsers();

    User getUser(String name);

    User disableUser(String name);

    User enableUser(String name);

    void featureExtension(int id);

    void unFeatureExtension(int id);

    void deleteExtension(int id);

    Admin getAdminInfo();

}
