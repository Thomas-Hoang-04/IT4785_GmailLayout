package com.example.gmaillayout.recylerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gmaillayout.R

class EmailAdapter(private val emailList: List<EmailData>): RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {
    class EmailViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val sender: TextView = itemView.findViewById(R.id.sender)
        val subject: TextView = itemView.findViewById(R.id.title)
        val message: TextView = itemView.findViewById(R.id.content)
        val dateTime: TextView = itemView.findViewById(R.id.datetime)
        val favourite: ImageView = itemView.findViewById(R.id.favor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val emailData = emailList[position]
        holder.sender.text = emailData.sender
        holder.subject.text = emailData.subject
        holder.message.text = emailData.message
        holder.dateTime.text = emailData.dateTime.toString()
        holder.favourite.setImageResource(
            if (emailData.isFavor) R.drawable.baseline_star_24
            else R.drawable.baseline_star_border_24
        )
    }

    override fun getItemCount(): Int = emailList.size
}