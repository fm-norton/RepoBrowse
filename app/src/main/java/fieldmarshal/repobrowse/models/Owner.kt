package fieldmarshal.repobrowse.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by fieldmarshal on 27.10.17.
 */

data class Owner (
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
    @SerializedName("repos_url")
    var reposUrl: String
)
