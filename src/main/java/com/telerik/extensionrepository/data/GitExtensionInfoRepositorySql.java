package com.telerik.extensionrepository.data;

import com.telerik.extensionrepository.data.base.GitExtensionInfoRepository;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.GitExtensionInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GitExtensionInfoRepositorySql implements GitExtensionInfoRepository {

    private SessionFactory factory;

    @Autowired
    public GitExtensionInfoRepositorySql(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public GitExtensionInfo getGitInfoById(int id) {

        GitExtensionInfo gitExtensionInfo = null;

            try(Session session = factory.openSession()){
                session.beginTransaction();

                gitExtensionInfo = session.get(GitExtensionInfo.class, id);

                session.getTransaction().commit();

            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            return gitExtensionInfo;

    }

    @Override
    public void updateGitInfo(GitExtensionInfo gitExtensionInfo) {


        try(Session session = factory.openSession()){
            session.beginTransaction();

            session.update(gitExtensionInfo);

            session.getTransaction().commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
