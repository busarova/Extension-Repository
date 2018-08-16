package com.telerik.extensionrepository;

import com.telerik.extensionrepository.model.DownloadFile;
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
                .addAnnotatedClass(DownloadFile.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        session.beginTransaction();

        Extension newExt = session.get(Extension.class, 1);

        session.getTransaction().commit();

        System.out.println(newExt.getName());

        session.close();


    }

}

