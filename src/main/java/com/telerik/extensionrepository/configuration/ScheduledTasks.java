package com.telerik.extensionrepository.configuration;

import com.telerik.extensionrepository.service.base.AdminService;
import com.telerik.extensionrepository.utils.exceptions.RepositoryException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private AdminService adminService;

    public ScheduledTasks(AdminService adminService){
        this.adminService = adminService;
    }

    //configured for 1 week refresh with 1 week initial delay

    @Scheduled(initialDelay=604800000, fixedRate = 604800000)
    public void reportCurrentTime() throws RepositoryException {

        adminService.refreshAllGitHubInfo();

    }

}
