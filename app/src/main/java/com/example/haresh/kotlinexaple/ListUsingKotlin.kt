package com.example.haresh.kotlinexaple

import Api.ApiClient
import Api.ApiInterface
import adapter.LocationAdapter
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.haresh.kotlinexaple.databinding.LayoutlistofplaceBinding
import interfaceclass.ActionCallback
import model.ListUserEntity
import model.LocationEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * Created by Prakash Software Solution
 * on 02-01-2018.
 */
class ListUsingKotlin :AppCompatActivity(), ActionCallback {

    override fun onCrashReport(view: View) {
    }

    override fun onClickAnotherActivity(view: View) {
    }

    /**
     * Below is define Ui component, interface, binding class
     */
    lateinit var mBinding : LayoutlistofplaceBinding;
    lateinit var mLocationAdapter: LocationAdapter
    var  context : Context?=null;
    var apiService: ApiInterface? = null
    var myList: MutableList<ListUserEntity.DataBean> = mutableListOf<ListUserEntity.DataBean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context=this;
        apiService = ApiClient.getClient()?.create(ApiInterface::class.java)
        mBinding=DataBindingUtil.setContentView(this,R.layout.layoutlistofplace)
        mLocationAdapter = LocationAdapter(this,this!!.myList!!)
        mBinding.recyclerview.setLayoutManager(LinearLayoutManager(this))
        mBinding.recyclerview.setAdapter(mLocationAdapter)

        mBinding.recyclerview.setOnClickListener(){
            val intent = Intent(applicationContext, ProfilePage::class.java)
            startActivity(intent)
        }
        callListofUser();
    }

    /**
     * This method get list of user from Server and set in List view
     */
    fun callListofUser() {
        var dialog = ProgressDialog(context)
        dialog?.setMessage("Please wait....")
        dialog?.show()

        var call: Call<ListUserEntity>? = apiService?.getListOfUser()
        call?.enqueue(object : Callback<ListUserEntity> {
            override fun onResponse(call: Call<ListUserEntity>?, response: Response<ListUserEntity>?) {
                if(dialog!=null){
                    dialog.dismiss()
                }
                if(response?.isSuccessful==true){
                    //mLocationList.plus(response?.body()?.data?.toList())
                    response?.body()?.getData()?.let { myList.addAll(it) }

                    mLocationAdapter.notifyDataSetChanged();
                }else if(response?.errorBody()!=null){
                    var str=response.errorBody().string()
                    Toast.makeText(applicationContext,str, Toast.LENGTH_SHORT).show()
                }

            }
            override fun onFailure(call: Call<ListUserEntity>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })

    }
    override fun onClick(view: View) {

    }

}