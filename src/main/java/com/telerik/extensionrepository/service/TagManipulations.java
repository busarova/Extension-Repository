package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.dto.TagForm;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.Tags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagManipulations {

    //Splits the input string tags by " "
    //Checks for every String (tag) of them if first character is #
    //If not puts # at char(0)

    public String checkForHashTag(String tags){

        String[] parsedTags = tags.split(" ");

        StringBuilder builder = new StringBuilder();

        for (String currentTag:
             parsedTags) {

            if(currentTag.charAt(0) != '#'){
                currentTag = '#' + currentTag;
            }

            builder.append(currentTag);
            builder.append(" ");

        }

        return builder.toString().trim();

    }

    //Extracts all tags which are separated by " " in an extension
    //And puts them into a List

    public List<TagForm> extractTagsFromExtension(Extension extension){

        List<TagForm> tags = new ArrayList<>();

        String[] parsedTags = extension.getTags().split(" ");

        for (String tag:
            parsedTags ) {
            tags.add(new TagForm(tag));
        }

        return tags;

        }


}
