package com.ynr.dailyquotes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.ynr.dailyquotes.R
import com.ynr.dailyquotes.activity.QuoteSectionActivity
import de.hdodenhof.circleimageview.CircleImageView


class QuoteSectionAdapter (

    private val context : Context

    ) :

    RecyclerView.Adapter<QuoteSectionAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.quote_section_item,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.image.setImageResource(R.drawable.splashscreen)
//        holder.title.text = ""
//        holder.quotes.setText("")


//        holder.cardView.setOnClickListener(View.OnClickListener {
//            Toast.makeText(context,position,Toast.LENGTH_SHORT).show()
//        })

    }

    override fun getItemCount(): Int {

        return 11

    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val cardView : MaterialCardView = itemView.findViewById(R.id.cardView)
        val image : CircleImageView = itemView.findViewById(R.id.image_quote)
        val title : TextView = itemView.findViewById(R.id.title)
        val quotes : TextView = itemView.findViewById(R.id.quotes)

    }

}