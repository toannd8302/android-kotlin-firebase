package com.example.leaningapplicationusingfirebase.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leaningapplicationusingfirebase.R
import com.example.leaningapplicationusingfirebase.adapter.UserAdapter
import com.example.leaningapplicationusingfirebase.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserActivity : AppCompatActivity() {
    var userList = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

    }
//    private fun getUserList() {
//        //Get user from Database
//        var firebase: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
//        var databaseReference: DatabaseReference =
//            FirebaseDatabase.getInstance().getReference("Users")
//        databaseReference.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                userList.clear()
//                for (dataSnapShot: DataSnapshot in snapshot.children) {
//                    var user = dataSnapShot.getValue(User::class.java)!!
//                    if (user!!.userId.equals(firebase.uid)) {
//                        userList.add(user)
//                    }
//                }
//                val userAdapter = UserAdapter(this@UserActivity, userList)
////                val userRecyclerView = findViewById<RecyclerView>(R.id.userRecyclerView)
////                userRecyclerView.adapter = userAdapter
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show()
//            }
//
//        })
//
//    }
}
