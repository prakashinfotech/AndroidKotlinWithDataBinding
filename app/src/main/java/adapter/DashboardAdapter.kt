package adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.haresh.kotlinexaple.ListUsingKotlin
import com.example.haresh.kotlinexaple.ProfilePage
import com.example.haresh.kotlinexaple.R
import com.example.haresh.kotlinexaple.ScanActivity

/**
 * Created
 * by Prakash Software Solution
 * on 11-05-2018.
 */
class DashboardAdapter(var mcontaxt:Context,val mLocationList: List<String>) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_row_dashbaord, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(mLocationList.get(position))
        holder.textViewName?.setOnClickListener {
            if(mLocationList.get(position).equals("Profile Page")) {
                val intent = Intent(mcontaxt, ProfilePage::class.java)
                intent.putExtra("userId",2)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                mcontaxt.startActivity(intent)
            }else if(mLocationList.get(position).equals(mcontaxt.getString(R.string.lbl_scandit))) {
                val intent = Intent(mcontaxt, ScanActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                mcontaxt.startActivity(intent)
            }else{
                val intent = Intent(mcontaxt, ListUsingKotlin::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                mcontaxt.startActivity(intent)
            }
        }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return mLocationList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView? = null
        fun bindItems(location: String) {
            textViewName = itemView.findViewById<TextView>(R.id.textViewUsername)
            textViewName?.text = location

        }
    }


}