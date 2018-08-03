package com.example.haresh.kotlinexaple

import Api.ApiClient
import Api.ApiInterface
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.layout_without_findview.*
import model.LoginEntitiy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by
 * Prakash Software Solution
 * on 20-12-2017.
 */
class WithoutFindView :AppCompatActivity() {
    /**
     * Below is define Ui component, interface, binding class
     */
    var  apiService: ApiInterface?=null
    var context:Context?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context=this;
        setContentView(R.layout.layout_without_findview)
        apiService = ApiClient.getClient()?.create(ApiInterface::class.java)

        //Type 1
        btnSubmit.setOnClickListener{
            if(editFirstName.text.isNullOrBlank()){
                Toast.makeText(applicationContext,getString(R.string.message_editFirst),Toast.LENGTH_LONG)?.show()
            }else if(editLastName.text.isNullOrBlank()){
                Toast.makeText(applicationContext,getString(R.string.message_editLastName),Toast.LENGTH_LONG)?.show()
            }else {
                callLoginMethod();

            }

        }
         //Type 2
        /*btnSubmit.setOnClickListener({
            if(editFirstName?.text.isNullOrBlank()){
                Toast.makeText(applicationContext,getString(R.string.message_editFirst),Toast.LENGTH_LONG)?.show()
            }else if(editLastName?.text.isNullOrBlank()){
                Toast.makeText(applicationContext,getString(R.string.message_editLastName),Toast.LENGTH_LONG)?.show()
            }else {
                var strFirstName:String?=null
                var strLastName:String?=null
                strFirstName=editFirstName.text.toString()
                strLastName=editLastName.text.toString()
                txt_FirstAndLastName.text=strFirstName.plus(" "+strLastName)
            }

        })

         //Type 3
        btnSubmit?.setOnClickListener(View.OnClickListener {

            if (editFirstName?.text.isNullOrBlank()) {
                Toast.makeText(applicationContext, getString(R.string.message_editFirst), Toast.LENGTH_LONG)?.show()
            } else if (editLastName?.text.isNullOrBlank()) {
                Toast.makeText(applicationContext, getString(R.string.message_editLastName), Toast.LENGTH_LONG)?.show()
            } else {
                var strFirstName: String? = null
                var strLastName: String? = null
                strFirstName = editFirstName.text.toString()
                strLastName = editLastName.text.toString()
                txt_FirstAndLastName.text = strFirstName.plus(" " + strLastName)
            }
        })*/

    }

    fun callLoginMethod(){
        val dialog = ProgressDialog(context)
        dialog?.setMessage("Please wait....")
        dialog?.show()

        var call : Call<LoginEntitiy>? =apiService?.getLogin(editFirstName.text.toString(),editLastName.text.toString())
        call?.enqueue(object : Callback<LoginEntitiy> {
            override fun onResponse(call: Call<LoginEntitiy>?, response: Response<LoginEntitiy>?) {
                if(dialog!=null){
                    dialog.dismiss()
                }
                if(response?.isSuccessful!!){
                    txt_FirstAndLastName.text=response?.body()?.token
                }else if(response?.errorBody()!=null){
                    var str=response.errorBody().string()
                    Toast.makeText(applicationContext,str,Toast.LENGTH_SHORT).show()
                    editFirstName.text.clear()
                    editLastName.text.clear()
                }
            }

            override fun onFailure(call: Call<LoginEntitiy>?, t: Throwable?) {
                t?.printStackTrace()
                Toast.makeText(applicationContext,t?.printStackTrace().toString(),Toast.LENGTH_SHORT).show()
            }
        })

    }
}