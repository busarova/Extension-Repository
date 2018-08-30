package com.telerik.extensionrepository.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "last_successful_sync")
    private Date lastSuccessfulSync;

    public Admin(){}

    public Date getLastSuccessfulSync() {
        return lastSuccessfulSync;
    }

    public void setLastSuccessfulSync(Date lastSuccessfulSync) {
        this.lastSuccessfulSync = lastSuccessfulSync;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
