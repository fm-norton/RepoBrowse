package fieldmarshal.repobrowse.mvp

import android.util.Log
import fieldmarshal.repobrowse.models.Repo
import fieldmarshal.repobrowse.models.User
import fieldmarshal.repobrowse.ui.ReposFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by fieldmarshal on 02.11.17.
 */

interface ReposView : BaseView {
    fun inflateToolbar(user: User)
    fun onReposLoaded()
    fun showToolbarProgress()
    fun hideToolbarProgress()
}

class ReposPresenter(view: ReposView) : BasePresenter<ReposView>(view) {
    private lateinit var username: String
    private lateinit var callRepos: Observable<List<Repo>>
    private lateinit var callUserInfo: Observable<User>

    var repos = listOf<Repo>()
        private set
    var reposMutableList = mutableListOf<Repo>()
        private set

    private lateinit var user: User

    override fun dispose() {
        super.dispose()
    }

    fun initServiceCalls(arg: String) {
        username = arg
        callRepos = githubRest.reposOfUser(username)
        callUserInfo = githubRest.getUserInfo(username)
    }

    fun loadToolbar() {
        view.showToolbarProgress()
        disposable.add(
                callUserInfo.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { response ->
                                    user = response
                                    view.inflateToolbar(user)
                                    view.hideToolbarProgress()
                                    // TODO load image, change toolbar title and hide progress after end
                                    //backdrop.loadUrlAndCropCircle(user.avatarUrl)
                                    //collapsing_toolbar.title = user.name
                                    //var reposCount = String.format(resources
                                    //       .getString(R.string.repos_count), user.publicRepos)
                                    //collapsing_toolbar. = reposCount
                                    //pbRepos.visibility = View.GONE
                                },
                                { t ->
                                    view.onError(t)
                                    t.printStackTrace()
                                }, { Log.d(ReposFragment.TAG, "UserInfo call completed") }
                        )
        )
    }

    fun loadRepos() {
        view.showProgress()
        disposable.add(
                callRepos.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { response ->
                                    repos = response
                                    reposMutableList.addAll(repos)
                                    view.onReposLoaded()
                                    view.hideProgress()
                                },
                                { t ->
                                    view.onError(t)
                                    Log.e(ReposFragment.TAG, t.message, t)
                                }, { Log.d(ReposFragment.TAG, "Repos call completed") }
                        )
        )
    }
}