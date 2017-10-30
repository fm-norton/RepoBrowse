package fieldmarshal.repobrowse.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by fieldmarshal on 27.10.17.
 */

data class Repo (
        @Expose
        @SerializedName("id")
        val id: Long,

        @Expose
        @SerializedName("name")
        var name: String,

        @Expose
        @SerializedName("full_name")
        var fullName: String,

        @Expose
        @SerializedName("owner")
        var owner: Owner,

        @Expose
        @SerializedName("created_at")
        var createdAt: String,

        @Expose
        @SerializedName("url")
        var url: String,

        @Expose
        @SerializedName("description")
        var description: String,

        @Expose
        @SerializedName("watchers_count")
        var watchers: Int

)
