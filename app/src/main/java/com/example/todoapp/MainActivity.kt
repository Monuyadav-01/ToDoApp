package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    private  var list=ArrayList<Data>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       val dbRf=Firebase.database.getReference("data")
        binding.rV.layoutManager=LinearLayoutManager(this)
        val rvAdapter=RvAdapter(list,this )
        binding.rV.adapter=rvAdapter

        dbRf.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for(dataSnap in snapshot.children){
                    val data=dataSnap.getValue(Data::class.java)
                   list.add(data!!)
                    rvAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }


        })


        binding.imageBtn.setOnClickListener {
            val i=Intent(this, ToDoActivity::class.java)
            startActivity(i)
        }
    }
}

