package com.example.todoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class RvAdapter(private val dataList: ArrayList<Data>, private val context: Context) :
    RecyclerView.Adapter<RvAdapter.ViewHoHolder>() {

    class ViewHoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById<TextView>(R.id.textView)
        val delete: Button = itemView.findViewById<Button>(R.id.Delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_design, parent, false)

        return ViewHoHolder(view)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHoHolder, position: Int) {
        holder.textView.text = dataList[position].data
        holder.delete.setOnClickListener {
            val key = dataList.get(position).key
            val dbRf=Firebase.database.getReference("data").child(key!!)
            dbRf.removeValue().addOnCompleteListener {
                if (it.isSuccessful) {
                    notifyDataSetChanged()
                    Toast.makeText(context, "Data deleted", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}


