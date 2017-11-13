package fieldmarshal.repobrowse.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import fieldmarshal.repobrowse.R
import fieldmarshal.repobrowse.api.ApiServiceGenerator
import fieldmarshal.repobrowse.api.GithubRest
import fieldmarshal.repobrowse.models.Repo
import fieldmarshal.repobrowse.models.User
import fieldmarshal.repobrowse.ui.adapters.RepoAdapter
import fieldmarshal.repobrowse.util.loadUrlAndCropCircle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_repos.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ReposFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ReposFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReposFragment : Fragment() {

    //private var mListener: OnFragmentInteractionListener? = null

    private lateinit var reposAdapter: RepoAdapter

    private var repos = listOf<Repo>()
    private var reposMutableList = mutableListOf<Repo>()
    private var itemInsertPos = 0

    private lateinit var user: User

    private val githubRest = ApiServiceGenerator.createService(GithubRest::class.java)

    var disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater!!.inflate(R.layout.fragment_repos, container, false)
        rootView.tag = this::class.java.simpleName
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = arguments.getString("username")
        val callRepos: Observable<List<Repo>> = githubRest.reposOfUser(username)
        val callUserInfo: Observable<User> = githubRest.getUserInfo(username)

        reposAdapter = RepoAdapter(context, reposMutableList, listener = {

        })
        rvRepos.adapter = reposAdapter
        rvRepos.layoutManager = LinearLayoutManager(context)
        rvRepos.isNestedScrollingEnabled = false

        (activity as AppCompatActivity).setSupportActionBar(toolbar)


        pbRepos.visibility = View.VISIBLE
        disposable.add(
                callUserInfo.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { response ->
                                    user = response
                                    backdrop.loadUrlAndCropCircle(user.avatarUrl)
                                    collapsing_toolbar.title = user.name
                                    //var reposCount = String.format(resources
                                    //       .getString(R.string.repos_count), user.publicRepos)
                                    //collapsing_toolbar. = reposCount
                                    pbRepos.visibility = View.GONE
                                },
                                {
                                    t -> t.printStackTrace()
                                }, { Log.d(TAG, "UserInfo call completed") }
                        )
        )
        disposable.add(
                callRepos.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { response ->
                                    repos = response
                                    reposMutableList.addAll(repos)
                                    reposAdapter.notifyItemRangeInserted(itemInsertPos, repos.size)
                                    pbRepos.visibility = View.GONE
                                },
                                { t ->
                                    Toast.makeText(context, "Error receiving data",
                                            Toast.LENGTH_LONG).show()
                                    Log.e(TAG, t.message, t)
                                },
                                {
                                    Log.d(TAG, "Repos call completed")
                                    //backdrop.loadUrlAndCropCircle(repos)
                                }
                        )
        )
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
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        var TAG = "ReposFragment"

        fun newInstance(): ReposFragment {
            return ReposFragment()
        }
    }
}
