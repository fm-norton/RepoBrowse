package fieldmarshal.repobrowse

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import fieldmarshal.repobrowse.api.ApiServiceGenerator
import fieldmarshal.repobrowse.api.GithubRest

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val githubRest = ApiServiceGenerator.createService(GithubRest::class.java)

        var username = "google"
        githubRest.getUserInfo(username)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    user ->
                    Log.d("API", user.name + " " + user.publicRepos)
                    Toast.makeText(this,
                            "Ok " + user.name + " " + user.publicRepos,
                            Toast.LENGTH_LONG).show()
                }, {
                    error -> Log.e("Error", error.message)
                })
    }
}
