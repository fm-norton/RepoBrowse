package fieldmarshal.repobrowse.api

import fieldmarshal.repobrowse.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by fieldmarshal on 29.10.17.
 */

class ApiServiceGenerator {

    companion object {

        private var client = OkHttpClient.Builder()

        private fun getClientLogger() : OkHttpClient.Builder {
            client.addInterceptor(HttpLoggingInterceptor().setLevel(Level.BODY))
            return client
        }

        private var builder = Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(getClientLogger().build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

        private var retrofit = builder.build()

        fun <T> createService(serviceClass: Class<T>) : T {
            return retrofit.create(serviceClass)
        }

    }

}