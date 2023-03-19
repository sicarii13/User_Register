package com.example.userregister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserListing : AppCompatActivity() {

    private var db = Firebase.firestore
    private lateinit var userList: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_listing)

        var rvUsers: RecyclerView = findViewById(R.id.rvUsers)
        rvUsers.layoutManager = LinearLayoutManager(this)

        userList = arrayListOf()

        db = FirebaseFirestore.getInstance()

        db.collection("Users").get()
            .addOnSuccessListener {
                if(!it.isEmpty){
                    for (data in it.documents){
                        val user: User? = data.toObject(User::class.java)
                        if(user != null){
                            userList.add(user)
                        }
                    }
                    rvUsers.adapter = MyAdapter(userList)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }


    }
}