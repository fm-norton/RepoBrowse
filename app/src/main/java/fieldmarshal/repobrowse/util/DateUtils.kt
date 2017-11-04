package fieldmarshal.repobrowse.util

import android.support.annotation.NonNull
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

/**
 * Created by fieldmarshal on 30.10.17.
 */

class DateUtils {
    companion object {
        fun getDate(@NonNull isoDateString: String): LocalDateTime {
            return LocalDateTime.parse(isoDateString, DateTimeFormatter.ISO_DATE_TIME)
        }

        //  call this as follows: var date = getPrettyDate("dd.MM.YYYY", createdAt)
        fun getPrettyDate(@NonNull format : String, @NonNull isoDateString: String): String {
            return DateTimeFormatter.ofPattern(format).format(getDate(isoDateString))
        }
    }
}