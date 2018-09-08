package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.dto.ExtensionDTO;
import com.telerik.extensionrepository.dto.ExtensionForm;
import com.telerik.extensionrepository.dto.TagForm;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.Tags;

import java.util.List;

public interface TagService {

    List<Tags> loadNewTags(ExtensionForm extensionForm);

    List<Tags> getAllTags();

    Tags getTagByName(String name);

    List<Tags> getAllTagsByName(String name);

    Tags getTagById(int tagId);

    void deleteTag(Tags tag);

}
