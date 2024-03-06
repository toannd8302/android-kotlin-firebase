package com.example.leaningapplicationusingfirebase.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.leaningapplicationusingfirebase.R
import com.example.leaningapplicationusingfirebase.models.User
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(private val context: Context, private val userList: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
      return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
     val user = userList[position]
        holder.txtUserName.text = user.userName
        // Load ảnh từ URL sử dụng Glide
        Glide.with(context)
            .load(user.userImage)
            .placeholder(R.drawable.baseline_add_24) // Ảnh placeholder cho trường hợp load ảnh không thành công
            .error(R.drawable.baseline_clear_24) // Ảnh hiển thị khi load ảnh thất bại
            .into(holder.imgUser)
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtUserName:TextView = view.findViewById(R.id.userName)
        val textDummy:TextView = view.findViewById(R.id.tmp)
        val imgUser: CircleImageView = view.findViewById(R.id.userImage)
    }
}