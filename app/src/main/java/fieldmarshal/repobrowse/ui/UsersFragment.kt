package fieldmarshal.repobrowse.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fieldmarshal.repobrowse.R
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
 * [UsersFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [UsersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UsersFragment : Fragment(), UsersView {

    //private var mListener: OnFragmentInteractionListener? = null

    private var presenter = UsersPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_users, container, false)
        rootView.tag = TAG
        return rootView
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.initRecyclerAdapter(context)
        presenter.feedAdapter(rvUsers)
        rvUsers.layoutManager = LinearLayoutManager(context)
        rvUsers.isNestedScrollingEnabled = false

        presenter.loadUsers()
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
    /*interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }*/

    companion object {
        val TAG = "UsersFragment"

        fun newInstance(): UsersFragment {
            return UsersFragment()
        }
    }
}
