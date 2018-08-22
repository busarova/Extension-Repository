package com.telerik.extensionrepository;

import com.telerik.extensionrepository.model.UploadFile;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.GitExtensionInfo;
import com.telerik.extensionrepository.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ExtensionTestTest {

    public static void main(String[] args){


        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Extension.class)
                .addAnnotatedClass(GitExtensionInfo.class)
                .addAnnotatedClass(UploadFile.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        UploadFile file = null;


            session.beginTransaction();

            file = session.get(UploadFile.class, 13);

            session.getTransaction().commit();


        session.close();

        System.out.println(file.getFileName());

    }



}

