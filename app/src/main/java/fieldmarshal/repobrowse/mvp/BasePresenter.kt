package fieldmarshal.repobrowse.mvp

import android.content.BroadcastReceiver
import fieldmarshal.repobrowse.util.NetworkStateReceiver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by fieldmarshal on 09.11.17.
 */

interface BaseView {
    // TODO write general methods for all the inherent interfaces if necessary
    fun onError(t : Throwable)
    //fun showLoading()
    //fun hideLoading()
}

open class BasePresenter<out T>(protected val view: T) where T : BaseView {
    protected val disposable = CompositeDisposable()

    protected lateinit var networkStateReceiver : NetworkStateReceiver

    init {

    }

    protected fun registerReceiver(receiver: BroadcastReceiver) {

    }

    protected fun addCall(d : Disposable) {
        disposable.add(d)
    }

    protected fun clearCalls() {
        disposable.clear()
    }

    protected fun dispose() {
        disposable.dispose()
    }

    fun checkNetworkState() = false

}