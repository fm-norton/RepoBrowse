package fieldmarshal.repobrowse.mvp

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

/**
 * Created by fieldmarshal on 02.11.17.
 */

interface ReposView : BaseView {
    fun onHeaderLoaded()
    fun onReposLoaded()
}

class ReposPresenter(view: ReposView) : BasePresenter<ReposView>(view) {

    fun startLoadingHeader() {}

    fun finishLoadingHeader() {}

    fun startLoadingRepos() {}

    fun finishLoadingRepos() {}

}