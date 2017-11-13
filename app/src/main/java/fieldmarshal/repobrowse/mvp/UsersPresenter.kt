package fieldmarshal.repobrowse.mvp

import android.util.Log
import fieldmarshal.repobrowse.models.Owner
import fieldmarshal.repobrowse.models.UserSearchResult
import fieldmarshal.repobrowse.ui.UsersFragment
import fieldmarshal.repobrowse.ui.adapters.OwnersAdapter
import fieldmarshal.repobrowse.util.Constants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by fieldmarshal on 02.11.17.
 */

interface UsersView : BaseView {
    fun onLoadStart()
    fun onLoadFinish()
    fun onUserClick(owner: Owner)
    fun onLoadReposFragment()
}

class UsersPresenter(view: UsersView) : BasePresenter<UsersView>(view) {

    private val queryStr = StringBuilder()
            .append(Constants.Q_CREATED)
            .append(">=").append(Constants.Q_DATE).toString()
    private var call: Observable<UserSearchResult> = githubRest.getUsers(queryStr)
    private var itemInsertPos = 0
    private var owners = listOf<Owner>()
    private var ownerMutableList = mutableListOf<Owner>()

    private lateinit var ownersAdapter: OwnersAdapter

    fun loadUsers() {
        if (ownerMutableList.isEmpty())
            disposable.add(
                call.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { response ->
                                    owners = response.items
                                    ownerMutableList.addAll(owners)
                                    ownersAdapter.notifyItemRangeInserted(itemInsertPos, owners.size)
                                    //pbUsers.visibility = View.GONE
                                },
                                { t: Throwable? ->
                                    // TODO tell the user about the error cause
                                    //Toast.makeText(context, "Error receiving data",
                                      //      Toast.LENGTH_LONG).show()
                                    Log.e(UsersFragment.TAG, "Exception", t)
                                },
                                { Log.d(UsersFragment.TAG, "Call completed") }
                        )
            )

    }

}