package com.telerik.extensionrepository;

import com.telerik.extensionrepository.model.*;
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

        UploadFile file = null;


            session.beginTransaction();

            Admin admin = session.get(Admin.class, 1);

        System.out.println(admin.getLastSuccessfulSync());

            session.getTransaction().commit();


        session.close();


    }



}

