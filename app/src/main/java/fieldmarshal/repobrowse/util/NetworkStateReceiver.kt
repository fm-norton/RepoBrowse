package fieldmarshal.repobrowse.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast

import java.io.IOException

/**
 * Created by fieldmarshal on 10.11.17.
 */

class NetworkStateReceiver : BroadcastReceiver() {

    val isOnline: Boolean
        get() {
            try {
                val ipProcess = Runtime.getRuntime().exec("/system/bin/ping -c 1 8.8.8.8")
                return ipProcess.waitFor() == 0     // exit value
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            return false
        }

    fun isNetworkAvailable(context: Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = manager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, intent.action)
        if (isNetworkAvailable(context) && isOnline) {
            longToast(context, Constants.CONNECT_OK)
        } else {
            longToast(context, Constants.CONNECT_FAIL)
        }
    }

    companion object {
        val TAG = "NetworkStateReceiver"
    }
}
