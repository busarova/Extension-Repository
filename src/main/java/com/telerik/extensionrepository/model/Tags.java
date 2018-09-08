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

    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    private List<Extension> extensions = new ArrayList<>();

    public List<Extension> getExtensions() {
        return extensions;
    }

    public void setExtensions(List<Extension> extensions) {
        this.extensions = extensions;
    }

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

    public String getNameNoHashTag(){
        if(getName().charAt(0) == '#'){
            return getName().substring(1, getName().length());
        }

        return getName();
    }
}
