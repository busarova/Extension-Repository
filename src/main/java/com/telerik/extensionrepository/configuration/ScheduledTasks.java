package com.telerik.extensionrepository.configuration;

import com.telerik.extensionrepository.service.base.AdminService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private AdminService adminService;

    public ScheduledTasks(AdminService adminService){
        this.adminService = adminService;
    }

    /*@Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {

        adminService.refreshAllGitHubInfo();

    }*/

}
