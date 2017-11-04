package fieldmarshal.repobrowse.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import fieldmarshal.repobrowse.R

class MainActivity : AppCompatActivity() {

    /*@InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var mainPresenter: MainPresenter*/

    private lateinit var usersFragment : UsersRecyclerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usersFragment = UsersRecyclerFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, usersFragment)
                .addToBackStack("users")
                .commitAllowingStateLoss()

    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStackImmediate()
        } else {
            finish()
            super.onBackPressed()
        }
    }
}
