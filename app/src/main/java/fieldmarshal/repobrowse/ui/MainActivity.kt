package fieldmarshal.repobrowse.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import fieldmarshal.repobrowse.R
import fieldmarshal.repobrowse.presenters.MainPresenter
import fieldmarshal.repobrowse.views.MainView

class MainActivity : AppCompatActivity() {

    /*@InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var mainPresenter: MainPresenter*/

    lateinit var usersFragment : UsersRecyclerFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usersFragment = UsersRecyclerFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, usersFragment)
                .addToBackStack("users")
                .commitAllowingStateLoss()

    }
}
