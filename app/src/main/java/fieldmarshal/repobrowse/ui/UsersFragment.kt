package fieldmarshal.repobrowse.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fieldmarshal.repobrowse.R
import fieldmarshal.repobrowse.models.Owner
import fieldmarshal.repobrowse.mvp.UsersPresenter
import fieldmarshal.repobrowse.mvp.UsersView
import fieldmarshal.repobrowse.util.Constants
import fieldmarshal.repobrowse.util.longToast
import fieldmarshal.repobrowse.util.nothing
import kotlinx.android.synthetic.main.fragment_users.*
import java.io.IOException

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [UsersFragment.OnUserSelectedListener] interface
 * to handle interaction events.
 * Use the [UsersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UsersFragment : Fragment(), UsersView {

    private var selectedListener: OnUserSelectedListener? = null
    private var presenter = UsersPresenter(this)
    private lateinit var tbUsers: Toolbar
    private var itemInsertPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_users, container, false)
        rootView.tag = TAG
        tbUsers = rootView.findViewById(R.id.tbUsers)
        (activity as AppCompatActivity).setSupportActionBar(tbUsers)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvUsers.adapter = OwnersAdapter(context, presenter.ownerMutableList, listener = { user ->
            if (selectedListener != null) {
                selectedListener?.onUserSelected(user)
            }
        })
        val linearLM = LinearLayoutManager(context)
        rvUsers.layoutManager = linearLM
        rvUsers.isNestedScrollingEnabled = false

        val scrollListener = object : RecyclerView.OnScrollListener() {
            val visibleThreshold = 5

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView?.layoutManager
                val totalItemCount = layoutManager?.itemCount
                val lastVisibleItem = (layoutManager as LinearLayoutManager)
                        .findLastVisibleItemPosition()

                if (totalItemCount!! <= (lastVisibleItem + visibleThreshold)) {
                    presenter.loadMoreUsers()
                }
            }
        }
        rvUsers.addOnScrollListener(scrollListener)
        presenter.loadUsers()
    }

    override fun onUsersLoaded() {
        rvUsers.adapter.notifyItemRangeInserted(itemInsertPos, presenter.owners.size)
        itemInsertPos += presenter.owners.size
    }

    override fun onMoreUsersLoaded() {
        rvUsers.adapter.notifyItemRangeInserted(itemInsertPos, presenter.owners.size)
        itemInsertPos += presenter.owners.size
    }

    override fun showProgress() {
        pbUsers.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbUsers.visibility = View.GONE
    }

    override fun onError(t: Throwable) {
        if (t is IOException) longToast(context, Constants.ERROR_IO)
    }

    override fun onOnlineCheck() {
        nothing()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnUserSelectedListener) {
            selectedListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnUserSelectedListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        selectedListener = null
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnUserSelectedListener {
        fun onUserSelected(user: Owner)
    }

    companion object {
        val TAG = "UsersFragment"

        fun newInstance(): UsersFragment {
            return UsersFragment()
        }
    }
}
