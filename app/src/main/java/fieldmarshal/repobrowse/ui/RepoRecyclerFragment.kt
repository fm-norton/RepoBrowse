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
import fieldmarshal.repobrowse.models.Repo
import fieldmarshal.repobrowse.models.UserSearchResult
import fieldmarshal.repobrowse.ui.adapters.OwnersAdapter
import fieldmarshal.repobrowse.ui.adapters.RepoAdapter
import fieldmarshal.repobrowse.util.Constants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [RepoRecyclerFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [RepoRecyclerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RepoRecyclerFragment : Fragment() {

    //private var mListener: OnFragmentInteractionListener? = null

    private lateinit var rvRepos: RecyclerView
    private lateinit var reposAdapter: RepoAdapter

    private var repos = listOf<Repo>()
    private var reposMutableList = mutableListOf<Repo>()
    private var itemInsertPos = 0

    private val githubRest = ApiServiceGenerator.createService(GithubRest::class.java)

    var disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(context,
                "RepoRecyclerFragment.onCreate", Toast.LENGTH_SHORT).show()
        var username = arguments.getString("username")
        var call: Observable<List<Repo>> = githubRest.reposOfUser(username)

        reposAdapter = RepoAdapter(context, reposMutableList, listener = {

        })
        disposable.add(
                call.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { response ->
                                    repos = response
                                    reposMutableList.addAll(repos)
                                    reposAdapter.notifyItemRangeInserted(itemInsertPos, repos.size)
                                    /*response.forEach { r ->
                                        Log.d(RepoRecyclerFragment.TAG, r.fullName)
                                    }*/
                                }, { t -> Log.e(TAG, t.message, t) },
                                { Log.d(TAG, "Call completed") }
                        )
        )
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var rootView = inflater!!.inflate(R.layout.fragment_repo_recycler, container, false)
        rootView.tag = this::class.java.simpleName

        rvRepos= rootView.findViewById(R.id.rvRepos)

        rvRepos.adapter = reposAdapter
        rvRepos.layoutManager = LinearLayoutManager(context)

        return rootView
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        var TAG = "RepoRecyclerFragment"
        // TODO: Rename and change types and number of parameters
        fun newInstance(): RepoRecyclerFragment {
            var fragment = RepoRecyclerFragment()
            /*val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)*/
            fragment.arguments = Bundle()
            return fragment
        }
    }
}// Required empty public constructor
