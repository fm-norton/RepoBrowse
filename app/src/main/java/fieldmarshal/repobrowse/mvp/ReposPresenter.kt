package fieldmarshal.repobrowse.mvp

import android.util.Log
import fieldmarshal.repobrowse.models.Repo
import fieldmarshal.repobrowse.models.User
import fieldmarshal.repobrowse.ui.ReposFragment
import fieldmarshal.repobrowse.util.LinkPager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

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
    private lateinit var callRepos: Observable<Response<List<Repo>>>
    private lateinit var callUserInfo: Observable<User>
    private lateinit var callNextPage: Observable<Response<List<Repo>>>

    var repos = listOf<Repo>()
        private set
    var reposMutableList = mutableListOf<Repo>()
        private set

    private lateinit var user: User
    private lateinit var nextPageUrl: String
    private lateinit var lastPageUrl: String

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
                callUserInfo.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { response ->
                                    user = response
                                    view.inflateToolbar(user)
                                    view.hideToolbarProgress()
                                },
                                { t ->
                                    view.hideToolbarProgress()
                                    view.onError(t)
                                    t.printStackTrace()
                                }, { Log.d(ReposFragment.TAG, "UserInfo call completed") }
                        )
        )
    }

    fun loadRepos() {
        view.showProgress()
        disposable.add(
                callRepos.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { response ->
                                    val linkPager = LinkPager(response.headers())
                                    nextPageUrl = linkPager.next!!
                                    lastPageUrl = linkPager.last!!
                                    repos = response.body()!!
                                    reposMutableList.addAll(repos)
                                    view.onReposLoaded()
                                    view.hideProgress()
                                },
                                { t ->
                                    view.hideProgress()
                                    view.onError(t)
                                    Log.e(ReposFragment.TAG, t.message, t)
                                }, { Log.d(ReposFragment.TAG, "Repos call completed") }
                        )
        )
    }

    fun loadMoreRepos() {
        // TODO Change progress showing methods to footer progressbars
        view.showProgress()
        callNextPage = githubRest.reposOfUserPaginated(nextPageUrl)
        disposable.add(
                callNextPage.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { response ->
                                    nextPageUrl = LinkPager(response.headers()).next!!
                                    repos = response.body()!!
                                    reposMutableList.addAll(repos)
                                    view.onReposLoaded()
                                    view.hideProgress()
                                },
                                { t ->
                                    view.hideProgress()
                                    view.onError(t)
                                    Log.e(ReposFragment.TAG, t.message, t)
                                },
                                { Log.d(ReposFragment.TAG, "Repos callNextPage completed") }
                        )
        )
    }
}