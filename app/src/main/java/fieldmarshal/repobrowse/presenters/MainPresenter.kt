package fieldmarshal.repobrowse.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import fieldmarshal.repobrowse.views.MainView

/**
 * Created by fieldmarshal on 02.11.17.
 */

@InjectViewState
class MainPresenter: MvpPresenter<MainView>() {
    fun onShowContainer() {
        viewState.showContainer()
    }

}