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
    @Column(name = "file_id")
    public int getId(){
        return id;
    }

    @OneToOne(mappedBy = "uploadFile", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Extension extension;

    public void setId(int id){
        this.id = id;
    }

    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    @Column(name = "file_data")
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
