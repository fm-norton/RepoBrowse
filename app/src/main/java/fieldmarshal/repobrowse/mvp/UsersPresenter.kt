package fieldmarshal.repobrowse.mvp

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import fieldmarshal.repobrowse.R
import fieldmarshal.repobrowse.models.Owner
import fieldmarshal.repobrowse.models.UserSearchResult
import fieldmarshal.repobrowse.ui.ReposFragment
import fieldmarshal.repobrowse.ui.UsersFragment
import fieldmarshal.repobrowse.ui.adapters.OwnersAdapter
import fieldmarshal.repobrowse.util.Constants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by fieldmarshal on 02.11.17.
 */

interface UsersView : BaseView

class UsersPresenter(view: UsersView) : BasePresenter<UsersView>(view) {

    private val queryStr = StringBuilder()
            .append(Constants.Q_CREATED)
            .append(">=").append(Constants.Q_DATE).toString()
    private var call: Observable<UserSearchResult> = githubRest.getUsers(queryStr)
    private var itemInsertPos = 0
    private var owners = listOf<Owner>()
    private var ownerMutableList = mutableListOf<Owner>()

    private lateinit var ownersAdapter: OwnersAdapter

    override fun dispose() = super.dispose()

    fun feedAdapter(rv: RecyclerView) { rv.adapter = ownersAdapter }

    override fun initRecyclerAdapter(context: Context) {
        ownersAdapter = OwnersAdapter(context, ownerMutableList, listener = {
            owner ->
            val args = Bundle()
            args.putString("username", owner.login)
            val repoFragment = ReposFragment.newInstance()
            repoFragment.arguments = args
            (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, repoFragment)
                    .addToBackStack("repos")
                    .commitAllowingStateLoss()
        })
    }

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
                                        ownersAdapter
                                                .notifyItemRangeInserted(itemInsertPos, owners.size)
                                        view.hideProgress()
                                    },
                                    { t: Throwable ->
                                        // TODO tell the user about the error cause
                                        //Toast.makeText(context, "Error receiving data",
                                        //      Toast.LENGTH_LONG).show()
                                        view.onError(t)
                                        Log.e(UsersFragment.TAG, "Exception", t)
                                    },
                                    { Log.d(UsersFragment.TAG, "Call completed") }
                            )
            )
        }
    }

}