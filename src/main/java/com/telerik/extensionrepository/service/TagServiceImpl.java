package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.data.base.TagRepository;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.Tags;
import com.telerik.extensionrepository.service.base.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    // Method splits tne newly tags by " " and for every tag checks if it is existent in tags table
    // If the repo returns null pointer exc, then no tag by that name
    // If it is not, creates new tag there and saves its owner
    // If there is, adds the owner to the existing ones


    @Override
    public void loadNewTags(Extension extension) {

        String[] tagList = extension.getTags().split(" ");

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
}
