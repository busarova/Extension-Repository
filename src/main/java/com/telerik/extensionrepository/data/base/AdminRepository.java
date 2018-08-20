package com.telerik.extensionrepository.data.base;

import com.telerik.extensionrepository.model.User;

import java.util.List;

public interface AdminRepository {

    void approveExtension(int id);

    List<User> getAllUsers();

    void disableUser(String name);

    void enableUser(String name);

    void featureExtension(String name);
}
