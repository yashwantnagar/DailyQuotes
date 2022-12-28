package com.ynr.dailyquotes.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.ynr.dailyquotes.R
import com.ynr.dailyquotes.ui.activity.QuotesDetail
import com.ynr.dailyquotes.util.QuotesModel

class ShowQuoteAdapter(

    private val context: Context,
    private val quoteList: MutableList<QuotesModel>

    ) : RecyclerView.Adapter<ShowQuoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.show_quotes_item,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.quotes.text = quoteList[position].quotes
        holder.author.setText(quoteList.get(position).author)

        holder.cardItem.setOnClickListener(View.OnClickListener {

            val intent = Intent(context, QuotesDetail::class.java)
            intent.putExtra("quotes",quoteList[position].quotes)
            intent.putExtra("author",quoteList[position].author)
            context.startActivity(intent)

        })



    }

    override fun getItemCount(): Int {

        return quoteList.size

    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        val cardItem : MaterialCardView = itemView.findViewById(R.id.cardItem)
        val quotes : TextView = itemView.findViewById(R.id.quotes)
        val author : TextView = itemView.findViewById(R.id.author)

    }

}



