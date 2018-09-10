package com.telerik.extensionrepository.servicesTests;

import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.exceptions.RepositoryException;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.SearchServiceImpl;
import com.telerik.extensionrepository.service.base.SearchService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchServiceTests {

    @Mock
    private ExtensionRepository extensionRepository = mock(ExtensionRepository.class);

    @Test
    public void getAllExtensionsByParameter_whenMatch_returnListOfExtensionsWithNameContainingParameter() throws RepositoryException {

        List<Extension> extensions = new ArrayList<>();

        for (int i = 1; i < 10; i++) {

            Extension extension = new Extension();
            extension.setName("test extension " + i);
            extensions.add(extension);
        }
        List<Extension> extensionsByDownloads = new ArrayList<>();

        for (int i = 1; i < 5; i++) {

            Extension extension = new Extension();
            extension.setName("test extension " + i);
            extensionsByDownloads.add(extension);
        }

        when(extensionRepository.getAllByParam(""))
                .thenReturn(extensions);
        when(extensionRepository.searchByDownloads(""))
                .thenReturn(extensionsByDownloads);

        SearchService service = new SearchServiceImpl(extensionRepository);


        List<Extension> resultExtensions = service.getAllByParam("", "downloads");

        Assert.assertEquals(resultExtensions.size(), 4);

        Assert.assertEquals("test extension 1", resultExtensions.get(0).getName());

    }

}
