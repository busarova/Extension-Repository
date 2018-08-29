package com.telerik.extensionrepository.service;

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
}
