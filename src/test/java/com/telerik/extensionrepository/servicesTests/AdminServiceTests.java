package com.telerik.extensionrepository.servicesTests;

import com.telerik.extensionrepository.data.base.AdminRepository;
import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.data.base.GitExtensionInfoRepository;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.User;
import com.telerik.extensionrepository.service.AdminServiceImpl;
import com.telerik.extensionrepository.service.base.AdminService;
import com.telerik.extensionrepository.service.base.GitService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class AdminServiceTests {

    @Mock
    AdminRepository adminRepository = mock(AdminRepository.class);
    @Mock
    private ExtensionRepository extensionRepository = mock(ExtensionRepository.class);
    @Mock
    private GitExtensionInfoRepository gitExtensionInfoRepository = mock(GitExtensionInfoRepository.class);
    @Mock
    private GitService gitService = mock(GitService.class);

    // TODO
    // public int refreshAllGitHubInfo


    @Test
    public void approveExtension_whenApproved_returnExtensionIsApproved() {

        Extension extension = new Extension();
        extension.setId(10);

        when(extensionRepository.getExtById(extension.getId())).thenReturn(extension);

        AdminService service = new AdminServiceImpl(extensionRepository, adminRepository, gitExtensionInfoRepository, gitService);

        Extension resultExt = service.approveExt(10);

        Assert.assertEquals(10, resultExt.getId());
        Assert.assertEquals("Approved", resultExt.isItApproved());

    }

    @Test
    public void removeExtensionApproval_whenRemoved_returnExtensionPendingApproval() {

        Extension extension = new Extension();
        extension.setId(10);

        when(extensionRepository.getExtById(extension.getId())).thenReturn(extension);

        AdminService service = new AdminServiceImpl(extensionRepository, adminRepository, gitExtensionInfoRepository, gitService);

        Extension resultExt = service.removeApproval(10);

        Assert.assertEquals(10, resultExt.getId());
        Assert.assertEquals("Pending Approval", resultExt.isItApproved());

    }


    @Test
    public void getAllUsers_whenMatch_returnListOfUsers() {

        // Arrange
        List<User> users = new ArrayList<>();

        for (int i = 1; i < 5; i++) {

            User user = new User();
            user.setName("test user " + i);
            users.add(user);

        }

        when(adminRepository.getAllUsers())
                .thenReturn(users);

        AdminService service = new AdminServiceImpl(extensionRepository, adminRepository, gitExtensionInfoRepository, gitService);

        // Act
        List<User> resultUsers = service.getAllUsers();

        // Assert
        Assert.assertEquals(resultUsers.size(), 4);

        Assert.assertEquals("test user 1", resultUsers.get(0).getName());
        Assert.assertEquals("test user 2", resultUsers.get(1).getName());
        Assert.assertEquals("test user 3", resultUsers.get(2).getName());
        Assert.assertEquals("test user 4", resultUsers.get(3).getName());
    }


//    public User getUser(String name) {
//        return adminRepository.getUserByName(name);

    @Test
    public void getUserByName_whenMatch_returnsUserWithRequestedName(){


        User user = new User();
        user.setName("test username 1");

        when(adminRepository.getUserByName(user.getName())).thenReturn(user);

        AdminService service = new AdminServiceImpl(extensionRepository, adminRepository, gitExtensionInfoRepository, gitService);

        User resultUser = service.getUser("test username 1");

        Assert.assertEquals("test username 1", resultUser.getName());

    }


//    public void disableUser(String name) {
//        adminRepository.disableUser(name);


//    public void enableUser(String name) {
//        adminRepository.enableUser(name);


//    public void featureExtension(int id) {
//        adminRepository.featureExtension(id);


//    public void unFeatureExtension(int id) {
//        adminRepository.unFeatureExtension(id);


//    public void deleteExtension(int id) {
//
//        Extension extension = extensionRepository.getExtById(id);
//
//        adminRepository.deleteExtension(extension);


//    public Admin getAdminInfo() {
//        return adminRepository.getAdminInfo();

}
