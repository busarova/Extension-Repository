package com.telerik.extensionrepository.model;


import javax.persistence.*;

@Entity
@Table(name = "download_file")
public class DownloadFile {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "downloadFileID")
    private int id;

//    TODO
}
