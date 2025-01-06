package com.example.suitmediamobiledevelopertestquestion1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.suitmediamobiledevelopertestquestion1.R
import com.example.suitmediamobiledevelopertestquestion1.models.User
import com.squareup.picasso.Picasso

class UserAdapter(private val users: List<User>, private val onItemClick: (User) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = users.size

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val avatarImageView: ImageView = itemView.findViewById(R.id.avatarImageView)

        fun bind(user: User) {
            nameTextView.text = "${user.first_name} ${user.last_name}"
            Picasso.get().load(user.avatar).into(avatarImageView)

            itemView.setOnClickListener {
                onItemClick(user)
            }
        }
    }
}