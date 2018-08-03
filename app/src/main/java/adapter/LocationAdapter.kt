package adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.haresh.kotlinexaple.ProfilePage
import com.example.haresh.kotlinexaple.R
import model.ListUserEntity
import model.LocationEntity

/**
 * Created by
 * Prakash Software Solution on 02-01-2018.
 */


class LocationAdapter(var mcontaxt: Context, val mLocationList: List<ListUserEntity.DataBean>) : RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(mLocationList[position])
        holder.textViewName?.setOnClickListener {
            val intent = Intent(mcontaxt, ProfilePage::class.java)
            intent.putExtra("userId",mLocationList[position].id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            mcontaxt.startActivity(intent)
        }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return mLocationList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView? = null
        fun bindItems(location: ListUserEntity.DataBean) {
            textViewName = itemView.findViewById<TextView>(R.id.textViewUsername)
            textViewName?.text = location.name

        }
    }

}