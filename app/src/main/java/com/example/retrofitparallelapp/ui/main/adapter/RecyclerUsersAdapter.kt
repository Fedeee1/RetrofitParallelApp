package com.example.retrofitparallelapp.ui.main.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitparallelapp.R
import com.example.retrofitparallelapp.data.domain.model.user.UserNameModel
import com.example.retrofitparallelapp.ui.main.MainActivity
import javax.inject.Inject

class RecyclerUsersAdapter @Inject constructor(
    private var listUsers: List<UserNameModel>,
    private var activity: MainActivity,
    private val listener: OnUserItemClickListener):
    RecyclerView.Adapter<RecyclerUsersAdapter.ViewHolder>() {

    interface OnUserItemClickListener{
        fun onUserClick(user: UserNameModel)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var txtUserName: TextView
        var txtUserId: TextView
        private var constraintCardPokemons: ConstraintLayout
        var cardUser: CardView

        init {
            txtUserName = itemView.findViewById(R.id.txtUserName)
            txtUserId = itemView.findViewById(R.id.txtUserId)
            constraintCardPokemons = itemView.findViewById(R.id.constraintCardUsers)
            cardUser = itemView.findViewById(R.id.cardUser)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_users, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.txtUserName.text = listUsers[position].name.replaceFirstChar { it.uppercaseChar() }
        viewHolder.txtUserId.text = listUsers[position].id.toString()

        viewHolder.cardUser.setOnClickListener {
            val user = listUsers[position]
            listener.onUserClick(user)
        }
    }
    override fun getItemCount(): Int {
        return listUsers.size
    }
}