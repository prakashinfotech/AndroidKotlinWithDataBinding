package com.example.haresh.kotlinexaple

import adapter.DashboardAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.haresh.kotlinexaple.databinding.ActivityDashboardBinding
import android.support.v7.widget.GridLayoutManager
import android.util.Log


/**
 * Created by Prakash Software Solution
 * on 11-05-2018.
 */
class DashBoard :AppCompatActivity() {
    /**
     * Below is define Ui component, interface, binding class
     */
    lateinit var banding:ActivityDashboardBinding;
    lateinit var adapterDashboard: DashboardAdapter
    private val dashBoardList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        banding=DataBindingUtil.setContentView(this,R.layout.activity_dashboard)
        dashBoardList.add(getString(R.string.lbl_profile_page))
        dashBoardList.add(getString(R.string.lbl_listpage))
        dashBoardList.add(getString(R.string.lbl_scandit))
        adapterDashboard = DashboardAdapter(applicationContext,this!!.dashBoardList!!)
        banding.recyclerview.setLayoutManager(GridLayoutManager(this, 2));
        //banding.recyclerview.setLayoutManager(LinearLayoutManager(this))
        banding.recyclerview.setAdapter(adapterDashboard)


    }
}