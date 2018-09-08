package com.telerik.extensionrepository;

import com.telerik.extensionrepository.model.*;
import com.telerik.extensionrepository.service.TagServiceImpl;
import com.telerik.extensionrepository.service.base.TagService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ExtensionTestTest {

    public static void main(String[] args){


        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Extension.class)
                .addAnnotatedClass(GitExtensionInfo.class)
                .addAnnotatedClass(UploadFile.class)
                .addAnnotatedClass(Tags.class)
                .addAnnotatedClass(Admin.class)
                .buildSessionFactory();

        Session session = factory.openSession();


            session.beginTransaction();

            Extension extension = session.get(Extension.class, 2);

            System.out.println(extension.getTags());

            session.getTransaction().commit();


        session.close();


    }



}

