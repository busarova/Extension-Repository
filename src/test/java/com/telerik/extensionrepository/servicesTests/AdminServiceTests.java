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
    public void approveExtension_whenApproved_returnExtensionApproved() {

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

    @Test
    public void getUserByName_whenMatch_returnsUserWithRequestedName() {


        User user = new User();
        user.setName("test username 1");

        when(adminRepository.getUserByName(user.getName())).thenReturn(user);

        AdminService service = new AdminServiceImpl(extensionRepository, adminRepository, gitExtensionInfoRepository, gitService);

        User resultUser = service.getUser("test username 1");

        Assert.assertEquals("test username 1", resultUser.getName());

    }

    @Test
    public void disableUser_whenDisabled_returnsUserDisabled() {


        User user = new User();
        user.setName("test username 1");

        when(adminRepository.getUserByName(user.getName())).thenReturn(user);

        AdminService service = new AdminServiceImpl(extensionRepository, adminRepository, gitExtensionInfoRepository, gitService);

        User resultUser = service.disableUser("test username 1");

        Assert.assertEquals("Disabled", resultUser.getStatus());

    }

    @Test
    public void enableUser_whenEnabled_returnsUserEnabled() {


        User user = new User();
        user.setName("test username 1");

        when(adminRepository.getUserByName(user.getName())).thenReturn(user);

        AdminService service = new AdminServiceImpl(extensionRepository, adminRepository, gitExtensionInfoRepository, gitService);

        User resultUser = service.enableUser("test username 1");

        Assert.assertEquals("Enabled", resultUser.getStatus());

    }

    @Test
    public void featureExtension_whenFeatured_returnExtensionFeatured() {

        Extension extension = new Extension();
        extension.setId(10);

        when(extensionRepository.getExtById(extension.getId())).thenReturn(extension);

        AdminService service = new AdminServiceImpl(extensionRepository, adminRepository, gitExtensionInfoRepository, gitService);

        Extension resultExt = service.featureExtension(10);

        Assert.assertEquals(10, resultExt.getId());
        Assert.assertEquals("Featured", resultExt.isItFeatured());

    }

    @Test
    public void removeExtensionFromFeatured_whenRemoved_returnExtensionNotFeatured() {

        Extension extension = new Extension();
        extension.setId(10);

        when(extensionRepository.getExtById(extension.getId())).thenReturn(extension);

        AdminService service = new AdminServiceImpl(extensionRepository, adminRepository, gitExtensionInfoRepository, gitService);

        Extension resultExt = service.unFeatureExtension(10);

        Assert.assertEquals(10, resultExt.getId());
        Assert.assertEquals("Not Featured", resultExt.isItFeatured());

    }

//    TODO
//    public void deleteExtension(int id) {
//
//    @Test
//    public void deleteExtension_whenDeleted_returnNull() {
//
//        Extension extension = new Extension();
//        extension.setId(10);
//
//        when(extensionRepository.getExtById(extension.getId())).thenReturn(extension);
//
//        AdminService service = new AdminServiceImpl(extensionRepository, adminRepository, gitExtensionInfoRepository, gitService);
//
//
//    }


//    public Admin getAdminInfo() {
//        return adminRepository.getAdminInfo();


}
