package com.telerik.extensionrepository.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "owners_id_list")
    private String owners_id_list;

    public Tags(){}

    public Tags(String name){
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

    public String getOwners_id_list() {
        return owners_id_list;
    }

    public void setOwners_id_list(String owners_id_list) {
        this.owners_id_list = owners_id_list;
    }

    public String getNameNoHashTag(){
        if(getName().charAt(0) == '#'){
            return getName().substring(1, getName().length());
        }

        return getName();
    }
}
