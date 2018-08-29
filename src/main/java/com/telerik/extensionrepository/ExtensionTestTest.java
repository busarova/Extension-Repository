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
                .buildSessionFactory();

        Session session = factory.openSession();

        UploadFile file = null;


            session.beginTransaction();

            String name = "#awesome";

            List<Tags> list = session.createQuery("from Tags t where t.name = :name")
                .setParameter("name", name)
                .list();

            session.getTransaction().commit();


        session.close();

        System.out.println(list.get(0).getName());

    }



}

