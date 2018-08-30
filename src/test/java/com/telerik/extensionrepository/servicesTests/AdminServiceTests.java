package com.telerik.extensionrepository.servicesTests;

import com.telerik.extensionrepository.data.base.AdminRepository;
import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.data.base.GitExtensionInfoRepository;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.AdminServiceImpl;
import com.telerik.extensionrepository.service.base.GitService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class AdminServiceTests {

    @Mock
    AdminRepository adminRepository = mock(AdminRepository.class);
    @Mock
    private ExtensionRepository extensionRepository =  mock(ExtensionRepository.class);
    @Mock
    private GitExtensionInfoRepository gitExtensionInfoRepository = mock(GitExtensionInfoRepository.class);
    @Mock
    private GitService gitService = mock(GitService.class);

    // TODO
   // public int refreshAllGitHubInfo

  //  public List<Extension> getUnnaprovedExt() {
//        return extensionRepository.getAllExtensions().stream()
//                .filter( x-> x.getApproved() == 1)
//                .collect(Collectors.toList());



//    public void approveExt(int id) {
//
//        adminRepository.approveExtension(id);
    @Test
    public void approveExtension_whenApproved_returnExtensionIsApproved(){

        Extension extension = new Extension();
        extension.setId(10);

        when(extensionRepository.getExtById(extension.getId())).thenReturn(extension);

        AdminServiceImpl service = new AdminServiceImpl(extensionRepository, adminRepository, gitExtensionInfoRepository, gitService);

        Extension resultExt = service.approveExt(extension.getId());

        Assert.assertEquals(10, resultExt.getId());
        Assert.assertEquals("Approved", resultExt.isItApproved());

    }

    @Test
    public void removeExtensionApproval_whenRemoved_returnExtensionPendingApproval(){

        Extension extension = new Extension();
        extension.setId(10);

        when(extensionRepository.getExtById(extension.getId())).thenReturn(extension);

        AdminServiceImpl service = new AdminServiceImpl(extensionRepository, adminRepository, gitExtensionInfoRepository, gitService);

        Extension resultExt = service.removeApproval(extension.getId());

        Assert.assertEquals(10, resultExt.getId());
        Assert.assertEquals("Pending Approval", resultExt.isItApproved());

    }


//    public void removeApproval(int id) {
//        adminRepository.removeApproval(id);


//    public List<User> getAllUsers() {
//        return adminRepository.getAllUsers();



//    public User getUser(String name) {
//        return adminRepository.getUserByName(name);



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
