package fieldmarshal.repobrowse.mvp

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import fieldmarshal.repobrowse.api.ApiServiceGenerator
import fieldmarshal.repobrowse.api.GithubRest
import fieldmarshal.repobrowse.util.NetworkStateReceiver
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by fieldmarshal on 09.11.17.
 */


interface BaseView {
    fun onOnlineCheck()
    fun showProgress()
    fun hideProgress()
    fun onError(t : Throwable)
}

open class BasePresenter<out T>(protected val view: T) where T : BaseView {
    protected val disposable = CompositeDisposable()
    protected val githubRest = ApiServiceGenerator.createService(GithubRest::class.java)

    // Mind that receiver registration / release are to be directly in the Activity
    private var networkStateReceiver = NetworkStateReceiver()
    private var intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)

    init {

    }

    open fun dispose() {
        disposable.clear()
        if (!disposable.isDisposed) disposable.dispose()
    }

    open fun initRecyclerAdapter(context: Context) {}

    fun checkNetworkState(context: Context)
            = (networkStateReceiver.isNetworkAvailable(context) && networkStateReceiver.isOnline)
}