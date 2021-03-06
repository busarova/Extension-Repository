package com.telerik.extensionrepository.data;

import com.telerik.extensionrepository.data.base.TagRepository;
import com.telerik.extensionrepository.model.Tags;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TagRepositorySql implements TagRepository {

    private SessionFactory factory;
    private static final Logger logger = LoggerFactory.getLogger(TagRepositorySql.class);

    @Autowired
    public TagRepositorySql(SessionFactory factory){
        this.factory = factory;
    }


    @Override
    public Tags getById(int id) {

        Tags tag = null;

        try(Session session = factory.openSession()){
            session.beginTransaction();

            tag = session.get(Tags.class, id);

            session.getTransaction().commit();

        }catch (Exception e){
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }

        return tag;
    }

    @Override
    public Tags getByName(String name) {

            Tags tag = null;

            try(Session session = factory.openSession()){
                session.beginTransaction();

                tag = (Tags) session.createQuery("from Tags t where t.name like :name")
                        .setParameter("name", "%" + name + "%")
                        .uniqueResult();

                session.getTransaction().commit();
            }catch (Exception e){
                logger.error(e.getMessage());
                System.out.println(e.getMessage());
            }

            return tag;
    }

    @Override
    public List<Tags> getAll() {

        List<Tags> all = new ArrayList<>();

        try(Session session = factory.openSession()){
            session.beginTransaction();

            all = session.createQuery("From Tags").list();

            session.getTransaction().commit();

        }catch (Exception e){
            logger.error(e.getMessage());
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
                    .setParameter("param", "%" + param + "%")
                    .list();

            session.getTransaction().commit();

        }catch (Exception e){
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }

        return theList;
    }

    @Override
    public void saveTag(Tags tag) {

        try(Session session = factory.openSession()){
            session.beginTransaction();

            session.save(tag);

            session.getTransaction().commit();

        }catch (Exception e){
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void deleteTag(Tags tag) {

        try(Session session = factory.openSession()){
            session.beginTransaction();

            session.delete(tag);

            session.getTransaction().commit();

        }catch (Exception e){
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }


    }
}
