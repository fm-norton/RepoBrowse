package fieldmarshal.repobrowse.util

import android.support.annotation.NonNull
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

/**
 * Created by fieldmarshal on 30.10.17.
 */

class DateUtils {
    companion object {
        fun getDate(@NonNull isoDateString: String): ZonedDateTime {
            return ZonedDateTime.parse(isoDateString).withZoneSameInstant(ZoneId.systemDefault())
        }

        // TODO call this as follows: var date = getPrettyDate("dd.MM.YYYY", createdAt)
        fun getPrettyDate(@NonNull format : String, @NonNull isoDateString: String): String {
            return DateTimeFormatter.ofPattern(format).format(getDate(isoDateString))
        }
    }
}