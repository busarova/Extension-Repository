package com.telerik.extensionrepository.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username")
    @NotNull(message="is required")
    @Size(min=1, message="is required")
    private String name;

    @Column(name = "password")
    @NotNull(message="is required")
    @Size(min=1, message="is required")
    private String password;

    @Column(name = "enabled")
    private int enabled;

    @Column(name = "profile_pic")
    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getStatus(){

        if(getEnabled() == 1){
            return "Enabled";
        }else{
            return "Disabled";
        }
    }
}
