package Api

import utility.Constants.Companion.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Prakash Software Solution
 * on 19-12-2017.
 */
class ApiClient {
    /**
     *  This API Singleton Class with use for all api calling
     */
    companion object {
    var retrofit: Retrofit?=null
    var token:String?="123";
    fun getClient() : Retrofit? {
        if (retrofit ==null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(setClient())
                    .build();
        }
        return retrofit;
    }

    fun setClient(): OkHttpClient {
        val interceptor = Interceptor { chain ->
            val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization-Token", token)
                    .build()
            chain.proceed(newRequest)
        }
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(interceptor)
        return okHttpBuilder.build()
    }
    }
}
