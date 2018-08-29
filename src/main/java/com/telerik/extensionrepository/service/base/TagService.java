package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.dto.TagForm;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.Tags;

import java.util.List;

public interface TagService {

    void loadNewTags(Extension extension);

    List<Tags> getAllTags();

    List<Tags> getAllTagsByName(String name);

    List<Extension> getExtensionsByTag(int tagId);

    Tags getTagById(int tagId);

    Tags getTagByName(String name);

    List<TagForm> extractTagsFromExtension(Extension extension);

}
