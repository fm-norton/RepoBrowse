package fieldmarshal.repobrowse.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by fieldmarshal on 27.10.17.
 */

class Owner {

    @Expose
    @SerializedName("login")
    var login: String? = null

    @Expose
    @SerializedName("id")
    var id: Int? = null

    @Expose
    @SerializedName("avatar_url")
    var avatarUrl: String? = null

    @Expose
    @SerializedName("gravatar_id")
    var gravatarId: String? = null

    @Expose
    @SerializedName("url")
    var url: String? = null

    @Expose
    @SerializedName("html_url")
    var htmlUrl: String? = null

    @Expose
    @SerializedName("followers_url")
    var followersUrl: String? = null

    @Expose
    @SerializedName("following_url")
    var followingUrl: String? = null

    @Expose
    @SerializedName("gists_url")
    var gistsUrl: String? = null

    @Expose
    @SerializedName("starred_url")
    var starredUrl: String? = null

    @Expose
    @SerializedName("subscriptions_url")
    var subscriptionsUrl: String? = null

    @Expose
    @SerializedName("organizations_url")
    var organizationsUrl: String? = null

    @Expose
    @SerializedName("repos_url")
    var reposUrl: String? = null

    @Expose
    @SerializedName("events_url")
    var eventsUrl: String? = null

    @Expose
    @SerializedName("received_events_url")
    var receivedEventsUrl: String? = null

    @Expose
    @SerializedName("type")
    var type: String? = null

    @Expose
    @SerializedName("site_admin")
    var siteAdmin: Boolean? = null
}
