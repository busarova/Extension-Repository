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

        if(tags == null || tags.equals("")){
            return "";
        }

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

        return builder.toString().trim().toLowerCase();

    }

}
