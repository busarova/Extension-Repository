package com.telerik.extensionrepository.data;

import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.GitExtensionInfo;
import com.telerik.extensionrepository.model.base.ExtensionForm;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ExtensionRepositorySql implements ExtensionRepository {

    private SessionFactory factory;

    @Autowired
    public ExtensionRepositorySql(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public List<Extension> getAllExtensions() {
        List<Extension> theList = new ArrayList<>();

        try(Session session = factory.openSession()){
            session.beginTransaction();

            theList = session.createQuery("from Extension").list();

            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return theList;
    }

    @Override
    public int createGithub_info(GitExtensionInfo gitExtensionInfo) {


        try(Session session = factory.openSession()){
            session.beginTransaction();

            session.save(gitExtensionInfo);

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return gitExtensionInfo.getId();

    }

    @Override
    public void createExtension(Extension extension) {

        try(Session session = factory.openSession()){
            session.beginTransaction();

            session.save(extension);

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Extension> getFeaturedExtensions() {

        List<Extension> theList = new ArrayList<>();

        try(Session session = factory.openSession()){
            session.beginTransaction();

            theList = session.createQuery("from Extension e where featured = 0 and approved = 0 order by e.name").list();

            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return theList;

    }

    @Override
    public List<Extension> getPopularExtensions() {
        List<Extension> theList = new ArrayList<>();

        try(Session session = factory.openSession()){
            session.beginTransaction();

            theList = session.createQuery("from Extension e where approved = 0 order by e.numberOfDownloads").list();

            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return theList;
    }

    @Override
    public List<Extension> getNewExtensions() {
        List<Extension> theList = new ArrayList<>();

        try(Session session = factory.openSession()){
            session.beginTransaction();

            theList = session.createQuery("from Extension e where approved = 0 order by e.name").list();

            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return theList;
    }

    @Override
    public List<Extension> getByUserName(String userName) {
        List<Extension> theList = new ArrayList<>();

        try(Session session = factory.openSession()){
            session.beginTransaction();

            theList = session.createQuery("from Extension e where owner = :username")
                    .setParameter("username", userName)
                    .list();

            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return theList;
    }

    @Override
    public Extension getExtByName(String name) {

        List<Extension> theList = new ArrayList<>();

        try(Session session = factory.openSession()){
            session.beginTransaction();

            theList = session.createQuery("from Extension e where name = :name")
                    .setParameter("name", name)
                    .list();

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return theList.get(0);
    }


}
