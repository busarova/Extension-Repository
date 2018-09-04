package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.data.base.TagRepository;
import com.telerik.extensionrepository.dto.TagForm;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.Tags;
import com.telerik.extensionrepository.service.base.ExtensionInfoService;
import com.telerik.extensionrepository.service.base.TagService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private TagManipulations tagManipulations = new TagManipulations();
    private TagRepository tagRepository;
    private ExtensionInfoService extensionInfoService;

    public TagServiceImpl(TagRepository tagRepository, ExtensionInfoService extensionInfoService){
        this.tagRepository = tagRepository;
        this.extensionInfoService = extensionInfoService;
    }

    // Method splits tne newly tags by " " and for every tag checks if it is existent in tags table
    // If the repo returns null pointer exc, then no tag by that name
    // If it is not, creates new tag there and saves its owner
    // If there is, adds the owner to the existing ones


    @Override
    public void loadNewTags(Extension extension) {

        String tags = extension.getTags();

        if(tags == null || tags.equals("")){
            return;
        }

        String[] tagList = tags.split(" ");

        for (String tag:
             tagList) {

            try{

                Tags newTag = tagRepository.getByName(tag);

                String owners = newTag.getOwners_id_list();

                newTag.setOwners_id_list(owners + " " + Integer.toString(extension.getId()));

                tagRepository.updateTag(newTag);


            }catch (NullPointerException e){

                Tags newTag = new Tags();

                newTag.setName(tag);
                newTag.setOwners_id_list(Integer.toString(extension.getId()));

                tagRepository.createNewTag(newTag);

            }

        }
    }

    @Override
    public List<Tags> getAllTags() {
        return tagRepository.getAll();
    }

    // Gets all tags by that name but first checks if the input string has # in the beginning of every word
    //every tag is split by " " and checks all of them for #

    @Override
    public List<Tags> getAllTagsByName(String name) {
        return tagRepository.getAllByName(tagManipulations.checkForHashTag(name));
    }

    //Returns List of Extensions that are marked with the current tag
    //You get the current tag by tagId
    //You get its getOwners field which is a string and every extension that ownes it is separated by " "
    //You parse it and iterate it adding each extension to the list and then return it

    @Override
    public List<Extension> getExtensionsByTag(int tagId) {

       Tags currentTag = tagRepository.getById(tagId);

       String[] currentTagExtensionIds = currentTag.getOwners_id_list().split(" ");

       List<Extension> list = new ArrayList<>();

        for (String a:
            currentTagExtensionIds ) {
            list.add(extensionInfoService.getById(Integer.parseInt(a)));
        }

        return list;
    }

    @Override
    public Tags getTagById(int tagId) {
        return tagRepository.getById(tagId);
    }


    // To finish
    @Override
    public Tags getTagByName(String name) {


        return tagRepository.getAll().stream()
                .filter( x -> x.getName().equals(tagManipulations.checkForHashTag(name)))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<TagForm> extractTagsFromExtension(Extension extension) {
        return tagManipulations.extractTagsFromExtension(extension);
    }


}
