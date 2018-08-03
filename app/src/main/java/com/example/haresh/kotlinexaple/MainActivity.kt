package com.example.haresh.kotlinexaple

import Api.ApiClient
import Api.ApiInterface
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.haresh.kotlinexaple.databinding.ActivityMainBinding
import interfaceclass.ActionCallback
import model.LoginEntitiy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric




class MainActivity : AppCompatActivity(), ActionCallback {
    /**
     * Below is define Ui component, interface, binding class
     */
    var apiService: ApiInterface? = null
    var context:Activity?=null
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialization();
    }

    /**
     * This method call when initialize all the component
     */
    fun initialization(){
        Fabric.with(this, Crashlytics())
        val fabric = Fabric.Builder(this)
                .kits(Crashlytics())
                .debuggable(true) // Enables Crashlytics debugger
                .build()
        Fabric.with(fabric)

        context=this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        apiService = ApiClient.getClient()?.create(ApiInterface::class.java)
        binding.setVariable(BR.callback,this)
    }

    /**
     * Method will validate user name and password
     * call api
     */
    fun callButtonEvent(binding: ActivityMainBinding) {
        if (binding.editUserName?.text.isNullOrBlank()) {
            Toast.makeText(applicationContext, getString(R.string.msg_username), Toast.LENGTH_LONG).show()
        } else if (binding.editPassword?.text.isNullOrBlank()) {
            Toast.makeText(applicationContext, getString(R.string.msg_password), Toast.LENGTH_LONG).show()
        } else {
            callLogin(binding);
        }
    }
   fun callAnotherActivity() {
        val intent = Intent(applicationContext, DashBoard::class.java)
        intent.putExtra("Name", "Haresh Ramani")
        startActivity(intent)
    }

    /**
     *  This method validate to authentication from server
     */
    fun callLogin(binding: ActivityMainBinding) {
        val dialog = ProgressDialog(context)
        dialog?.setMessage("Please wait....")
        dialog?.show()

        var call: Call<LoginEntitiy>? = apiService?.getLogin(binding.editUserName.text.toString(), binding.editPassword.text.toString())
        call?.enqueue(object : Callback<LoginEntitiy> {
            override fun onResponse(call: Call<LoginEntitiy>?, response: Response<LoginEntitiy>?) {
                if(dialog.isShowing){
                    dialog.dismiss()
                }
                if(response?.isSuccessful!!){
                    //binding.editValue?.setText(response?.body()?.data?.first_name + " " + response?.body()?.data?.last_name)
                    //ApiClient.token=response?.headers()?.get("Authorization-Token");
                    Toast.makeText(applicationContext,getString(R.string.msg_loging),Toast.LENGTH_SHORT).show()
                    callAnotherActivity();
                }else if(response?.errorBody()!=null){
                    var str=response.errorBody().string()
                    Toast.makeText(applicationContext,str,Toast.LENGTH_SHORT).show()
                    binding.editUserName.text.clear()
                    binding.editPassword.text.clear()
                    binding.editValue.text.clear()
                }

            }
            override fun onFailure(call: Call<LoginEntitiy>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })

    }

    override fun onClickAnotherActivity(view:View) {
        val intent = Intent(applicationContext, ListUsingKotlin::class.java)
        startActivity(intent)
    }
    override fun onClick(view: View) {
        callButtonEvent(binding);
    }
    override fun onCrashReport(view: View) {
        Toast.makeText(applicationContext, "Please check google Crash Report in google fireBase.", Toast.LENGTH_LONG).show()
        Crashlytics.getInstance().crash();
    }

}
