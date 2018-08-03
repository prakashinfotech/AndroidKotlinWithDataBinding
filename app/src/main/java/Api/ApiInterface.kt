package Api

import model.ListUserEntity
import model.LocationEntity
import model.LoginEntitiy
import model.ProfileEntity
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Prakash Software Solution
 * on 19-12-2017.
 */
interface ApiInterface {

    /**
     * This are All define  Get and Post Method
     */
    @FormUrlEncoded
    @POST("api/login?")
    fun getLogin(@Field("email") username_or_email:String,
                 @Field("password") password:String
                 ): Call<LoginEntitiy>

    @GET("api/users/{userId}")
    fun getProfilePage(@Path("userId") userId:Int): Call<ProfileEntity>

    @GET("api/unknown")
    fun getListOfUser():Call<ListUserEntity>



}