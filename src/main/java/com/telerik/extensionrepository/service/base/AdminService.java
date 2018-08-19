package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.User;

import java.util.List;

public interface AdminService {

    List<Extension> getUnnaprovedExt();

    void approveExt(int id);

    List<User> getAllUsers();

}
