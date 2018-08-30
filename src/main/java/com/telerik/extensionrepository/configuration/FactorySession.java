package com.telerik.extensionrepository.configuration;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.telerik.extensionrepository.model.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactorySession {

    @Bean
    public SessionFactory createSessionFactory(){

        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Extension.class)
                .addAnnotatedClass(GitExtensionInfo.class)
                .addAnnotatedClass(UploadFile.class)
                .addAnnotatedClass(Tags.class)
                .addAnnotatedClass(Admin.class)
                .buildSessionFactory();
    }

    @Bean
    public Module datatypeHibernateModule() {
        return new Hibernate4Module();
    }
}
