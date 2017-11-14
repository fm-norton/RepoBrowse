package fieldmarshal.repobrowse.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import fieldmarshal.repobrowse.R
import fieldmarshal.repobrowse.models.Owner

class MainActivity : AppCompatActivity(), UsersFragment.OnUserSelectedListener {

    lateinit var usersFragment: UsersFragment
    private lateinit var reposFragment: ReposFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usersFragment = UsersFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, usersFragment)
                .addToBackStack("users")
                .commitAllowingStateLoss()

    }

    override fun onUserSelected(user: Owner) {
        val args = Bundle()
        args.putString("username", user.login)
        reposFragment = ReposFragment.newInstance()
        reposFragment.arguments = args
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, reposFragment)
                .addToBackStack("repos")
                .commitAllowingStateLoss()
        supportActionBar?.title = ""
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
