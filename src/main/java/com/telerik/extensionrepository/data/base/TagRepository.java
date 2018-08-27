package com.telerik.extensionrepository.data.base;

import com.telerik.extensionrepository.model.Tags;

import java.util.List;

public interface TagRepository {

    void createNewTag(Tags tag);

    void updateTag(Tags tag);

    Tags getById(int id);

    Tags getByName(String name);

    List<Tags> getAll();

}
