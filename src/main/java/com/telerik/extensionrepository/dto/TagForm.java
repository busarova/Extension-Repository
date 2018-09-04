package com.telerik.extensionrepository.dto;

public class TagForm {

    int id;
    String name;

    public TagForm(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TagForm(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameNoHashTag(){
        if(getName().equals("")){
            return getName();
        }

        if(getName().charAt(0) == '#'){
            return getName().substring(1, getName().length());
        }

        return getName();
    }
}
