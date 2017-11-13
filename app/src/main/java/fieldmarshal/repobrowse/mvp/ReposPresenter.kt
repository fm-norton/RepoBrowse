package fieldmarshal.repobrowse.mvp

import android.util.Log
import fieldmarshal.repobrowse.models.Repo
import fieldmarshal.repobrowse.models.User
import fieldmarshal.repobrowse.ui.ReposFragment
import fieldmarshal.repobrowse.ui.adapters.RepoAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by fieldmarshal on 02.11.17.
 */

interface ReposView : BaseView {
    fun onHeaderLoaded()
    fun onReposLoaded()
}

class ReposPresenter(view: ReposView) : BasePresenter<ReposView>(view) {
    private lateinit var username: String
    val callRepos: Observable<List<Repo>> = githubRest.reposOfUser(username)
    val callUserInfo: Observable<User> = githubRest.getUserInfo(username)

    private var repos = listOf<Repo>()
    private var reposMutableList = mutableListOf<Repo>()
    private var itemInsertPos = 0
    private lateinit var reposAdapter: RepoAdapter

    private lateinit var user: User

    fun loadToolbar() {
        disposable.add(
                callUserInfo.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { response ->
                                    user = response
                                    // TODO load image, change toolbar title and hide progress after end
                                    //backdrop.loadUrlAndCropCircle(user.avatarUrl)
                                    //collapsing_toolbar.title = user.name
                                    //var reposCount = String.format(resources
                                    //       .getString(R.string.repos_count), user.publicRepos)
                                    //collapsing_toolbar. = reposCount
                                    //pbRepos.visibility = View.GONE
                                },
                                {
                                    t -> t.printStackTrace()
                                }, { Log.d(ReposFragment.TAG, "UserInfo call completed") }
                        )
        )
    }

    fun loadRepos() {
        // TODO make views show & hide just in time
        disposable.add(
                callRepos.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { response ->
                                    repos = response
                                    reposMutableList.addAll(repos)
                                    reposAdapter.notifyItemRangeInserted(itemInsertPos, repos.size)
                                    //pbRepos.visibility = View.GONE
                                },
                                { t ->
                                   // TODO tell user about this error cause
                                   // Toast.makeText(context, "Error receiving data",
                                   //         Toast.LENGTH_LONG).show()
                                    Log.e(ReposFragment.TAG, t.message, t)
                                },
                                {
                                    Log.d(ReposFragment.TAG, "Repos call completed")
                                    //backdrop.loadUrlAndCropCircle(repos)
                                }
                        )
        )
    }
}