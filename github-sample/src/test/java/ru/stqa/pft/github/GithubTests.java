package ru.stqa.pft.github;

import com.google.common.collect.ImmutableBiMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {
    @Test
    public void testsCommits() throws IOException {
        Github github = new RtGithub("9c4c2c68a6d6460c698053cce53c5785a35989cc");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("ViktorKovalenko", "java_pft")).commits();
        for(RepoCommit commit: commits.iterate(new ImmutableBiMap.Builder<String, String>().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
