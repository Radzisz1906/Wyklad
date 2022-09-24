package com.example.wyklad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class recycleadapter(
    private val messages: MutableList<User>
) : RecyclerView.Adapter<recycleadapter.messageViewHolder>() {
    class messageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val usernamet = itemView.findViewById<TextView>(R.id.usernamet)
        val username = itemView.findViewById<TextView>(R.id.username)
        val pointst = itemView.findViewById<TextView>(R.id.pointst)
        val points = itemView.findViewById<TextView>(R.id.points)



        fun bind(curMessage: User){
            username.text = curMessage.login
            points.text = curMessage.points.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): messageViewHolder {
        return messageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_list, parent, false)
        )
    }

    fun addMessage(message: User) {
        messages.add(message)
        notifyItemInserted(messages.size - 1)
    }

    override fun onBindViewHolder(holder: messageViewHolder, position: Int) {
        val curMessage = messages[position]
        holder.bind(curMessage)
    }

    override fun getItemCount(): Int {
        return messages.size
    }
}