package fieldmarshal.repobrowse.views

import com.arellomobile.mvp.MvpView

/**
 * Created by fieldmarshal on 02.11.17.
 */
interface UsersView : MvpView {
    fun showError(message: String)

    fun hideError()

    fun onStartLoading()

    fun onFinishLoading()

}