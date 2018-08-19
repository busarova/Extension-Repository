package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.model.Extension;

import java.util.List;

public interface AdminService {

    public List<Extension> getUnnaprovedExt();

    public void approveExt(int id);
}
