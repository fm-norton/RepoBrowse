package fieldmarshal.repobrowse.util

/**
 * Created by fieldmarshal on 31.10.17.
 */

enum class UserType(val t: String) { USER("user"), ORG("org") }

class Constants {
    companion object {
        val Q_REPOS = "repos"
        val Q_DATE = "2009-06-19"
        val Q_LOCATION = "location:"
        val Q_LANGUAGE = "language:"
        val Q_CREATED = "created:"
        val Q_FOLLOWERS = "followers:"
    }
}