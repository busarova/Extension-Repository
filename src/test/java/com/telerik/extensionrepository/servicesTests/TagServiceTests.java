package com.telerik.extensionrepository.servicesTests;

import com.telerik.extensionrepository.data.base.AdminRepository;
import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.data.base.TagRepository;
import com.telerik.extensionrepository.dto.ExtensionForm;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.Tags;
import com.telerik.extensionrepository.service.AdminServiceImpl;
import com.telerik.extensionrepository.service.TagManipulations;
import com.telerik.extensionrepository.service.TagServiceImpl;
import com.telerik.extensionrepository.service.base.AdminService;
import com.telerik.extensionrepository.service.base.ExtensionInfoService;
import com.telerik.extensionrepository.service.base.TagService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TagServiceTests {

    @Mock
    private ExtensionRepository extensionRepository = mock(ExtensionRepository.class);

    @Mock
    private AdminRepository adminRepository = mock(AdminRepository.class);

    @Mock
    private TagRepository tagRepository = mock(TagRepository.class);

    @Mock
    private ExtensionInfoService extensionInfoService = mock(ExtensionInfoService.class);

    @Test
    public void loadNewTags_when_given_string_from_extensionForm_separates_tags_by_space_and_puts_them_in_arraylist() {

        ExtensionForm extensionForm = new ExtensionForm();
        extensionForm.setTags("test1 test2 test3");

        when(tagRepository.getByName("test1")).thenReturn(new Tags("test1"));
        when(tagRepository.getByName("test2")).thenReturn(new Tags("test2"));
        when(tagRepository.getByName("test3")).thenReturn(new Tags("test3"));


        TagService tagService = new TagServiceImpl(tagRepository, extensionInfoService);

        List<Tags> resultList = tagService.loadNewTags(extensionForm);

        Assert.assertEquals("#test1", resultList.get(0).getName());
        Assert.assertEquals("#test2", resultList.get(1).getName());
        Assert.assertEquals("#test3", resultList.get(2).getName());
        Assert.assertEquals(3, resultList.size());

    }

    @Test
    public void tagManipulations_checkForHashTag_checks_and_if_not_puts_hashTag_at_beginning_and_LowerCases_Everything(){

        TagManipulations tagManipulations = new TagManipulations();

        String testString = "wowIamTestString";
        String testString2 = "iAmReallyAnotherTestString";
        String testString3 = "#ohThirdTestStringOnTheWay";
        String testString4 = "#weAreAnArmyOfTestStringForReal";



        String outcomeString = tagManipulations.checkForHashTag(testString);
        String outcomeString2 = tagManipulations.checkForHashTag(testString2);
        String outcomeString3 = tagManipulations.checkForHashTag(testString3);
        String outcomeString4 = tagManipulations.checkForHashTag(testString4);

        Assert.assertEquals("#wowiamteststring", outcomeString);
        Assert.assertEquals("#iamreallyanotherteststring", outcomeString2);
        Assert.assertEquals("#ohthirdteststringontheway", outcomeString3);
        Assert.assertEquals("#weareanarmyofteststringforreal", outcomeString4);

    }
}
