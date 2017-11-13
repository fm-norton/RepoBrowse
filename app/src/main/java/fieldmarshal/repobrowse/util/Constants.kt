package fieldmarshal.repobrowse.util

import android.graphics.Typeface

/**
 * Created by fieldmarshal on 31.10.17.
 */

enum class UserType(val type: String) { USER("user"), ORG("org") }

class Constants {
    companion object {
        val Q_REPOS = "repos"
        val Q_DATE = "2009-06-19"
        val Q_LOCATION = "location:"
        val Q_LANGUAGE = "language:"
        val Q_CREATED = "created:"
        val Q_FOLLOWERS = "followers:"
        val ROBOTO_BOLD = "RobotoSlab-Bold.ttf"
        val ROBOTO_REGULAR = "RobotoSlab-Regular.ttf"
        val ROBOTO_LIGHT = "RobotoSlab-Light.ttf"
        val ROBOTO_THIN = "RobotoSlab-Thin.ttf"

        val CONNECT_OK = "Connected"
        val CONNECT_FAIL = "No connection, check it and try again"
        val NOTHING = "I am doing nothing!"
        val ERROR_IO = "Error receiving data. Check your internet connection"
    }
}