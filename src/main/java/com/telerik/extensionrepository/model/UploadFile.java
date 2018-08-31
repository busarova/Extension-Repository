package com.telerik.extensionrepository.model;


import javax.persistence.*;
import java.util.Base64;

@Entity
@Table(name = "files")
public class UploadFile {

    private int id;
    private String fileName;
    private byte[] data;
    private byte[] logoData;


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

    @Column(name = "logo_data")
    public byte[] getLogoData(){return logoData;}

    public void setLogoData(byte[] logoData){this.logoData = logoData;}

    public String encodeLogoToString(){
        return Base64.getEncoder().encodeToString(getLogoData());
    }

}
