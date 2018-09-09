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

    @Column(name = "last_successful_tag_clean")
    private Date lastSuccessfulTagClean;

    @Column(name = "last_failed_sync")
    private Date lastFailedSync;

    @Column(name = "last_failed_reason")
    private String lastFailedReason;

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

    public Date getLastSuccessfulTagClean() {
        return lastSuccessfulTagClean;
    }

    public void setLastSuccessfulTagClean(Date lastSuccessfulTagClean) {
        this.lastSuccessfulTagClean = lastSuccessfulTagClean;
    }

    public Date getLastFailedSync() {
        return lastFailedSync;
    }

    public void setLastFailedSync(Date lastFailedSync) {
        this.lastFailedSync = lastFailedSync;
    }

    public String getLastFailedReason() {
        return lastFailedReason;
    }

    public void setLastFailedReason(String lastFailedReason) {
        this.lastFailedReason = lastFailedReason;
    }
}
