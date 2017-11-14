package fieldmarshal.repobrowse.mvp

import android.content.Context
import android.util.Log
import fieldmarshal.repobrowse.models.Owner
import fieldmarshal.repobrowse.models.UserSearchResult
import fieldmarshal.repobrowse.ui.UsersFragment
import fieldmarshal.repobrowse.util.Constants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by fieldmarshal on 02.11.17.
 */

interface UsersView : BaseView {
    fun onUsersLoaded()
}

class UsersPresenter(view: UsersView) : BasePresenter<UsersView>(view) {

    private val queryStr = StringBuilder()
            .append(Constants.Q_CREATED)
            .append(">=").append(Constants.Q_DATE).toString()
    private var call: Observable<UserSearchResult> = githubRest.getUsers(queryStr)
    private var itemInsertPos = 0
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
                                        owners = response.items
                                        ownerMutableList.addAll(owners)
                                        //ownersAdapter
                                          //      ?.notifyItemRangeInserted(itemInsertPos, owners.size)
                                        view.onUsersLoaded()
                                        view.hideProgress()
                                    },
                                    { t: Throwable ->
                                        view.onError(t)
                                        Log.e(UsersFragment.TAG, "Exception", t)
                                    },
                                    { Log.d(UsersFragment.TAG, "Call completed") }
                            )
            )
        }
    }

}