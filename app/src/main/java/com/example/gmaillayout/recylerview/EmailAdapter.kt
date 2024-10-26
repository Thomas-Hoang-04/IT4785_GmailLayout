package com.example.gmaillayout.recylerview

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gmaillayout.R
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class EmailAdapter(private val emailList: List<EmailData>): RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {
    class EmailViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var isStared: Boolean = true
        val sender: TextView = itemView.findViewById(R.id.sender)
        val subject: TextView = itemView.findViewById(R.id.title)
        val message: TextView = itemView.findViewById(R.id.content)
        val dateTime: TextView = itemView.findViewById(R.id.datetime)
        val favourite: ImageView = itemView.findViewById(R.id.favor)
    }

    private fun parseDateTime(dateTime: LocalDateTime): String {
        val locale: Locale = Locale.getDefault()
        var patternDate: String = "MMM d"
        var patternTime: String = "hh:mm"
        when (locale.language) {
            "en" -> {
                patternDate = "MMM d"
                patternTime = "hh:mm"
            }
            "vi" -> {
                patternDate = "d MMM"
                patternTime = "h:mm"
            }
            "ko" -> {
                patternDate = "MMM d"
                patternTime = "a h:mm"
            }
        }
        return if (Duration.between(dateTime, LocalDateTime.now()).toDays() == 0L) dateTime.toLocalTime().format(DateTimeFormatter.ofPattern(patternTime))
        else dateTime.toLocalDate().format(DateTimeFormatter.ofPattern(patternDate))
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
        holder.dateTime.text = this.parseDateTime(emailData.dateTime)
        if (emailData.read) {
            holder.sender.setTypeface(Typeface.DEFAULT_BOLD)
            holder.subject.setTypeface(Typeface.DEFAULT_BOLD)
            holder.dateTime.setTypeface(Typeface.DEFAULT_BOLD)
        }
        holder.favourite.setImageResource(
            if (emailData.isFavor) R.drawable.baseline_star_24
            else R.drawable.baseline_star_border_24
        )
        holder.isStared = emailData.isFavor
        holder.favourite.setOnClickListener( { v ->
            if (holder.isStared){
                (v as ImageView).setImageResource(R.drawable.baseline_star_24)
            } else {
                (v as ImageView).setImageResource(R.drawable.baseline_star_border_24)
            }
            holder.isStared = !holder.isStared
        })
    }

    override fun getItemCount(): Int = emailList.size
}