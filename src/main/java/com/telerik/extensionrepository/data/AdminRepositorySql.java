package com.telerik.extensionrepository.data;

import com.telerik.extensionrepository.data.base.AdminRepository;
import com.telerik.extensionrepository.model.Extension;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminRepositorySql implements AdminRepository {

    private SessionFactory factory;

    @Autowired
    public AdminRepositorySql(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void approveExtension(int id) {


        try(Session session = factory.openSession()){
            session.beginTransaction();

            Extension extension = session.get(Extension.class, id);

            extension.setApproved(0);

            session.update(extension);
            session.save(extension);

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }
}