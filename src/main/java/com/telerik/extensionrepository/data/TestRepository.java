package com.telerik.extensionrepository.data;

import com.telerik.extensionrepository.model.TestExtension;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class TestRepository {

    private SessionFactory factory;

    @Autowired
    public TestRepository(SessionFactory factory){
        this.factory = factory;
    }

    public void saveTestExtension(TestExtension testExtension) {

        try(Session session = factory.openSession()){
            session.beginTransaction();

            session.save(testExtension);

            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
