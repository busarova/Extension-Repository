package com.telerik.extensionrepository.data;

import com.telerik.extensionrepository.data.base.FileRepository;
import com.telerik.extensionrepository.model.UploadFile;
import com.telerik.extensionrepository.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileRepositorySql implements FileRepository {

    private SessionFactory factory;
    private static final Logger logger = LoggerFactory.getLogger(FileRepositorySql.class);

    @Autowired
    public FileRepositorySql(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void saveUploadFile(UploadFile uploadFile) {

        try(Session session = factory.openSession()){
            session.beginTransaction();

            session.save(uploadFile);

            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void updateUploadFile(UploadFile uploadFile) {

        try(Session session = factory.openSession()){
            session.beginTransaction();

            session.update(uploadFile);

            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveProfilePic(User user) {

        try(Session session = factory.openSession()){
            session.beginTransaction();

            session.update(user);

            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    @Override
    public UploadFile getFile(int id) {

        UploadFile file = null;

        try(Session session = factory.openSession()){
            session.beginTransaction();

            file = session.get(UploadFile.class, id);

            session.getTransaction().commit();
        }catch (Exception e){
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }

        return file;
    }
}
