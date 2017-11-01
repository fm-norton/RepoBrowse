package fieldmarshal.repobrowse.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import fieldmarshal.repobrowse.R
import fieldmarshal.repobrowse.api.ApiServiceGenerator
import fieldmarshal.repobrowse.api.GithubRest
import fieldmarshal.repobrowse.models.Owner
import fieldmarshal.repobrowse.models.UserSearchResult
import fieldmarshal.repobrowse.ui.adapters.OwnersAdapter
import fieldmarshal.repobrowse.util.Constants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlin.collections.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [UsersRecyclerFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [UsersRecyclerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UsersRecyclerFragment : Fragment() {

    //private var mListener: OnFragmentInteractionListener? = null

    private lateinit var rvUsers: RecyclerView
    private lateinit var ownersAdapter: OwnersAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    protected var owners = listOf<Owner>()
    protected var ownerMutableList = mutableListOf<Owner>()
    private var itemInsertPos = 0


    private val githubRest = ApiServiceGenerator.createService(GithubRest::class.java)

    // TODO fix the query so that it returns correct response
    private var queryStr = StringBuilder()
            .append(Constants.Q_CREATED)
            .append(">=").append(Constants.Q_DATE).toString()

    private var call: Observable<UserSearchResult> = githubRest.getUsers(queryStr)
    private var disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ownersAdapter = OwnersAdapter(context, ownerMutableList, listener = {
            owner -> Toast.makeText(context,
                owner.url, Toast.LENGTH_SHORT).show()
        })
        disposable.add(
                call.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { response ->
                                    response.items.forEach { u ->
                                        Log.d(TAG, u.login + " " + u.reposUrl)
                                    }
                                    owners = response.items
                                    Log.d(TAG, "Users' data received")
                                    ownerMutableList.addAll(owners)
                                    ownersAdapter.notifyItemRangeInserted(itemInsertPos, owners.size)

                                },
                                { t: Throwable? ->
                                    Toast.makeText(context, t?.message, Toast.LENGTH_LONG).show()
                                    Log.e(TAG, "Exception", t)
                                },
                                {
                                    Log.d(TAG, "Call completed")
                                }
                        )
        )
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_users_recycler, container, false)
        rootView.tag = TAG
        rvUsers = rootView.findViewById(R.id.rvUsers)

        rvUsers.adapter = ownersAdapter

        rvUsers.layoutManager = LinearLayoutManager(context)

        return rootView
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        /*if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }*/
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        /*if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }*/
    }

    override fun onDetach() {
        super.onDetach()
        //mListener = null
    }

    override fun onDestroy() {
        disposable.dispose()
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
    /*interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }*/

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val TAG = "UsersRecyclerFragment"

        // TODO: Rename and change types and number of parameters
        fun newInstance(): UsersRecyclerFragment {
            val fragment = UsersRecyclerFragment()
            //val args = Bundle()
            //args.putString(ARG_PARAM1, param1)
            //args.putString(ARG_PARAM2, param2)
            //fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
