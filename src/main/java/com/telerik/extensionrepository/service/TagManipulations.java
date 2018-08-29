package com.telerik.extensionrepository.service;

public class TagManipulations {

    public String checkForHashTag(String tag){

        String[] parsedTags = tag.split(" ");

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
}
