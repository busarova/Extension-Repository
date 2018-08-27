package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.Tags;

import java.util.List;

public interface TagService {

    void loadNewTags(Extension extension);

    List<Tags> getAllTags();
}