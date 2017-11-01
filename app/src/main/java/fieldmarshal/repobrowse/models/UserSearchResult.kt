package fieldmarshal.repobrowse.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by fieldmarshal on 31.10.17.
 */

data class UserSearchResult (
        @Expose
        @SerializedName("total_count")
        var totalCount : Int,

        @Expose
        @SerializedName("incomplete_results")
        var incompleteResults : Boolean,

        @Expose
        @SerializedName("items")
        var items: List<Owner>
)