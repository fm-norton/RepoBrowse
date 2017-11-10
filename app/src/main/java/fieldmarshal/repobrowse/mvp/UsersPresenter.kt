package fieldmarshal.repobrowse.mvp

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

/**
 * Created by fieldmarshal on 02.11.17.
 */

interface UsersView : BaseView {
    fun onLoadStart()
    fun onLoadFinish()
    fun onError(t : Throwable)
}

class UsersPresenter(view: UsersView) : BasePresenter<UsersView>(view) {

    fun startLoading() {}

    fun finishLoading() {}

}