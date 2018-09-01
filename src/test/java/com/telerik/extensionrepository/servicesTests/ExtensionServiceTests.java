package com.telerik.extensionrepository.servicesTests;

import com.telerik.extensionrepository.data.base.AdminRepository;
import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.data.base.GitExtensionInfoRepository;
import com.telerik.extensionrepository.dto.ExtensionForm;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.ExtensionServiceImpl;
import com.telerik.extensionrepository.service.base.ExtensionService;
import com.telerik.extensionrepository.service.base.GitService;
import com.telerik.extensionrepository.service.base.TagService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ExtensionServiceTests {

    @Mock
    private ExtensionRepository extensionRepository = mock(ExtensionRepository.class);
        TagService tagService;
        GitService gitService;


//    void createExtension(ExtensionForm extensionForm);


//    void changeExtensionName(Extension extension, String newName);
@Test
public void changeExtensionName_whenChanged_returnsExtensionWithNewName() {

    Extension extension = new Extension();
    extension.setName("test name");

    when(extensionRepository.updateExtension(extension)).thenReturn(extension);

    ExtensionService service = new ExtensionServiceImpl(extensionRepository, tagService, gitService);

    Extension resultExtension = service.changeExtensionName(extension, "new test name");

    Assert.assertEquals("new test name", resultExtension.getName());

}

//    void registerDownload(Extension extension);

//    void changeExtensionDescription(Extension extension, String newDescription);
@Test
public void changeExtensionDescription_whenChanged_returnsExtensionWithNewDescription() {

    Extension extension = new Extension();
    extension.setDescription("test description");

    when(extensionRepository.updateExtension(extension)).thenReturn(extension);

    ExtensionService service = new ExtensionServiceImpl(extensionRepository, tagService, gitService);

    Extension resultExtension = service.changeExtensionDescription(extension, "new test description");

    Assert.assertEquals("new test description", resultExtension.getDescription());

}

//    void addExtensionTag(Extension extension, String newTags);
}
