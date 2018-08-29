package com.telerik.extensionrepository.data;

import com.telerik.extensionrepository.data.base.TagRepository;
import com.telerik.extensionrepository.model.Tags;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TagRepositorySql implements TagRepository {

    private SessionFactory factory;

    @Autowired
    public TagRepositorySql(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void createNewTag(Tags tag) {

        try(Session session = factory.openSession()){
            session.beginTransaction();

            session.save(tag);

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateTag(Tags tag) {

        try(Session session = factory.openSession()){
            session.beginTransaction();

            session.update(tag);

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Tags getById(int id) {

        Tags tag = null;

        try(Session session = factory.openSession()){
            session.beginTransaction();

            tag = session.get(Tags.class, id);

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return tag;
    }

    @Override
    public Tags getByName(String name) {

            List<Tags> list = null;

            try(Session session = factory.openSession()){
                session.beginTransaction();

                list = session.createQuery("from Tags t where t.name = :name")
                        .setParameter("name", name)
                        .list();

                session.getTransaction().commit();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }


            if(list == null || list.size() == 0){
                return null;
            }else {
                return list.get(0);
            }

    }

    @Override
    public List<Tags> getAll() {

        List<Tags> all = new ArrayList<>();

        try(Session session = factory.openSession()){
            session.beginTransaction();

            all = session.createQuery("From Tags").list();

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return all;
    }

    @Override
    public List<Tags> getAllByName(String param) {
        List<Tags> theList = new ArrayList<>();

        try(Session session = factory.openSession()){
            session.beginTransaction();

            theList = session.createQuery("from Tags t where t.name like :param")
                    .setParameter("param", param)
                    .list();

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return theList;
    }
}
