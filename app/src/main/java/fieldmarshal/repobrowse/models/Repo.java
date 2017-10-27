package fieldmarshal.repobrowse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fieldmarshal on 27.10.17.
 */

public class Repo {

    @Expose
    @SerializedName("id")
    public Integer id;

    @Expose
    @SerializedName("name")
    public String name;

    @Expose
    @SerializedName("full_name")
    public String fullName;

    @Expose
    @SerializedName("owner")
    public Owner owner;

    @Expose
    @SerializedName("private")
    public Boolean _private;

    @Expose
    @SerializedName("html_url")
    public String htmlUrl;

    @Expose
    @SerializedName("description")
    public String description;

    @Expose
    @SerializedName("fork")
    public Boolean fork;

    @Expose
    @SerializedName("url")
    public String url;

    @Expose
    @SerializedName("forks_url")
    public String forksUrl;

    @Expose
    @SerializedName("keys_url")
    public String keysUrl;

    @Expose
    @SerializedName("collaborators_url")
    public String collaboratorsUrl;

    @Expose
    @SerializedName("teams_url")
    public String teamsUrl;

    @Expose
    @SerializedName("hooks_url")
    public String hooksUrl;

    @Expose
    @SerializedName("issue_events_url")
    public String issueEventsUrl;

    @Expose
    @SerializedName("events_url")
    public String eventsUrl;

    @Expose
    @SerializedName("assignees_url")
    public String assigneesUrl;

    @Expose
    @SerializedName("branches_url")
    public String branchesUrl;

    @Expose
    @SerializedName("tags_url")
    public String tagsUrl;

    @Expose
    @SerializedName("blobs_url")
    public String blobsUrl;

    @Expose
    @SerializedName("git_tags_url")
    public String gitTagsUrl;

    @Expose
    @SerializedName("git_refs_url")
    public String gitRefsUrl;

    @Expose
    @SerializedName("trees_url")
    public String treesUrl;

    @Expose
    @SerializedName("statuses_url")
    public String statusesUrl;

    @Expose
    @SerializedName("languages_url")
    public String languagesUrl;

    @Expose
    @SerializedName("stargazers_url")
    public String stargazersUrl;

    @Expose
    @SerializedName("contributors_url")
    public String contributorsUrl;

    @Expose
    @SerializedName("subscribers_url")
    public String subscribersUrl;

    @Expose
    @SerializedName("subscription_url")
    public String subscriptionUrl;

    @Expose
    @SerializedName("commits_url")
    public String commitsUrl;

    @Expose
    @SerializedName("git_commits_url")
    public String gitCommitsUrl;

    @Expose
    @SerializedName("comments_url")
    public String commentsUrl;

    @Expose
    @SerializedName("issue_comment_url")
    public String issueCommentUrl;

    @Expose
    @SerializedName("contents_url")
    public String contentsUrl;

    @Expose
    @SerializedName("compare_url")
    public String compareUrl;

    @Expose
    @SerializedName("merges_url")
    public String mergesUrl;

    @Expose
    @SerializedName("archive_url")
    public String archiveUrl;

    @Expose
    @SerializedName("downloads_url")
    public String downloadsUrl;

    @Expose
    @SerializedName("issues_url")
    public String issuesUrl;

    @Expose
    @SerializedName("pulls_url")
    public String pullsUrl;

    @Expose
    @SerializedName("milestones_url")
    public String milestonesUrl;

    @Expose
    @SerializedName("notifications_url")
    public String notificationsUrl;

    @Expose
    @SerializedName("labels_url")
    public String labelsUrl;

    @Expose
    @SerializedName("releases_url")
    public String releasesUrl;

    @Expose
    @SerializedName("deployments_url")
    public String deploymentsUrl;

    @Expose
    @SerializedName("created_at")
    public String createdAt;

    @Expose
    @SerializedName("updated_at")
    public String updatedAt;

    @Expose
    @SerializedName("pushed_at")
    public String pushedAt;

    @Expose
    @SerializedName("git_url")
    public String gitUrl;

    @Expose
    @SerializedName("ssh_url")
    public String sshUrl;

    @Expose
    @SerializedName("clone_url")
    public String cloneUrl;

    @Expose
    @SerializedName("svn_url")
    public String svnUrl;

    @Expose
    @SerializedName("homepage")
    public String homepage;

    @Expose
    @SerializedName("size")
    public Integer size;

    @Expose
    @SerializedName("stargazers_count")
    public Integer stargazersCount;

    @Expose
    @SerializedName("watchers_count")
    public Integer watchersCount;

    @Expose
    @SerializedName("language")
    public String language;

    @Expose
    @SerializedName("has_issues")
    public Boolean hasIssues;

    @Expose
    @SerializedName("has_projects")
    public Boolean hasProjects;

    @Expose
    @SerializedName("has_downloads")
    public Boolean hasDownloads;

    @Expose
    @SerializedName("has_wiki")
    public Boolean hasWiki;

    @Expose
    @SerializedName("has_pages")
    public Boolean hasPages;

    @Expose
    @SerializedName("forks_count")
    public Integer forksCount;

    @Expose
    @SerializedName("mirror_url")
    public Object mirrorUrl;

    @Expose
    @SerializedName("archived")
    public Boolean archived;

    @Expose
    @SerializedName("open_issues_count")
    public Integer openIssuesCount;

    @Expose
    @SerializedName("forks")
    public Integer forks;

    @Expose
    @SerializedName("open_issues")
    public Integer openIssues;

    @Expose
    @SerializedName("watchers")
    public Integer watchers;

    @Expose
    @SerializedName("default_branch")
    public String defaultBranch;

}
