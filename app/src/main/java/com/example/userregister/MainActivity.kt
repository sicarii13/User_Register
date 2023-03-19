package com.example.userregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userName: EditText = findViewById(R.id.etxtName)
        val userPhone: EditText = findViewById(R.id.etxtPhone)
        val addUser: Button = findViewById(R.id.btnAdd)
        val viewUsers: Button = findViewById(R.id.btnViewUsers)

        addUser.setOnClickListener {
            if(userName.text.isEmpty() || userPhone.text.isEmpty()){
                Toast.makeText(this@MainActivity, "Please complete all fields", Toast.LENGTH_SHORT).show()
            }else {
                val user = User(userName.text.toString(), userPhone.text.toString())

                val db = Firebase.firestore
                db.collection("Users")
                    .add(user)

                Toast.makeText(this@MainActivity, "Data added successfully", Toast.LENGTH_SHORT).show()
            }
        }

        viewUsers.setOnClickListener {
            startActivity(Intent(this, UserListing::class.java))
        }
    }
}