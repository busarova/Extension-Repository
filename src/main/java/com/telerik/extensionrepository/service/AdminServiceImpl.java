package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.data.base.AdminRepository;
import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.data.base.GitExtensionInfoRepository;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.User;
import com.telerik.extensionrepository.service.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private ExtensionRepository extensionRepository;
    private AdminRepository adminRepository;
    private GitExtensionInfoRepository gitExtensionInfoRepository;

    @Autowired
    public AdminServiceImpl(ExtensionRepository extensionRepository, AdminRepository adminRepository, GitExtensionInfoRepository gitExtensionInfoRepository){
        this.extensionRepository = extensionRepository;
        this.adminRepository = adminRepository;
        this.gitExtensionInfoRepository = gitExtensionInfoRepository;
    }

    @Override
    public List<Extension> getUnnaprovedExt() {
        return extensionRepository.getAllExtensions().stream()
                .filter( x-> x.getApproved() == 1)
                .collect(Collectors.toList());
    }

    @Override
    public void approveExt(int id) {

        adminRepository.approveExtension(id);

    }

    @Override
    public void uNApproveExt(int id) {
        adminRepository.uNApproveExtension(id);
    }

    @Override
    public List<User> getAllUsers() {
        return adminRepository.getAllUsers();
    }

    @Override
    public void disableUser(String name) {
        adminRepository.disableUser(name);
    }

    @Override
    public void enableUser(String name) {
        adminRepository.enableUser(name);
    }

    @Override
    public void featureExtension(String name) {
        adminRepository.featureExtension(name);
    }

    @Override
    public void unFeatureExtension(String name) {
        adminRepository.unFeatureExtension(name);
    }

    @Override
    public void deleteExtension(String name) {

        Extension extension = extensionRepository.getExtByName(name);

       int gitId = extension.getGitId();

        System.out.println(gitId);

       adminRepository.deleteExtension(extension);
       adminRepository.deleteGitExtensionInfo(gitExtensionInfoRepository.getGitInfoById(gitId));

    }
}
