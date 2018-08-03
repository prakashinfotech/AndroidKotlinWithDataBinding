package com.example.haresh.kotlinexaple

import Api.ApiClient
import Api.ApiInterface
import android.app.ProgressDialog
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.haresh.kotlinexaple.databinding.ActivityProfilePageBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile_page.view.*
import model.ProfileEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Prakash Software Solution
 * on 16-04-2018.
 */
class ProfilePage : AppCompatActivity() {

    /**
     * Below is define Ui component, interface, binding class
     */
    lateinit var binding: ActivityProfilePageBinding;
    var apiService: ApiInterface? = null
    var context: Context? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this;
        initialization();
    }

    /**
     * This method call when initialize all the component
     */
    private fun initialization() {
        apiService = ApiClient.getClient()?.create(ApiInterface::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_page)
        binding.setVariable(BR.callback,this)
        var userId: Int? = null
        userId = intent.extras.getInt("userId")
        if (userId == null) {
            callProfile(binding, 2)
        } else {
            callProfile(binding, userId)
        }
    }

    /**
     * This method to call Profile Api to Server and Get data to login user
     */
    fun callProfile(binding: ActivityProfilePageBinding, userId: Int) {
        var dialog = ProgressDialog(context)
        dialog?.setMessage("Please wait....")
        dialog?.show()

        var call: Call<ProfileEntity>? = apiService?.getProfilePage(userId)
        call?.enqueue(object : Callback<ProfileEntity> {
            override fun onResponse(call: Call<ProfileEntity>?, response: Response<ProfileEntity>?) {
                if (dialog != null) {
                    dialog.dismiss()
                }
                if (response?.isSuccessful == true) {
                    //This set data in view model and view model will set data in text view
                    binding.viewModel=response?.body()?.getData();
                    //This old methode of data set in text or etc..
                    //binding.root.txtFName?.setText(response?.body()?.getData()?.first_name)
                    //binding.root.txtLName?.setText(response?.body()?.getData()?.last_name)
                    Picasso.get()
                            .load(response?.body()?.getData()?.avatar)
                            .placeholder(R.mipmap.ic_launcher)
                            .error(R.mipmap.ic_launcher)
                            .into(binding.root.imgProfile);
                }
            }

            override fun onFailure(call: Call<ProfileEntity>?, t: Throwable?) {
                t?.printStackTrace()
                if (dialog != null) {
                    dialog.dismiss()
                }
            }
        })

    }
}
