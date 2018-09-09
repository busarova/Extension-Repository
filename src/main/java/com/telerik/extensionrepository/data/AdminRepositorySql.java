package com.telerik.extensionrepository.data;

import com.telerik.extensionrepository.data.base.AdminRepository;
import com.telerik.extensionrepository.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class AdminRepositorySql implements AdminRepository {

    private SessionFactory factory;

    @Autowired
    public AdminRepositorySql(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        try(Session session = factory.openSession()){
            session.beginTransaction();

            users = session.createQuery("from User").list();

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return users;

    }

    @Override
    public User updateUser(User user) {

        try(Session session = factory.openSession()){
            session.beginTransaction();

            session.update(user);

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return user;
    }

    @Override
    public void updateLastSuccessfulSync(Date date) {

        try(Session session = factory.openSession()){
            session.beginTransaction();

            Admin admin = session.get(Admin.class, 1);

            admin.setLastSuccessfulSync(date);

            session.update(admin);

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Admin getAdminInfo() {

        Admin admin = null;

        try(Session session = factory.openSession()){
            session.beginTransaction();

            admin = session.get(Admin.class, 1);

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return admin;
    }

    @Override
    public User getUserByName(String name) {

        User user = null;

        try(Session session = factory.openSession()){
            session.beginTransaction();

            user = session.get(User.class, name);

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return user;

    }

    @Override
    public void updateAdminInfo(Admin admin) {

        try(Session session = factory.openSession()){
            session.beginTransaction();

            session.update(admin);

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateLastSuccessfulTagClean(Date date) {

        try(Session session = factory.openSession()){
            session.beginTransaction();

            Admin admin = session.get(Admin.class, 1);

            admin.setLastSuccessfulTagClean(date);

            session.update(admin);

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }



}
