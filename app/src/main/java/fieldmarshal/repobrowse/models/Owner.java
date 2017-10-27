package fieldmarshal.repobrowse.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fieldmarshal on 27.10.17.
 */

public class Owner {

    @Expose
    @SerializedName("login")
    public String login;

    @Expose
    @SerializedName("id")
    public Integer id;

    @Expose
    @SerializedName("avatar_url")
    public String avatarUrl;

    @Expose
    @SerializedName("gravatar_id")
    public String gravatarId;

    @Expose
    @SerializedName("url")
    public String url;

    @Expose
    @SerializedName("html_url")
    public String htmlUrl;

    @Expose
    @SerializedName("followers_url")
    public String followersUrl;

    @Expose
    @SerializedName("following_url")
    public String followingUrl;

    @Expose
    @SerializedName("gists_url")
    public String gistsUrl;

    @Expose
    @SerializedName("starred_url")
    public String starredUrl;

    @Expose
    @SerializedName("subscriptions_url")
    public String subscriptionsUrl;

    @Expose
    @SerializedName("organizations_url")
    public String organizationsUrl;

    @Expose
    @SerializedName("repos_url")
    public String reposUrl;

    @Expose
    @SerializedName("events_url")
    public String eventsUrl;

    @Expose
    @SerializedName("received_events_url")
    public String receivedEventsUrl;

    @Expose
    @SerializedName("type")
    public String type;

    @Expose
    @SerializedName("site_admin")
    public Boolean siteAdmin;

}
