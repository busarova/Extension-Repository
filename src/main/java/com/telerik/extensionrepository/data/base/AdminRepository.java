package com.telerik.extensionrepository.data.base;

import com.telerik.extensionrepository.model.Admin;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.GitExtensionInfo;
import com.telerik.extensionrepository.model.User;

import java.util.Date;
import java.util.List;

public interface AdminRepository {

    List<User> getAllUsers();

    User updateUser(User user);

    void updateLastSuccessfulSync(Date date);

    Admin getAdminInfo();

    User getUserByName(String name);

    void updateLastSuccessfulTagClean(Date date);

}
