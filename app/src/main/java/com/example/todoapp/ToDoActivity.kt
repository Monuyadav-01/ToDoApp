package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todoapp.databinding.ActivityToDoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ToDoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityToDoBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToDoBinding.inflate(layoutInflater)
        database = Firebase.database.reference
        setContentView(binding.root)

        binding.button.setOnClickListener {

            if (binding.editTextText.length() == 0) {
                Toast.makeText(this, "Enter Your Task", Toast.LENGTH_LONG).show()
            } else {
                val key = database.child("data").key
                database.child(key!!).push()
                    .setValue(Data(binding.editTextText.text.toString(), key))
                    .addOnCompleteListener {
                        Toast.makeText(this, "Your Data Is Added", Toast.LENGTH_LONG).show()
                        val i = Intent(this, MainActivity::class.java)
                        startActivity(i)
                    }


            }
        }
    }


}