package fieldmarshal.repobrowse.api

import fieldmarshal.repobrowse.models.Repo
import fieldmarshal.repobrowse.models.User
import fieldmarshal.repobrowse.models.UserSearchResult
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


/**
 * Created by fieldmarshal on 27.10.17.
 */
interface GithubRest {

    @GET("users/{user}/repos")
    fun reposOfUser(@Path("user") user: String): Observable<Response<List<Repo>>>

    @GET("users/{username}")
    fun getUserInfo(@Path("username") username: String): Observable<User>

    @GET("search/users")
    fun getUsers(@Query("q") query: String?): Observable<Response<UserSearchResult>>

    @GET
    fun getUsersPaginated(@Url url: String?): Observable<Response<UserSearchResult>>

    @GET
    fun reposOfUserPaginated(@Url url: String?): Observable<Response<List<Repo>>>
}