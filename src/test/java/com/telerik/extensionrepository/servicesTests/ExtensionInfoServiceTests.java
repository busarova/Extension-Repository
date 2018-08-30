package com.telerik.extensionrepository.servicesTests;

import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.ExtensionInfoServiceImpl;
import com.telerik.extensionrepository.service.base.ExtensionInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExtensionInfoServiceTests {

    @Mock
    private ExtensionRepository extensionRepository = mock(ExtensionRepository.class);

    //    List<Extension> getAll();
    @Test
    public void getAllExtensions_whenMatch_returnListOfExtensions() {

        List<Extension> extensions = new ArrayList<>();

        for (int i = 1; i < 5; i++) {

            Extension extension = new Extension();
            extension.setName("test extension " + i);
            extensions.add(extension);
        }

        when(extensionRepository.getAllExtensions())
                .thenReturn(extensions);

        ExtensionInfoService service = new ExtensionInfoServiceImpl(extensionRepository);


        List<Extension> resultExtensions = service.getAll();

        Assert.assertEquals(resultExtensions.size(), 4);

        Assert.assertEquals("test extension 1", resultExtensions.get(0).getName());
        Assert.assertEquals("test extension 2", resultExtensions.get(1).getName());
        Assert.assertEquals("test extension 3", resultExtensions.get(2).getName());
        Assert.assertEquals("test extension 4", resultExtensions.get(3).getName());
    }

    //
//    List<Extension> getAllApproved();
    @Test
    public void getApprovedExtensions_whenMatch_returnListOfApprovedExtensions() {

        List<Extension> extensions = new ArrayList<>();

        for (int i = 1; i < 5; i++) {

            Extension extension = new Extension();
            extension.setApproved(1);
            extensions.add(extension);
        }

        when(extensionRepository.getAllApproved())
                .thenReturn(extensions);

        ExtensionInfoService service = new ExtensionInfoServiceImpl(extensionRepository);

        List<Extension> resultExtensions = service.getAllApproved();

        Assert.assertEquals(resultExtensions.size(), 4);

        Assert.assertEquals("Approved", resultExtensions.get(0).isItApproved());
        Assert.assertEquals("Approved", resultExtensions.get(1).isItApproved());
        Assert.assertEquals("Approved", resultExtensions.get(2).isItApproved());
        Assert.assertEquals("Approved", resultExtensions.get(3).isItApproved());
    }
//
//    List<Extension> getFeatured();
@Test
public void getFeaturedExtensions_whenMatch_returnListOfFeaturedExtensions() {

    List<Extension> extensions = new ArrayList<>();

    for (int i = 1; i < 5; i++) {

        Extension extension = new Extension();
        extension.setFeatured(1);
        extensions.add(extension);
    }

    when(extensionRepository.getFeaturedExtensions())
            .thenReturn(extensions);

    ExtensionInfoService service = new ExtensionInfoServiceImpl(extensionRepository);

    List<Extension> resultExtensions = service.getFeatured();

    Assert.assertEquals(resultExtensions.size(), 4);

    Assert.assertEquals("Featured", resultExtensions.get(0).isItFeatured());
    Assert.assertEquals("Featured", resultExtensions.get(1).isItFeatured());
    Assert.assertEquals("Featured", resultExtensions.get(2).isItFeatured());
    Assert.assertEquals("Featured", resultExtensions.get(3).isItFeatured());
}
//
//    List<Extension> getPopular();
@Test
public void getPopularExtensions_whenMatch_returnListOfPopularExtensions() {

    List<Extension> extensions = new ArrayList<>();

    for (int i = 1; i < 5; i++) {

        Extension extension = new Extension();
        extension.setName("test extension " + i);
        extensions.add(extension);
    }

    when(extensionRepository.getPopularExtensions())
            .thenReturn(extensions);

    ExtensionInfoService service = new ExtensionInfoServiceImpl(extensionRepository);

    List<Extension> resultExtensions = service.getPopular();

    Assert.assertEquals(resultExtensions.size(), 4);

    Assert.assertEquals("test extension 1", resultExtensions.get(0).getName());
    Assert.assertEquals("test extension 2", resultExtensions.get(1).getName());
    Assert.assertEquals("test extension 3", resultExtensions.get(2).getName());
    Assert.assertEquals("test extension 4", resultExtensions.get(3).getName());
}

//
//    List<Extension> getNew();
//
//    List<Extension> getByUserName(String userName);

//
//    Extension getById(int id);
@Test
public void getExtensionById_whenMatch_returnsExtensionWithRequestedId() {


    Extension extension = new Extension();
    extension.setId(11);

    when(extensionRepository.getExtById(extension.getId())).thenReturn(extension);

    ExtensionInfoService service = new ExtensionInfoServiceImpl(extensionRepository);

    Extension resultExtension = service.getById(11);

    Assert.assertEquals(11, resultExtension.getId());

}
//
//    Extension getExtByName(String name);
@Test
public void getExtensionByName_whenMatch_returnsExtensionWithRequestedName() {


    Extension extension = new Extension();
    extension.setName("extension test 1");

    when(extensionRepository.getExtByName(extension.getName())).thenReturn(extension);

    ExtensionInfoService service = new ExtensionInfoServiceImpl(extensionRepository);

    Extension resultExtension = service.getExtByName("extension test 1");

    Assert.assertEquals("extension test 1", resultExtension.getName());

}
//
//    List<Extension> returnOrderedBy(String parameter);
//
//    List<Extension> returnAllOrderedBy(String parameter);
//
//    List<Extension> sortListBy(List<Extension> list, String parameter);
}
