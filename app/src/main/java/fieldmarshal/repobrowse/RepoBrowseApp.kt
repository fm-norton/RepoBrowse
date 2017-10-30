package fieldmarshal.repobrowse

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

/**
 * Created by fieldmarshal on 30.10.17.
 */

class RepoBrowseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}
