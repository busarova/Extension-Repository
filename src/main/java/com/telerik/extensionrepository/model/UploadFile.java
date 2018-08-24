package com.telerik.extensionrepository.model;


import javax.persistence.*;

@Entity
@Table(name = "files")
public class UploadFile {

    private int id;
    private String fileName;
    private byte[] data;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    @Column(name = "fileName")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    @Column(name = "fileData")
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
