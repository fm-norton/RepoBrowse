package fieldmarshal.repobrowse.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by fieldmarshal on 29.10.17.
 */
data class User (
        @Expose
        @SerializedName("id")
        val id: Long,

        @Expose
        @SerializedName("login")
        val login: String,

        @Expose
        @SerializedName("url")
        var url: String,

        @Expose
        @SerializedName("avatar_url")
        var avatarUrl: String,

        @Expose
        @SerializedName("name")
        var name: String,

        @Expose
        @SerializedName("public_repos")
        var publicRepos: Int
)