package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.data.base.TagRepository;
import com.telerik.extensionrepository.dto.ExtensionDTO;
import com.telerik.extensionrepository.dto.ExtensionForm;
import com.telerik.extensionrepository.dto.TagForm;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.Tags;
import com.telerik.extensionrepository.service.base.ExtensionInfoService;
import com.telerik.extensionrepository.service.base.TagService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private TagManipulations tagManipulations = new TagManipulations();
    private TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    //Loads the new tags into an arraylist
    //while checking if such a tag exists or not with the repo

    @Override
    public List<Tags> loadNewTags(ExtensionForm extensionForm) {

        List<Tags> tagList = new ArrayList<>();

        if(extensionForm.getTags() == null || extensionForm.getTags().equals("")){
            return tagList;
        }

        String[] tags = tagManipulations.checkForHashTag(extensionForm.getTags()).split(" ");


        for (String tag:
            tags ) {



            Tags currentTag = tagRepository.getByName(tag);

            if(currentTag == null){
                tagList.add(new Tags(tag));
            }else{
                tagList.add(currentTag);
            }

        }

        return tagList;

    }

    @Override
    public List<Tags> getAllTags() {
        return tagRepository.getAll();
    }

    @Override
    public Tags getTagByName(String name) {

          return tagRepository.getByName(name);
    }

    @Override
    public List<Tags> getAllTagsByName(String name) {
        return tagRepository.getAllByName(name);
    }

    @Override
    public Tags getTagById(int tagId) {
        return tagRepository.getById(tagId);
    }

    @Override
    public void deleteTag(Tags tag) {
        tagRepository.deleteTag(tag);
    }

}
