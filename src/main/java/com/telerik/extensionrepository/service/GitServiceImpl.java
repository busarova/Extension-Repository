package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.exceptions.GithubNameException;
import com.telerik.extensionrepository.exceptions.GithubSyncException;
import com.telerik.extensionrepository.model.GitExtensionInfo;
import com.telerik.extensionrepository.service.base.GitService;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class GitServiceImpl implements GitService {

    //If the link is different than null or empty string
    //Then connect with Git Api and get the data then return the GitExtensionInfo object full
    //Otherwise return empty

    @Override
    public GitExtensionInfo getGitDetails(String gitLink) throws GithubSyncException, GithubNameException {

        final String token = "6bd730709f26540abf3e15e3a6dcbf1bd4a6693c";

        if (gitLink == null || gitLink.equals("")) {
            return new GitExtensionInfo();
        }
        GitHub gitHub = null;

        try {
            gitHub = GitHub.connectUsingOAuth(token);
        } catch (IOException e) {
            e.printStackTrace();
            throw new GithubSyncException("There was a problem with the OAuth token for github");
        }

        String gitUserRepo = "";

        try {

            String[] link = gitLink.replaceAll("https://github.com/", "").split("/");
            gitUserRepo = link[0] + "/" + link[1];

        }catch (Exception e){
            throw new GithubNameException("The Github name you have entered is invalid.Cannot make a connection.No extension created.");
        }

        try {

            GHRepository repo = gitHub.getRepository(gitUserRepo);

            int pullRequests = repo.getPullRequests(GHIssueState.OPEN).size();
            int openIssues = repo.getOpenIssueCount();
            List<GHCommit> commits = repo.listCommits().asList();
            Date lastCommitDate = commits.get(0).getCommitDate();

            GitExtensionInfo newGit = new GitExtensionInfo(openIssues, pullRequests, lastCommitDate);

            newGit.setGitRepoLink(gitLink);

            return newGit;

        } catch (IOException e) {
            e.printStackTrace();
            throw new GithubSyncException("There was a problem retrieving data from Github.");
        }

    }
}
