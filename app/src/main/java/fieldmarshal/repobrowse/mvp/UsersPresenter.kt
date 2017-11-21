package fieldmarshal.repobrowse.mvp

import android.util.Log
import fieldmarshal.repobrowse.models.Owner
import fieldmarshal.repobrowse.models.UserSearchResult
import fieldmarshal.repobrowse.ui.UsersFragment
import fieldmarshal.repobrowse.util.Constants
import fieldmarshal.repobrowse.util.LinkPager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

/**
 * Created by fieldmarshal on 02.11.17.
 */

interface UsersView : BaseView {
    fun onUsersLoaded()
    fun onMoreUsersLoaded()
}

class UsersPresenter(view: UsersView) : BasePresenter<UsersView>(view) {

    private val queryStr = StringBuilder()
            .append(Constants.Q_CREATED)
            .append(">=").append(Constants.Q_DATE).toString()

    private var call: Observable<Response<UserSearchResult>> = githubRest.getUsers(queryStr)

    lateinit var nextPageUrl: String
    lateinit var lastPageUrl: String
    var nextPage: Int = 0
    private lateinit var callNextPage: Observable<Response<UserSearchResult>>

    var owners = listOf<Owner>()
        private set
    var ownerMutableList = mutableListOf<Owner>()
        private set

    override fun dispose() = super.dispose()

    fun loadUsers() {
        if (ownerMutableList.isEmpty()) {
            view.showProgress()
            disposable.add(
                    call.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    { response ->
                                        nextPageUrl = LinkPager(response.headers()).next!!
                                        owners = response.body()!!.items
                                        ownerMutableList.addAll(owners)
                                        view.onUsersLoaded()
                                        view.hideProgress()
                                    },
                                    { t ->
                                        view.hideProgress()
                                        view.onError(t)
                                        Log.e(UsersFragment.TAG, "Exception", t)
                                    }, { Log.d(UsersFragment.TAG, "Call completed") }
                            )
            )
        }
    }

    fun loadMoreUsers() {
        view.showProgress()
        callNextPage = githubRest.getUsersPaginated(nextPageUrl)

        disposable.add(
                callNextPage.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { response ->
                                    val pager = LinkPager(response.headers())
                                    nextPageUrl = pager.next!!
                                    owners = response.body()!!.items
                                    ownerMutableList.addAll(owners)
                                    view.onMoreUsersLoaded()
                                    view.hideProgress()
                                },
                                { t ->
                                    view.hideProgress()
                                    view.onError(t)
                                    Log.e(UsersFragment.TAG, "Exception", t)
                                }, { Log.d(UsersFragment.TAG, "CallNextPage completed") }
                        )
        )
    }

}