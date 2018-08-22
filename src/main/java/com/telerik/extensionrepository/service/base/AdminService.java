package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.User;

import java.util.List;

public interface AdminService {

    List<Extension> getUnnaprovedExt();

    void approveExt(int id);

    void uNApproveExt(int id);

    List<User> getAllUsers();

    void disableUser(String name);

    void enableUser(String name);

    void featureExtension(String name);

    void unFeatureExtension(String name);

    void deleteExtension(String name);

}
