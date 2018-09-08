package com.telerik.extensionrepository.data.base;

import com.telerik.extensionrepository.model.Tags;

import java.util.List;

public interface TagRepository {

    Tags getById(int id);

    Tags getByName(String name);

    List<Tags> getAll();

    List<Tags> getAllByName(String name);

    void saveTag(Tags tag);

}
