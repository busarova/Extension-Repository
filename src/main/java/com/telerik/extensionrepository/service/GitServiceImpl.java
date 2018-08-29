package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.data.base.GitExtensionInfoRepository;
import com.telerik.extensionrepository.model.GitExtensionInfo;
import com.telerik.extensionrepository.service.base.GitService;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class GitServiceImpl implements GitService {

    private GitExtensionInfoRepository gitRepository;
    private GitHub gitHub;

    @Autowired
    public GitServiceImpl(GitExtensionInfoRepository gitRepository, GitHub gitHub) {
        this.gitRepository = gitRepository;
        this.gitHub = gitHub;
    }

    @Override
    public GitExtensionInfo getGitDetails(String gitLink) {

        String[] link = gitLink.replaceAll("https://github.com/", "").split("/");
        String gitUserRepo = link[0] + "/" + link[1];

        try {
            GHRepository repo = gitHub.getRepository(gitUserRepo);
            int pullRequests = repo.getPullRequests(GHIssueState.OPEN).size();
            int openIssues = repo.getIssues(GHIssueState.ALL).size();
            List<GHCommit> commits = repo.listCommits().asList();
            String lastCommitDate = commits.get(0).getCommitDate().toString();

            System.out.println("--------------------------------asdsdasdas-da-sd-sa");
            System.out.println("PULL: " + pullRequests);
            System.out.println("OPEN ISSUES: " + openIssues);
            System.out.println("number of commits: " + commits.size());
            System.out.println("last one: " + lastCommitDate);

            return new GitExtensionInfo(openIssues, pullRequests, lastCommitDate);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new GitExtensionInfo();
    }
}
