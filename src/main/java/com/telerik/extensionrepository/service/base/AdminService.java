package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.User;

import java.util.List;

public interface AdminService {

    List<Extension> getUnnaprovedExt();

    void approveExt(int id);

    void removeApproval(int id);

    List<User> getAllUsers();

    void disableUser(String name);

    void enableUser(String name);

    void featureExtension(int id);

    void unFeatureExtension(int id);

    void deleteExtension(int id);

}
