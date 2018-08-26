package com.telerik.extensionrepository.model;

import javax.persistence.*;

@Entity
@Table(name = "test_git_info")
public class TestGit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gitId")
    private int id;

    @Column(name = "info")
    private String info;


    @OneToOne(mappedBy = "testGit", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private TestExtension testExtension;

    public TestGit(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public TestExtension getTestExtension() {
        return testExtension;
    }

    public void setTestExtension(TestExtension testExtension) {
        this.testExtension = testExtension;
    }
}
