package com.ynr.dailyquotes.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.ynr.dailyquotes.R
import com.ynr.dailyquotes.activity.SaveActivity
import com.ynr.dailyquotes.database.Quote
import com.ynr.dailyquotes.database.QuoteDbModel

class SaveQuoteAdapter(

    private val context: SaveActivity,
    private val getList: ArrayList<QuoteDbModel>

    ) : RecyclerView.Adapter<SaveQuoteAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.save_quotes_item,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.quotes.text = getList[position].quotes
        holder.author.text = getList[position].author

        holder.btnDelete.setOnClickListener {

            Log.e("Save", "onBindViewHolder: ${getList[position].id}")

            context.onItemClick(
                Quote(
                    getList[position].id,
                    getList[position].quotes, getList[position].author
                )
            )

            notifyItemChanged(position)

        }
    }


    override fun getItemCount() : Int {

        return getList.size

    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cardItem : MaterialCardView = itemView.findViewById(R.id.cardItem)
        val quotes : TextView = itemView.findViewById(R.id.quotes)
        val author : TextView = itemView.findViewById(R.id.author)
        val btnDelete : AppCompatImageButton = itemView.findViewById(R.id.btnDelete)

    }

}