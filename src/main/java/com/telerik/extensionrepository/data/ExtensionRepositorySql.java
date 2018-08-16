package com.telerik.extensionrepository.data;

import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.model.Extension;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ExtensionRepositorySql implements ExtensionRepository {

    private SessionFactory factory;

    @Autowired
    public ExtensionRepositorySql(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public List<Extension> getAllExtensions() {
        List<Extension> theList = new ArrayList<>();

        try(Session session = factory.openSession()){
            session.beginTransaction();

            theList = session.createQuery("from Extension").list();

            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return theList;
    }
}
