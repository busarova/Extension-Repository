package com.telerik.extensionrepository.servicesTests;

import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.Tags;
import com.telerik.extensionrepository.service.ExtensionServiceImpl;
import com.telerik.extensionrepository.service.TagManipulations;
import com.telerik.extensionrepository.service.base.ExtensionService;
import com.telerik.extensionrepository.service.base.GitService;
import com.telerik.extensionrepository.service.base.TagService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ExtensionServiceTests {

    @Mock
    private ExtensionRepository extensionRepository = mock(ExtensionRepository.class);
    @Mock
    private TagManipulations tagManipulations = mock(TagManipulations.class);
    private TagService tagService;
    private GitService gitService;


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
    @Test
    public void incrementNumberOfDownloads_whenFileDownloaded_returnsExtensionWithUpdatedNumberOfDownloads() {


        Extension extension = new Extension();
        extension.setNumberOfDownloads(41);

        when(extensionRepository.updateExtension(extension)).thenReturn(extension);

        ExtensionService service = new ExtensionServiceImpl(extensionRepository, tagService, gitService);

        Extension resultExtension = service.registerDownload(extension);

        Assert.assertEquals(42, resultExtension.getNumberOfDownloads());

    }

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

    @Test
    public void addNewTagsToExtension_whenAdded_returnsExtensionWithAllTags(){

        Tags tag = new Tags();
        tag.setName("#test_tag_1");
        List<Tags> tags = new ArrayList<>();
        tags.add(tag);

        Extension extension = new Extension();
        extension.setTags(tags);

        when(tagManipulations.checkForHashTag("")).thenReturn("#test_tag_2");
        when(extensionRepository.updateExtension(extension)).thenReturn(extension);

        ExtensionService service = new ExtensionServiceImpl(extensionRepository, tagService, gitService);
        Extension resultExtension = service.addExtensionTag(extension, "#test_tag_2");

        Assert.assertEquals("#test_tag_1", resultExtension.getTags().get(0).getName());
        Assert.assertEquals("#test_tag_2", resultExtension.getTags().get(1).getName());
    }

}
