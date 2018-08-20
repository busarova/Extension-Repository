package com.telerik.extensionrepository.data;

import com.telerik.extensionrepository.data.base.AdminRepository;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.User;
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

    @Override
    public List<User> getAllUsers() {

        List<User> newList = null;

        try(Session session = factory.openSession()){
            session.beginTransaction();

            newList = session.createQuery("from User").list();

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return newList;

    }

    @Override
    public void disableUser(String name) {

        try(Session session = factory.openSession()){
            session.beginTransaction();

            User user = session.get(User.class, name);

            user.setEnabled(0);

            session.update(user);

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void enableUser(String name) {

        try(Session session = factory.openSession()){
            session.beginTransaction();

            User user = session.get(User.class, name);

            user.setEnabled(1);

            session.update(user);

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void featureExtension(String name) {
        try(Session session = factory.openSession()){
            session.beginTransaction();

            List<Extension> extension = session.createQuery("from Extension where name = :name")
                    .setParameter("name", name)
                    .list();

            extension.get(0).setFeatured(0);

            session.update(extension.get(0));

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void unFeatureExtension(String name) {

        try(Session session = factory.openSession()){
            session.beginTransaction();

            List<Extension> extension = session.createQuery("from Extension where name = :name")
                    .setParameter("name", name)
                    .list();

            extension.get(0).setFeatured(1);

            session.update(extension.get(0));

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
