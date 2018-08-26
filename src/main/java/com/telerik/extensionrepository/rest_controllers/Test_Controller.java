package com.telerik.extensionrepository.rest_controllers;

import com.telerik.extensionrepository.data.TestRepository;
import com.telerik.extensionrepository.model.TestExtension;
import com.telerik.extensionrepository.model.TestGit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test_Controller {

    private TestRepository testRepository;

    @Autowired
    public Test_Controller(TestRepository testRepository){
        this.testRepository = testRepository;
    }

    @GetMapping("/api/test")
    public void createTestExt(){

        TestExtension testExtension = new TestExtension();

        TestGit testGit = new TestGit();

        testGit.setInfo("BLA BLA BLAAAAA");

        testExtension.setTestGit(testGit);

        testRepository.saveTestExtension(testExtension);

    }

}
