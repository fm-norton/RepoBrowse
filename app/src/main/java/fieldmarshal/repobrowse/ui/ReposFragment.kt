package fieldmarshal.repobrowse.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fieldmarshal.repobrowse.R
import fieldmarshal.repobrowse.models.User
import fieldmarshal.repobrowse.mvp.ReposPresenter
import fieldmarshal.repobrowse.mvp.ReposView
import fieldmarshal.repobrowse.util.*
import kotlinx.android.synthetic.main.fragment_repos.*
import java.io.IOException


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ReposFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ReposFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReposFragment : Fragment(), ReposView {

    //private var mListener: OnFragmentInteractionListener? = null

    private var presenter = ReposPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_repos, container, false)
        rootView.tag = this::class.java.simpleName
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.initServiceCalls(arguments.getString("username"))
        presenter.initRecyclerAdapter(context)
        presenter.feedAdapter(rvRepos)
        rvRepos.layoutManager = LinearLayoutManager(context)
        rvRepos.isNestedScrollingEnabled = false

        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        presenter.loadToolbar()
        presenter.loadRepos()

    }

    override fun inflateToolbar(user: User) {
        backdrop.loadUrlAndCropCircle(user.avatarUrl)
        collapsing_toolbar.title = user.name
        collapsing_toolbar.setContentScrimColor(resources.getColor(R.color.md_light_appbar))
        collapsing_toolbar.setBackgroundColor(resources.getColor(R.color.md_light_appbar))
    }

    override fun showToolbarProgress() {
        pbToolbar.visibility = View.VISIBLE
    }

    override fun hideToolbarProgress() {
        pbToolbar.visibility = View.GONE
    }

    override fun showProgress() {
        pbRepos.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbRepos.visibility = View.GONE
    }

    override fun onError(t: Throwable) {
        if (t is IOException)
            longToast(context, Constants.ERROR_IO)
    }

    override fun onOnlineCheck() {
        nothing()
    }

    override fun onDestroy() {
        presenter.dispose()
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
