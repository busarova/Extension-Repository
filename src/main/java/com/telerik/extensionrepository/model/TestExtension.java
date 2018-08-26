package com.telerik.extensionrepository.model;

import javax.persistence.*;

@Entity
@Table(name = "test_extension")
public class TestExtension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "extensionID")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gitId")
    private TestGit testGit;

    public TestExtension(){}

    public TestExtension(TestGit testGit) {
        this.testGit = testGit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TestGit getTestGit() {
        return testGit;
    }

    public void setTestGit(TestGit testGit) {
        this.testGit = testGit;
    }
}
