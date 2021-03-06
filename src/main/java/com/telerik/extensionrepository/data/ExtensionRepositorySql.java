package com.telerik.extensionrepository.data;

import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.exceptions.RepositoryException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.OptimisticLockException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExtensionRepositorySql implements ExtensionRepository {

    private static final Logger logger = LoggerFactory.getLogger(ExtensionRepositorySql.class);
    private SessionFactory factory;

    @Autowired
    public ExtensionRepositorySql(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Extension> getAllExtensions() throws RepositoryException {
        List<Extension> theList = new ArrayList<>();

        try (Session session = factory.openSession()) {
            session.beginTransaction();

            theList = session.createQuery("from Extension e order by e.name").list();

            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RepositoryException("There was a problem with the database.");
        }

        return theList;
    }


    @Override
    public List<Extension> getAllApproved() throws RepositoryException {
        List<Extension> theList = new ArrayList<>();

        try (Session session = factory.openSession()) {
            session.beginTransaction();

            theList = session.createQuery("from Extension e where approved = 1 order by e.name").list();

            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RepositoryException("There was a problem with the database.");
        }

        return theList;
    }

    @Override
    public List<Extension> getAllNotApproved() {
        List<Extension> theList = new ArrayList<>();

        try (Session session = factory.openSession()) {
            session.beginTransaction();

            theList = session.createQuery("from Extension e where approved = 0 order by e.name").list();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }

        return theList;
    }

    @Override
    public Extension updateExtension(Extension extension) {

        Transaction transaction = null;

        try (Session session = factory.openSession()) {

            transaction = session.beginTransaction();
            extension.getTags().forEach(session::saveOrUpdate);
            session.update(extension);
            transaction.commit();

        } catch (HibernateException | OptimisticLockException e) {

            logger.error(e.getMessage());
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

        }

        return extension;
    }


    @Override
    public Extension createExtension(Extension extension) {

        try (Session session = factory.openSession()) {
            session.beginTransaction();

            extension.getTags().forEach(session::saveOrUpdate);

            session.save(extension);

            session.getTransaction().commit();

        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }

        return extension;
    }

    @Override
    public List<Extension> getFeaturedExtensions() {

        List<Extension> theList = new ArrayList<>();

        try (Session session = factory.openSession()) {
            session.beginTransaction();

            theList = session.createQuery("from Extension e where featured = 1 and approved = 1 order by e.name").list();

            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }

        return theList;

    }

    @Override
    public List<Extension> getPopularExtensions() {
        List<Extension> theList = new ArrayList<>();

        try (Session session = factory.openSession()) {
            session.beginTransaction();

            theList = session.createQuery("from Extension e where approved = 1 order by e.numberOfDownloads desc").list();

            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }

        return theList;
    }

    @Override
    public List<Extension> getNewExtensions() {
        List<Extension> theList = new ArrayList<>();

        try (Session session = factory.openSession()) {
            session.beginTransaction();

            theList = session.createQuery("from Extension e where approved = 1 order by e.uploadDate desc").list();

            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }

        return theList;
    }

    @Override
    public List<Extension> getByUserName(String userName) {
        List<Extension> theList = new ArrayList<>();

        try (Session session = factory.openSession()) {
            session.beginTransaction();

            theList = session.createQuery("from Extension e where owner = :username")
                    .setParameter("username", userName)
                    .list();

            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }

        return theList;
    }

    @Override
    public Extension getExtByName(String name) {

        List<Extension> theList = new ArrayList<>();

        try (Session session = factory.openSession()) {
            session.beginTransaction();

            theList = session.createQuery("from Extension e where name = :name")
                    .setParameter("name", name)
                    .list();

            session.getTransaction().commit();

        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }

        return theList.get(0);
    }

    @Override
    public Extension getExtById(int id) {

        Extension extension = null;

        try (Session session = factory.openSession()) {
            session.beginTransaction();

            extension = session.get(Extension.class, id);

            session.getTransaction().commit();

        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }

        return extension;
    }

    @Override
    public Extension deleteExtension(Extension extension) {

        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.delete(extension);

            System.out.println(extension.getName() + " deleted!");

            session.getTransaction().commit();

        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return extension;
    }

//    Search By
    @Override
    public List<Extension> getAllByParam(String param) {
        List<Extension> theList = new ArrayList<>();

        try (Session session = factory.openSession()) {
            session.beginTransaction();

            theList = session.createQuery("from Extension where approved = 1 and name like :param")
                    .setParameter("param", "%" + param + "%")
                    .list();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }

        return theList;
    }

    @Override
    public List<Extension> searchByDownloads(String param) {
        List<Extension> theList = new ArrayList<>();

        try (Session session = factory.openSession()) {
            session.beginTransaction();

            theList = session.createQuery("from Extension where approved = 1 and name like :param order by numberOfDownloads desc")
                    .setParameter("param", "%" + param + "%")
                    .list();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }

        return theList;
    }
    @Override
    public List<Extension> searchByUploadDate(String param) {
        List<Extension> theList = new ArrayList<>();

        try (Session session = factory.openSession()) {
            session.beginTransaction();

            theList = session.createQuery("from Extension where approved = 1 and name like :param order by uploadDate desc")
                    .setParameter("param", "%" + param + "%")
                    .list();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }

        return theList;
    }

    @Override
    public List<Extension> searchByLastCommitDate(String param) {
        List<Extension> theList = new ArrayList<>();

        try (Session session = factory.openSession()) {
            session.beginTransaction();

            theList = session.createQuery("from Extension where approved = 1 and name like :param order by gitExtensionInfo.lastCommitDate desc")
                    .setParameter("param", "%" + param + "%")
                    .list();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }

        return theList;
    }
}