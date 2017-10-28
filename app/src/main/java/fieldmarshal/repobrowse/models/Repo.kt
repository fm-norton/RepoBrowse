package fieldmarshal.repobrowse.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by fieldmarshal on 27.10.17.
 */

class Repo {

    @Expose
    @SerializedName("id")
    var id: Int? = null

    @Expose
    @SerializedName("name")
    var name: String? = null

    @Expose
    @SerializedName("full_name")
    var fullName: String? = null

    @Expose
    @SerializedName("owner")
    var owner: Owner? = null

    @Expose
    @SerializedName("private")
    var _private: Boolean? = null

    @Expose
    @SerializedName("html_url")
    var htmlUrl: String? = null

    @Expose
    @SerializedName("description")
    var description: String? = null

    @Expose
    @SerializedName("fork")
    var fork: Boolean? = null

    @Expose
    @SerializedName("url")
    var url: String? = null

    @Expose
    @SerializedName("forks_url")
    var forksUrl: String? = null

    @Expose
    @SerializedName("keys_url")
    var keysUrl: String? = null

    @Expose
    @SerializedName("collaborators_url")
    var collaboratorsUrl: String? = null

    @Expose
    @SerializedName("teams_url")
    var teamsUrl: String? = null

    @Expose
    @SerializedName("hooks_url")
    var hooksUrl: String? = null

    @Expose
    @SerializedName("issue_events_url")
    var issueEventsUrl: String? = null

    @Expose
    @SerializedName("events_url")
    var eventsUrl: String? = null

    @Expose
    @SerializedName("assignees_url")
    var assigneesUrl: String? = null

    @Expose
    @SerializedName("branches_url")
    var branchesUrl: String? = null

    @Expose
    @SerializedName("tags_url")
    var tagsUrl: String? = null

    @Expose
    @SerializedName("blobs_url")
    var blobsUrl: String? = null

    @Expose
    @SerializedName("git_tags_url")
    var gitTagsUrl: String? = null

    @Expose
    @SerializedName("git_refs_url")
    var gitRefsUrl: String? = null

    @Expose
    @SerializedName("trees_url")
    var treesUrl: String? = null

    @Expose
    @SerializedName("statuses_url")
    var statusesUrl: String? = null

    @Expose
    @SerializedName("languages_url")
    var languagesUrl: String? = null

    @Expose
    @SerializedName("stargazers_url")
    var stargazersUrl: String? = null

    @Expose
    @SerializedName("contributors_url")
    var contributorsUrl: String? = null

    @Expose
    @SerializedName("subscribers_url")
    var subscribersUrl: String? = null

    @Expose
    @SerializedName("subscription_url")
    var subscriptionUrl: String? = null

    @Expose
    @SerializedName("commits_url")
    var commitsUrl: String? = null

    @Expose
    @SerializedName("git_commits_url")
    var gitCommitsUrl: String? = null

    @Expose
    @SerializedName("comments_url")
    var commentsUrl: String? = null

    @Expose
    @SerializedName("issue_comment_url")
    var issueCommentUrl: String? = null

    @Expose
    @SerializedName("contents_url")
    var contentsUrl: String? = null

    @Expose
    @SerializedName("compare_url")
    var compareUrl: String? = null

    @Expose
    @SerializedName("merges_url")
    var mergesUrl: String? = null

    @Expose
    @SerializedName("archive_url")
    var archiveUrl: String? = null

    @Expose
    @SerializedName("downloads_url")
    var downloadsUrl: String? = null

    @Expose
    @SerializedName("issues_url")
    var issuesUrl: String? = null

    @Expose
    @SerializedName("pulls_url")
    var pullsUrl: String? = null

    @Expose
    @SerializedName("milestones_url")
    var milestonesUrl: String? = null

    @Expose
    @SerializedName("notifications_url")
    var notificationsUrl: String? = null

    @Expose
    @SerializedName("labels_url")
    var labelsUrl: String? = null

    @Expose
    @SerializedName("releases_url")
    var releasesUrl: String? = null

    @Expose
    @SerializedName("deployments_url")
    var deploymentsUrl: String? = null

    @Expose
    @SerializedName("created_at")
    var createdAt: String? = null

    @Expose
    @SerializedName("updated_at")
    var updatedAt: String? = null

    @Expose
    @SerializedName("pushed_at")
    var pushedAt: String? = null

    @Expose
    @SerializedName("git_url")
    var gitUrl: String? = null

    @Expose
    @SerializedName("ssh_url")
    var sshUrl: String? = null

    @Expose
    @SerializedName("clone_url")
    var cloneUrl: String? = null

    @Expose
    @SerializedName("svn_url")
    var svnUrl: String? = null

    @Expose
    @SerializedName("homepage")
    var homepage: String? = null

    @Expose
    @SerializedName("size")
    var size: Int? = null

    @Expose
    @SerializedName("stargazers_count")
    var stargazersCount: Int? = null

    @Expose
    @SerializedName("watchers_count")
    var watchersCount: Int? = null

    @Expose
    @SerializedName("language")
    var language: String? = null

    @Expose
    @SerializedName("has_issues")
    var hasIssues: Boolean? = null

    @Expose
    @SerializedName("has_projects")
    var hasProjects: Boolean? = null

    @Expose
    @SerializedName("has_downloads")
    var hasDownloads: Boolean? = null

    @Expose
    @SerializedName("has_wiki")
    var hasWiki: Boolean? = null

    @Expose
    @SerializedName("has_pages")
    var hasPages: Boolean? = null

    @Expose
    @SerializedName("forks_count")
    var forksCount: Int? = null

    @Expose
    @SerializedName("mirror_url")
    var mirrorUrl: Any? = null

    @Expose
    @SerializedName("archived")
    var archived: Boolean? = null

    @Expose
    @SerializedName("open_issues_count")
    var openIssuesCount: Int? = null

    @Expose
    @SerializedName("forks")
    var forks: Int? = null

    @Expose
    @SerializedName("open_issues")
    var openIssues: Int? = null

    @Expose
    @SerializedName("watchers")
    var watchers: Int? = null

    @Expose
    @SerializedName("default_branch")
    var defaultBranch: String? = null
}
